package bot.box.appusage.handler;

import bot.box.appusage.contract.UsageContracts;
import bot.box.appusage.utils.DurationRange;

/**
 * Created by BarryAllen
 *
 * @TheBotBox boxforbot@gmail.com
 */
public class UsageGenerator {

    private UsageContracts.Presenter mPresenter;


    UsageGenerator(UsageContracts.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void fetchFor(@DurationRange.DURATIONRANGE int duration) {
        if (this.mPresenter != null) {
            this.mPresenter.loadUsageData(duration);

        } else
            throw new IllegalStateException("Your view must implement UsageContract.View");
    }

}
