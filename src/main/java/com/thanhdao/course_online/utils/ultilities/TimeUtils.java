package com.thanhdao.course_online.utils.ultilities;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class TimeUtils {
    public static Timestamp getCurrentVietNamTime(){
        Instant currentTimeInUTC = Instant.now();
//        currentTimeInUTC.
        return new Timestamp(currentTimeInUTC.plus(7, ChronoUnit.DAYS).toEpochMilli());
    }
}
