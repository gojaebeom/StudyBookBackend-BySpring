package me.studybook.api.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeToNaturalTime {
    public static final int SEC = 60;
    public static final int MIN = 60;
    public static final int HOUR = 24;
    public static final int DAY = 30;
    public static final int MONTH = 12;


    public static String formatTimeString(LocalDateTime tempDate) {

        long curTime = System.currentTimeMillis();
        long regTime = tempDate.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(curTime);
        System.out.println(regTime);
        long diffTime = (curTime - regTime) / 1000;

        String msg = null;
        if ( diffTime < TimeToNaturalTime.SEC ) {
            // sec
            msg = "방금 전";
        } else if ((diffTime /= TimeToNaturalTime.SEC) < TimeToNaturalTime.MIN) {
            // min
            msg = diffTime + "분 전";
        } else if ((diffTime /= TimeToNaturalTime.MIN) < TimeToNaturalTime.HOUR) {
            // hour
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= TimeToNaturalTime.HOUR) < TimeToNaturalTime.DAY) {
            // day
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= TimeToNaturalTime.DAY) < TimeToNaturalTime.MONTH) {
            // day
            msg = (diffTime) + "달 전";
        } else {
            msg = (diffTime) + "년 전";
        }
        return msg;
    }
}