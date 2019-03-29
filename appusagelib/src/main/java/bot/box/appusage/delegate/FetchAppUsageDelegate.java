package bot.box.appusage.delegate;

import android.os.AsyncTask;

import java.util.List;

import bot.box.appusage.BotMonitor;
import bot.box.appusage.datamanager.DataManager;
import bot.box.appusage.model.AppData;

/**
 * Created by BarryAllen
 *
 * @TheBotBOx boxforbot@gmail.com
 */
public class FetchAppUsageDelegate extends AsyncTask<Integer, Integer, List<AppData>> {
    private AppUsageCallback mListener;

    public FetchAppUsageDelegate(AppUsageCallback l) {
        this.mListener = l;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.mListener.onPreExecute();
    }

    @Override
    protected List<AppData> doInBackground(Integer... params) {
        return DataManager.getInstance().getUsedApps(BotMonitor.getMonitorContext(), params[0]);
    }

    @Override
    protected void onPostExecute(List<AppData> aVoid) {
        super.onPostExecute(aVoid);
        long mTotalUsage = 0;

        for (AppData item : aVoid) {
            if (item.mUsageTime <= 0) continue;
            mTotalUsage += item.mUsageTime;
            item.mCanOpen = BotMonitor.getMonitorContext().getPackageManager().getLaunchIntentForPackage(item.mPackageName) != null;
        }

        this.mListener.onAppDataFetch(aVoid, mTotalUsage);
    }

    public interface AppUsageCallback {
        void onPreExecute();

        void onAppDataFetch(List<AppData> usageData, long mTotalUsage);
    }
}
