package bot.box.appusage.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

/**
 * Created by BarryAllen
 *
 * @TheBotBox boxforbot@gmail.com
 */
public class AppData implements Parcelable {

    public String mName;
    public String mPackageName;
    public long mEventTime;
    public long mUsageTime;
    public int mEventType;
    public int mCount;
    public long mMobile;
    public long mWifi;
    public boolean mCanOpen;
    public boolean mIsSystem;

    public AppData() {
    }

    protected AppData(Parcel in) {
        mName = in.readString();
        mPackageName = in.readString();
        mEventTime = in.readLong();
        mUsageTime = in.readLong();
        mEventType = in.readInt();
        mCount = in.readInt();
        mMobile = in.readLong();
        mWifi = in.readLong();
        mCanOpen = in.readByte() != 0;
        mIsSystem = in.readByte() != 0;
    }

    public static final Creator<AppData> CREATOR = new Creator<AppData>() {
        @Override
        public AppData createFromParcel(Parcel in) {
            return new AppData(in);
        }

        @Override
        public AppData[] newArray(int size) {
            return new AppData[size];
        }
    };

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "name:%s package_name:%s time:%d total:%d type:%d system:%b count:%d",
                mName, mPackageName, mEventTime, mUsageTime, mEventType, mIsSystem, mCount);
    }


    public AppData copy() {
        AppData newItem = new AppData();
        newItem.mName = this.mName;
        newItem.mPackageName = this.mPackageName;
        newItem.mEventTime = this.mEventTime;
        newItem.mUsageTime = this.mUsageTime;
        newItem.mEventType = this.mEventType;
        newItem.mIsSystem = this.mIsSystem;
        newItem.mCount = this.mCount;
        return newItem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mPackageName);
        dest.writeLong(mEventTime);
        dest.writeLong(mUsageTime);
        dest.writeInt(mEventType);
        dest.writeInt(mCount);
        dest.writeLong(mMobile);
        dest.writeLong(mWifi);
        dest.writeByte((byte) (mCanOpen ? 1 : 0));
        dest.writeByte((byte) (mIsSystem ? 1 : 0));
    }
}
