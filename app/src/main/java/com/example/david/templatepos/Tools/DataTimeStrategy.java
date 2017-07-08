package com.example.david.templatepos.Tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by David on 23/06/2017.
 */

public class DataTimeStrategy {

    private static Locale locale;
    private static SimpleDateFormat dateFormat;
    public static String format = "dd/MM/yyyy hh:mm:ss";
    public static String formatReport = "dd/MM/yyyy";

    private DataTimeStrategy() {
        // Static class
    }

    /**
     * Set local of time for use in application.
     * @param lang Language.
     * @param reg Region.
     */
    public static void setLocale(String lang, String reg) {
        locale = new Locale(lang, reg);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", locale);
    }

    /**
     * Sets current time format.
     * @param date date of this format.
     * @return current time format.
     */
    public static String format(String date) {
        return dateFormat.format(Calendar.getInstance(locale).getTime());
    }

    /**
     * Returns current time.
     * @return current time.
     */
    public static String getCurrentTime() {
        return dateFormat.format(Calendar.getInstance().getTime()).toString();
    }

    /**
     * Convert the calendar format to date format for adapt in SQL.
     * @param instance calendar .
     * @return date format.
     */
    public static String getSQLDateFormat(Calendar instance) {
        return dateFormat.format(instance.getTime()).toString().substring(0,10);
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getDate(long milliSeconds)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getDateReport(long milliSeconds)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(formatReport);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}
