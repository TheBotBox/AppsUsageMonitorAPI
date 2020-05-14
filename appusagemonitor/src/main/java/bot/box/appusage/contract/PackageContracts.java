package bot.box.appusage.contract;

import bot.box.appusage.model.AppData;
import bot.box.appusage.utils.DurationRange;

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */

public class PackageContracts {

    public interface View extends BaseView {

        void getUsageForPackage(AppData appData, @DurationRange.DURATIONRANGE int duration);
    }

    public interface Presenter {
        void loadUsageForPackage(int duration, String mPackageName);
    }
}
