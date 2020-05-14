package bot.box.appusage.presenter;

import java.util.List;

import bot.box.appusage.contract.PackageContracts;
import bot.box.appusage.delegate.FetchAppUsageDelegate;
import bot.box.appusage.model.AppData;
import bot.box.appusage.utils.UsageManager;

/**
 * Created by Barry Allen .
 * boxforbot@gmail.com
 */

public class PackagePresenter implements PackageContracts.Presenter {

    private final PackageContracts.View mView;

    public PackagePresenter(PackageContracts.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadUsageForPackage(int duration, String mPackageName) {
        if (UsageManager.getInstance() != null) {
            if (UsageManager.getInstance().getAppUsageList() != null
                    && UsageManager.getInstance().getAppUsageList().size() > 0) {
                this.mView.showProgress();
                for (AppData data : UsageManager.getInstance().getAppUsageList()) {
                    if (data.mPackageName.equals(mPackageName)) {
                        this.mView.getUsageForPackage(data, duration);
                        this.mView.hideProgress();
                        break;
                    }
                }

            } else {
                new FetchAppUsageDelegate(new FetchAppUsageDelegate.AppUsageCallback() {
                    @Override
                    public void onPreExecute() {
                        mView.showProgress();
                    }

                    @Override
                    public void onAppDataFetch(List<AppData> usageData, long mTotalUsage) {
                        UsageManager.getInstance().setAppUsageList(usageData, mTotalUsage);
                        for (AppData data : usageData) {
                            if (data.mPackageName.equals(mPackageName)) {
                                mView.getUsageForPackage(data, duration);
                                mView.hideProgress();
                                break;
                            }
                        }
                    }
                }).executeExecutor(duration);

            }
        } else {
            new FetchAppUsageDelegate(new FetchAppUsageDelegate.AppUsageCallback() {
                @Override
                public void onPreExecute() {
                    mView.showProgress();
                }

                @Override
                public void onAppDataFetch(List<AppData> usageData, long mTotalUsage) {
                    UsageManager.getInstance().setAppUsageList(usageData, mTotalUsage);
                    for (AppData data : usageData) {
                        if (data.mPackageName.equals(mPackageName)) {
                            mView.getUsageForPackage(data, duration);
                            mView.hideProgress();
                            break;
                        }
                    }
                }
            }).executeExecutor(duration);
        }
    }
}
