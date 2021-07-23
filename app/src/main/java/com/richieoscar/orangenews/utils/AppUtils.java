package com.richieoscar.orangenews.utils;

import java.util.Calendar;

public class AppUtils {

    private AppUtils(){}

    public static String fromDate(){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        return String.format("%d-%02d-%d", year,  month , day);
    }

    public static String toDate(){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        return String.format("%d-%02d-%d", year,  month , day);
    }
}
