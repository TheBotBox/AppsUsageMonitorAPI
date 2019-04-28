package bot.box.appusage.contract;

import bot.box.appusage.utils.Duration;

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */

public class PackageContracts {

    public interface View extends BaseView {

        void getUsageForPackage(String mPackage, long mTotalUsage, @Duration.DURATION int duration);
    }

    public interface Presenter {
        void loadUsageForPackage(int duration, String mPackageName);
    }
}
