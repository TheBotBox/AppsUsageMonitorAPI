package bot.box.appusage.datamanager;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bot.box.appusage.model.AppData;
import bot.box.appusage.utils.SortOrder;
import bot.box.appusage.utils.UsageUtils;

/**
 * Created by BarryAllen
 *
 * @TheBotBox boxforbot@gmail.com
 */
public class DataManager {

    private static DataManager mInstance;

    public static void init() {
        mInstance = new DataManager();
    }

    public static DataManager getInstance() {
        return mInstance;
    }


    public List<AppData> getUsedApps(Context context, int offset) {

        List<AppData> items = new ArrayList<>();
        List<AppData> newList = new ArrayList<>();
        UsageStatsManager manager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        if (manager != null) {

            String prevPackage = "";
            Map<String, Long> startPoints = new HashMap<>();
            Map<String, ClonedEvent> endPoints = new HashMap<>();

            SortOrder sortEnum = SortOrder.getSortEnum(offset);

            long[] range = UsageUtils.getTimeRange(sortEnum);
            UsageEvents events;
            if (sortEnum.name().equalsIgnoreCase("MONTH")) {
                events = manager.queryEvents(UsageStatsManager.INTERVAL_MONTHLY, range[1]);
            } else {
                events = manager.queryEvents(range[0], range[1]);
            }

            UsageEvents.Event event = new UsageEvents.Event();
            while (events.hasNextEvent()) {

                events.getNextEvent(event);
                int eventType = event.getEventType();
                long eventTime = event.getTimeStamp();
                String eventPackage = event.getPackageName();

                if (eventType == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                    AppData item = containItem(items, eventPackage);
                    if (item == null) {
                        item = new AppData();
                        item.mPackageName = eventPackage;
                        items.add(item);
                    }
                    if (!startPoints.containsKey(eventPackage)) {
                        startPoints.put(eventPackage, eventTime);
                    }
                }

                if (eventType == UsageEvents.Event.MOVE_TO_BACKGROUND) {
                    if (startPoints.size() > 0 && startPoints.containsKey(eventPackage)) {
                        endPoints.put(eventPackage, new ClonedEvent(event));
                    }
                }

                if (TextUtils.isEmpty(prevPackage)) prevPackage = eventPackage;
                if (!prevPackage.equals(eventPackage)) {
                    if (startPoints.containsKey(prevPackage) && endPoints.containsKey(prevPackage)) {
                        ClonedEvent lastEndEvent = endPoints.get(prevPackage);
                        AppData listItem = containItem(items, prevPackage);
                        if (listItem != null) { // update list item info
                            listItem.mEventTime = lastEndEvent.timeStamp;
                            long duration = lastEndEvent.timeStamp - startPoints.get(prevPackage);
                            if (duration <= 0) duration = 0;
                            listItem.mUsageTime += duration;
                            if (duration > UsageUtils.USAGE_TIME_MIX) {
                                listItem.mCount++;
                            }
                        }
                        startPoints.remove(prevPackage);
                        endPoints.remove(prevPackage);
                    }
                    prevPackage = eventPackage;
                }
            }
        }

        if (items.size() > 0) {
            boolean hideSystem = false;
            boolean hideUninstall = true;

            PackageManager packageManager = context.getPackageManager();
            for (AppData item : items) {
                if (!UsageUtils.openable(packageManager, item.mPackageName)) {
                    continue;
                }
                if (hideSystem && UsageUtils.isSystemApp(packageManager, item.mPackageName)) {
                    continue;
                }
                if (hideUninstall && !UsageUtils.isInstalled(packageManager, item.mPackageName)) {
                    continue;
                }


                item.mName = UsageUtils.parsePackageName(packageManager, item.mPackageName);
                newList.add(item);
            }

            Collections.sort(newList, (left, right) -> (int) (right.mUsageTime - left.mUsageTime));

        }
        return newList;
    }

    private AppData containItem(List<AppData> items, String packageName) {
        for (AppData item : items) {
            if (item.mPackageName.equals(packageName)) return item;
        }
        return null;
    }
    class ClonedEvent {

        String packageName;
        String eventClass;
        long timeStamp;
        int eventType;


        ClonedEvent(UsageEvents.Event event) {
            packageName = event.getPackageName();
            eventClass = event.getClassName();
            timeStamp = event.getTimeStamp();
            eventType = event.getEventType();
        }
    }
}
