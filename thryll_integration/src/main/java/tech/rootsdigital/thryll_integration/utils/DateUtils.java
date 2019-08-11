package tech.rootsdigital.thryll_integration.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kwaku on 11/08/2017.
 */

public class DateUtils {
    public static String getCurrentDateFormatted(Context context){
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss",
                context.getResources().getConfiguration().locale);
        String DateToStr = format.format(curDate);
        System.out.println(DateToStr);
        return DateToStr;
    }

    public static String FormatDateToMonthDay(Date date){
        SimpleDateFormat format = new SimpleDateFormat("MMM dd");
        return format.format(date);
    }

    public static String FormatDateToMonthDay(long date){
        SimpleDateFormat format = new SimpleDateFormat("MMM dd");
        return format.format(new Date(date));
    }

    public static String FormatDateToDayMMdd(long date){
        Date temp = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd");
        return format.format(temp);
    }

    public static String FormatDateToTime(Date date){
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        return format.format(date);
    }

    public static String FormatDateToTime(long date){
        Date temp = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        return format.format(temp);
    }

    public static String FormatDateToDayMMdd(Date date){
        SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd");
        return format.format(date);
    }

    public static String FormatDateToDateTime(long date){
        Date temp = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd - h:mm a");
        return format.format(temp);
    }

    public static Date MergeDateTime(long date, long time){
        Calendar md = Calendar.getInstance();
        Calendar mt = Calendar.getInstance();
        Calendar result = Calendar.getInstance();

        md.setTime(new Date(date));
        mt.setTime(new Date(time));

        result.set(md.get(Calendar.YEAR), md.get(Calendar.MONTH), md.get(Calendar.DAY_OF_MONTH),
                mt.get(Calendar.HOUR_OF_DAY), mt.get(Calendar.MINUTE));

        return result.getTime();
    }

    public static Date MergeDateTime(long date, int hours, int minutes){
        Calendar md = Calendar.getInstance();
        Calendar result = Calendar.getInstance();

        md.setTime(new Date(date));

        result.set(md.get(Calendar.YEAR), md.get(Calendar.MONTH), md.get(Calendar.DAY_OF_MONTH),
                hours, minutes);

        return result.getTime();
    }

    public static String GetDateAgeFormatted(long startDate) {
        //milliseconds
        long difference = new Date().getTime() - startDate;

        System.out.println("startDate : " + startDate);
        System.out.println("different : " + difference);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = difference / daysInMilli;
        difference = difference % daysInMilli;

        long elapsedHours = difference / hoursInMilli;
        difference = difference % hoursInMilli;

        long elapsedMinutes = difference / minutesInMilli;

        if(elapsedDays > 6)
            return FormatDateToDayMMdd(startDate);
        else if (elapsedDays > 1)
            return elapsedDays + " days ago";
        else if (elapsedDays == 1)
            return elapsedDays + " day ago";
        else if (elapsedHours > 1)
            return elapsedHours + " hrs ago";
        else if (elapsedHours == 1)
            return elapsedHours + " hr ago";
        else if (elapsedMinutes > 1)
            return elapsedMinutes + " mins ago";
        else
            return  "1 min ago";

    }

    public static int getDateAgeInDays(long startDate){
        //milliseconds
        long difference = new Date().getTime() - startDate;

        System.out.println("startDate : " + startDate);
        System.out.println("different : " + difference);

        long daysInMilli = 1000 * 60 * 60 * 24;

        long elapsedDays = difference / daysInMilli;

        return (int) Math.floor(elapsedDays);
    }

    public static long SubtractHoursFrom(long time, int hours){
        Date temp = new Date(time);

        Calendar mc = Calendar.getInstance();
        mc.setTime(temp);
        mc.add(Calendar.HOUR_OF_DAY,-hours);

        temp = mc.getTime();
        return temp.getTime();
    }

    public static long AddHoursTo(long time, int hours){
        Date temp = new Date(time);

        Calendar mc = Calendar.getInstance();
        mc.setTime(temp);
        mc.add(Calendar.HOUR_OF_DAY,hours);

        temp = mc.getTime();
        return temp.getTime();
    }

    public static long AddDaysTo(long date, int days){
        Date temp = new Date(date);

        Calendar mc = Calendar.getInstance();
        mc.setTime(temp);
        mc.add(Calendar.DAY_OF_MONTH,days);

        temp = mc.getTime();
        return temp.getTime();
    }

    public static boolean DateIsYoungerThan(long date, int ageInDays){
        long today = new Date().getTime();
        long target = AddDaysTo(date,ageInDays);

        return today < target;
    }

    public static int GetTimeofDay(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 6 && timeOfDay < 12){
            return 0;
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            return 1;
        }else if(timeOfDay >= 16 && timeOfDay < 18){
            return 2;
        }else if(timeOfDay >= 18 && timeOfDay < 24){
            return 3;
        }else
            return 4;
    }

    public static long getDateMs(long timestamp){

        Calendar md = Calendar.getInstance();

        Calendar result = Calendar.getInstance();

        md.setTime(new Date(timestamp));

        result.set(md.get(Calendar.YEAR), md.get(Calendar.MONTH), md.get(Calendar.DAY_OF_MONTH),
                0, 0);

        return result.getTime().getTime();
    }
}
