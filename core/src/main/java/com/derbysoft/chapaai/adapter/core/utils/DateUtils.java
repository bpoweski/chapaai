package com.derbysoft.chapaai.adapter.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DateUtils {

    private static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";


    public static String getUTC(Date date) {
        return new SimpleDateFormat(DATE_FORMAT_FULL).format(date);
    }

    private static String getDateFormatYmd(int type, String date, int interval) {
        Calendar calendar = Calendar.getInstance();
        if (date.length() != 10) {
            throw new IllegalArgumentException(date + " format error");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YMD);
        try {
            calendar.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        calendar.add(type, interval);
        return dateFormat.format(calendar.getTime());
    }

    private static int getDay() {
        return Calendar.DATE;
    }

    private static int getMonth() {
        return Calendar.MONTH;
    }

    private static int getYear() {
        return Calendar.YEAR;
    }

    public static String nextDay(String date, int day) {
        return getDateFormatYmd(getDay(), date, day);
    }

    public static String nextYear(String date, int year) {
        return getDateFormatYmd(getYear(), date, year);
    }

    public static String nextMonth(String date, int month) {
        return getDateFormatYmd(getMonth(), date, month);
    }

    public static List<String> rangeDate(String starDate, String overDate) {
        List<String> rangeDates = new ArrayList<String>();
        String dateIterator = starDate;
        if (overDate.compareTo(starDate) > 0) {
            for (; !dateIterator.equals(overDate); dateIterator = DateUtils.nextDay(dateIterator, 1)) {
                rangeDates.add(dateIterator);
            }
            rangeDates.add(overDate);
        }
        if (overDate.equals(starDate)) {
            rangeDates.add(overDate);
        }
        return rangeDates;
    }
}
