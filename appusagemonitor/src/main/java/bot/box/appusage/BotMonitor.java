package bot.box.appusage;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by BarryAllen
 *
 * @TheBotBOx boxforbot@gmail.com
 */
public class BotMonitor {

    private static Context mContext;

    public static void instantiate(Context context) {
        mContext = context;
    }

    public static Context getMonitorContext() {
        return mContext.getApplicationContext();
    }


    public static void requestPermission() {
        mContext.startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
                .setFlags(FLAG_ACTIVITY_NEW_TASK));
    }

    public static boolean checkUsagePermission() {
        AppOpsManager
                appOps = (AppOpsManager) mContext.getSystemService(Context.APP_OPS_SERVICE);
        if (appOps != null) {
            int mode = appOps.checkOpNoThrow("android:get_usage_stats", android.os.Process.myUid(),
                    mContext.getPackageName());
            return mode == AppOpsManager.MODE_ALLOWED;
        }

        return false;
    }
}
