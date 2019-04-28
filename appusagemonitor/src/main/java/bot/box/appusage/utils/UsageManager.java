package bot.box.appusage.utils;


import java.util.ArrayList;
import java.util.List;

import bot.box.appusage.model.AppData;

/**
 * Created by BarryAllen
 *
 * @TheBotBox boxforbot@gmail.com
 */
public class UsageManager {

    private static UsageManager ourInstance;
    private List<AppData> mList;
    private long mTotalUsage;


    private UsageManager() {
        mList = new ArrayList<>();

    }

    public static UsageManager getInstance() {
        if (ourInstance == null) {
            synchronized (UsageManager.class) {
                if (ourInstance == null) {
                    ourInstance = new UsageManager();
                }
            }
        }
        return ourInstance;
    }

    public void setAppUsageList(List<AppData> mList, long totalUsage) {
        this.mList = mList;
        this.mTotalUsage = totalUsage;
    }

    public List<AppData> getAppUsageList() {
        return this.mList;
    }

    public long getTotalUsage() {
        return this.mTotalUsage;
    }

}
