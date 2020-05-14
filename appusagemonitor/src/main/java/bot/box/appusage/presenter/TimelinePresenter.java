package bot.box.appusage.presenter;

import android.os.AsyncTask;

import bot.box.appusage.contract.TimelineContracts;
import bot.box.appusage.delegate.FetchPackageTimelineDelegate;
import bot.box.appusage.utils.DurationRange;

public class TimelinePresenter implements TimelineContracts.Presenter {
    private TimelineContracts.View mView;

    public TimelinePresenter(TimelineContracts.View mView) {
        this.mView = mView;
    }


    @Override
    public void generateTimeline(@DurationRange.DURATIONRANGE int duration, String mPackageName) {
        this.mView.showProgress();
        new FetchPackageTimelineDelegate(mList -> {
            this.mView.onTimelineGenerated(mList);
            this.mView.hideProgress();
        }, duration).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mPackageName);
    }
}
