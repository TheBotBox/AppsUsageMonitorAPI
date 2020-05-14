package bot.box.appusage.handler;

import bot.box.appusage.contract.TimelineContracts;
import bot.box.appusage.utils.DurationRange;

public class TimelineGenerator {
    private TimelineContracts.Presenter mPresenter;

    TimelineGenerator(TimelineContracts.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void fetchForToday() {
        if (mPackageName == null)
            throw new NullPointerException("Must provide a legit package name i.e. " +
                    "Monitor.scan().generateTimeline().whichPackage().fetchFor()");

        if (this.mPackageName.trim().length() != 0)
            fetchFor(DurationRange.TODAY, this.mPackageName);
        else
            throw new IllegalArgumentException("Must provide a legit package name i.e. " +
                    "Monitor.scan().generateTimeline().whichPackage().fetchFor()");
    }


    private void fetchFor(@DurationRange.DURATIONRANGE int duration, String mPackage) {
        if (mPresenter != null)
            this.mPresenter.generateTimeline(duration, mPackage);
        else
            throw new IllegalStateException("Your view must implement TimelineContracts.View");
    }


    private String mPackageName;

    public TimelineGenerator whichPackage(String mPackage) {
        if (mPackage.trim().length() == 0) {
            throw new IllegalArgumentException("Package Name must not be empty.");
        }
        this.mPackageName = mPackage;
        return this;
    }
}
