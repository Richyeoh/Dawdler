package com.richyeoh.android.dawdler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateOps {
    private static SimpleDateFormat sFormat;

    static {
        sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }

    private DateOps() {
    }

    public static Date getCurrentDate() {
        return getCurrentDate(0);
    }

    public static Date getCurrentDate(int amount) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        if (!(amount <= 0)) {
            calendar.add(Calendar.DATE, amount);
        }
        return calendar.getTime();
    }

    public static String getCurrentDate(String pattern) {
        sFormat.applyPattern(pattern);
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        Date time = calendar.getTime();
        return sFormat.format(time);
    }

    public static Date parseDate(String pattern, String time) {
        Date date = null;
        try {
            sFormat.applyPattern(pattern);
            date = sFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
