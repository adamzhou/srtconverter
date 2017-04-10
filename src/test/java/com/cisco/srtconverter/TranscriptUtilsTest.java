package com.cisco.srtconverter;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by linzhou on 4/9/17.
 */
public class TranscriptUtilsTest {
    @Test
    public void testToSRTTime() {
        double time1 = 5.92;
        double time2 = 60.52;
        String srtTime1 = "00:00:05,920";
        String srtTime2 = "00:01:00,520";
        assertEquals(srtTime1, TranscriptUtils.toSRTTime(time1));
        assertEquals(srtTime2, TranscriptUtils.toSRTTime(time2));
    }
}
