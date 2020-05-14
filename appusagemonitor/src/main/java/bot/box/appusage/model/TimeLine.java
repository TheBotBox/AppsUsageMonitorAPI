package bot.box.appusage.model;

public class TimeLine extends AppData {

    public long mUsageForegroundTime;

    public TimeLine copy() {
        TimeLine timeLine = new TimeLine();
        timeLine.mName = this.mName;
        timeLine.mPackageName = this.mPackageName;
        timeLine.mEventTime = this.mEventTime;
        timeLine.mUsageTime = this.mUsageTime;
        timeLine.mEventType = this.mEventType;
        timeLine.mIsSystem = this.mIsSystem;
        timeLine.mCount = this.mCount;
        return timeLine;
    }
}
