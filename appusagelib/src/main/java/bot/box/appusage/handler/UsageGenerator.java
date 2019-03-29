package bot.box.appusage.handler;

import bot.box.appusage.contract.UsageContracts;
import bot.box.appusage.utils.Duration;

/**
 * Created by BarryAllen
 *
 * @TheBotBOx boxforbot@gmail.com
 */
public class UsageGenerator {

    private UsageContracts.Presenter mPresenter;

    UsageGenerator(UsageContracts.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void fetchFor(@Duration.DURATION int duration) {
        if (mPresenter != null)
            this.mPresenter.loadUsageData(duration);
        else
            throw new IllegalStateException("View must implement UsageContract.View");
    }

}
