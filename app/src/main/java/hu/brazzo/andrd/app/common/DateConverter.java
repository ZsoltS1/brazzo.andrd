package hu.brazzo.andrd.app.common;

import android.databinding.BindingConversion;
import android.databinding.InverseMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {

    private static ThreadLocal<SimpleDateFormat> dayDateFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };

    private static ThreadLocal<SimpleDateFormat> fullDateFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS zzz");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };

    private static ThreadLocal<SimpleDateFormat> monthDateFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM.dd");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };

    private static ThreadLocal<SimpleDateFormat> hourDateFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };

    private static ThreadLocal<SimpleDateFormat> dayOfWeekDateFormat = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };


    @BindingConversion
    @InverseMethod("convertDisplayedTextToDate")
    public static String convertDateToDisplayedText(Date date) {
        if (date == null) {
            return "";
        }
        return dayDateFormat.get().format(date);
    }

    @BindingConversion
    public static Date convertDisplayedTextToDate(String date) {
        try {
            return dayDateFormat.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BindingConversion
    public static Date convertStringToUTCDate(String date) {
        try {
            return fullDateFormat.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BindingConversion
    public static String convertDateToMonthAndDayText(Date date) {
        if (date == null) {
            return "";
        }
        return monthDateFormat.get().format(date);
    }

    @BindingConversion
    public static String convertDateToHourAndMinutes(Date date) {
        if (date == null) {
            return "";
        }
        return hourDateFormat.get().format(date);
    }

    @BindingConversion
    public static String convertDateToDayOfWeek(Date date) {
        if (date == null) {
            return "";
        }
        return dayOfWeekDateFormat.get().format(date);
    }

}
