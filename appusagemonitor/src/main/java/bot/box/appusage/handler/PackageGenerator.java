package bot.box.appusage.handler;

import bot.box.appusage.contract.PackageContracts;
import bot.box.appusage.utils.DurationRange;

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */

public class PackageGenerator {

    private PackageContracts.Presenter mPresenter;

    PackageGenerator(PackageContracts.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }


    public void fetchFor(@DurationRange.DURATIONRANGE int duration) {
        if (mPackageName == null)
            throw new NullPointerException("Must provide a legit package name i.e. " +
                    "Monitor.scan().queryFor().whichPackage().fetchFor()");

        if (this.mPackageName.trim().length() != 0) {
            fetchFor(duration, this.mPackageName);
        } else
            throw new IllegalArgumentException("Must provide a legit package name i.e. " +
                    "Monitor.scan().queryFor().whichPackage().fetchFor()");
    }


    private void fetchFor(@DurationRange.DURATIONRANGE int duration, String mPackage) {
        if (mPresenter != null)
            this.mPresenter.loadUsageForPackage(duration, mPackage);
        else
            throw new IllegalStateException("Your view must implement UsageContract.View");
    }

    private String mPackageName;

    public PackageGenerator whichPackage(String mPackage) {
        if (mPackage.trim().length() == 0) {
            throw new IllegalArgumentException("Package Name must not be empty.");
        }
        this.mPackageName = mPackage;
        return this;
    }

}
