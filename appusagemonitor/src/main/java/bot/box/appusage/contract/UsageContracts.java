package bot.box.appusage.contract;

import java.util.List;

import bot.box.appusage.model.AppData;
import bot.box.appusage.utils.DurationRange;

/**
 * Created by BarryAllen
 *
 * @TheBotBox boxforbot@gmail.com
 */
public class UsageContracts {

    public interface View extends BaseView {
        void getUsageData(List<AppData> usageData, long mTotalUsage, @DurationRange.DURATIONRANGE int duration);


    }

    public interface Presenter {
        void loadUsageData(int duration);
    }
}
