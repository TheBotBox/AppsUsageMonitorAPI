package bot.box.appusage.handler;

import bot.box.appusage.BotMonitor;
import bot.box.appusage.contract.UsageContracts;
import bot.box.appusage.presenter.UsagePresenter;

/**
 * Created by BarryAllen
 *
 * @TheBotBOx boxforbot@gmail.com
 */
public class Monitor {

    private static Monitor mInstance = null;

    private Monitor() {
    }

    public static void requestUsagePermission() {
        BotMonitor.requestPermission();
    }

    public static boolean hasUsagePermission() {
        return BotMonitor.checkUsagePermission();
    }

    public static Monitor scan() {
        if (mInstance == null) {
            synchronized (Monitor.class) {
                if (mInstance == null) {
                    mInstance = new Monitor();
                }
            }
        }
        return mInstance;
    }

    public UsageGenerator getAppLists(UsageContracts.View mView) {
        return new UsageGenerator(new UsagePresenter(mView));
    }


}
