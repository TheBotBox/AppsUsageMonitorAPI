package bot.box.appusage.delegate;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.os.AsyncTask;

import com.google.common.collect.Lists;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import bot.box.appusage.BotMonitor;
import bot.box.appusage.datamanager.DataManager;
import bot.box.appusage.model.TimeLine;

/**
 * Created by Anon on 12,March,2019
 */
public class FetchPackageTimelineDelegate extends AsyncTask<String, Void, List<List<TimeLine>>> {

    private WeakReference<Context> mContext;
    private OnTimeLineCallback mCallback;
    private int duration;

    public FetchPackageTimelineDelegate(OnTimeLineCallback l, int offSet) {
        mContext = new WeakReference<>(BotMonitor.getMonitorContext());
        this.mCallback = l;
        this.duration = offSet;
    }

    @Override
    protected List<List<TimeLine>> doInBackground(String... strings) {
        List<TimeLine> targetAppTimeline = DataManager.getInstance().getTargetAppTimeline(mContext.get(), strings[0], this.duration);

        List<TimeLine> newList = new ArrayList<>();

        for (TimeLine item : targetAppTimeline) {
            if (item.mEventType == UsageEvents.Event.USER_INTERACTION || item.mEventType == UsageEvents.Event.NONE) {
                continue;
            }

            if (item.mEventType == UsageEvents.Event.MOVE_TO_BACKGROUND) {
                TimeLine newItem = item.copy();
                newItem.mEventType = -1;
                newList.add(newItem);
            }
            newList.add(item);
        }
        return Lists.partition(newList, 3);
    }

    @Override
    protected void onPostExecute(List<List<TimeLine>> appData) {
        mCallback.onTimeLineFetched(appData);
    }


    public interface OnTimeLineCallback {
        void onTimeLineFetched(List<List<TimeLine>> mList);
    }
}
