package bot.box.appusage.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by BarryAllen
 *
 * @TheBotBox boxforbot@gmail.com
 */
public class Duration {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {TODAY, YESTERDAY, WEEK, MONTH})

    public @interface DURATION {
    }

    public static final int TODAY = 0, YESTERDAY = 1, WEEK = 2, MONTH = 3;

    public static String getCurrentReadableDay(@DURATION int duration) {
        if (duration == 0)
            return "TODAY";
        else if (duration == 1)
            return "YESTERDAY";
        else if (duration == 2)
            return "WEEK";
        else if (duration == 3)
            return "MONTH";
        else if (duration == 4)
            return "YEAR";

        return "TODAY";
    }

}
