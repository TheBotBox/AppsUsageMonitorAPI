package bot.box.appusage.contract;

import java.util.List;

import bot.box.appusage.model.TimeLine;

public class TimelineContracts {
    public interface View extends BaseView {
        void onTimelineGenerated(List<List<TimeLine>> timeline);
    }

    public interface Presenter {
        void generateTimeline(int duration, String mPackageName);
    }
}
