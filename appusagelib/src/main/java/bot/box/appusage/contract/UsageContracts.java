package bot.box.appusage.contract;

import java.util.List;

import bot.box.appusage.model.AppData;
import bot.box.appusage.utils.Duration;

/**
 * Created by BarryAllen
 *
 * @TheBotBOx boxforbot@gmail.com
 */
public class UsageContracts {

    public interface View extends BaseView {
        void getUsageData(List<AppData> usageData, long mTotalUsage, @Duration.DURATION int duration);
    }

    public interface Presenter {
        void loadUsageData(int duration);
    }
}
