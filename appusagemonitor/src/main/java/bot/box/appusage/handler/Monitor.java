package bot.box.appusage.handler;

import bot.box.appusage.BotMonitor;
import bot.box.appusage.contract.PackageContracts;
import bot.box.appusage.contract.TimelineContracts;
import bot.box.appusage.contract.UsageContracts;
import bot.box.appusage.presenter.PackagePresenter;
import bot.box.appusage.presenter.TimelinePresenter;
import bot.box.appusage.presenter.UsagePresenter;



/**
 * Created by BarryAllen
 *
 * @TheBotBox boxforbot@gmail.com
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

    public PackageGenerator queryFor(PackageContracts.View mView) {
        return new PackageGenerator(new PackagePresenter(mView));
    }

    public TimelineGenerator generateTimeline(TimelineContracts.View mView) {
        return new TimelineGenerator(new TimelinePresenter(mView));
    }
}
