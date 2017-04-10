package com.cisco.srtconverter;

import java.util.concurrent.TimeUnit;

/**
 * Created by linzhou on 4/9/17.
 */
public class TranscriptUtils {
    public static String toSRTTime(double from) {
        //SRT time format: hours:minutes:seconds,milliseconds
        long totalMilliSeconds = (long) (from * 1000);
        long hours = TimeUnit.MILLISECONDS.toHours(totalMilliSeconds);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(totalMilliSeconds - TimeUnit.HOURS.toMillis(hours));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(totalMilliSeconds - TimeUnit.HOURS.toMillis(hours) - TimeUnit.MINUTES.toMillis(minutes));
        long milliseconds = totalMilliSeconds - TimeUnit.HOURS.toMillis(hours) - TimeUnit.MINUTES.toMillis(minutes) - TimeUnit.SECONDS.toMillis(seconds);
        return new String().format("%02d:%02d:%02d,%d", hours, minutes, seconds, milliseconds);
    }
}
