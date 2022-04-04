package com.example.cloudx_small_tools.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: 日期工具类
 * @author: 10191
 * @date:2022/3/12 10:46
 **/
public class DateUtils {

    public static final String SUNDAY = "SUNDAY";
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";


    /**
     * 获取指定年份所有日期
     * @param year
     * @return
     */
    public static List<Date> getAllDatesOfYear(int year){
        List<Date> allDate = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.set(year, 0, 1);
        Calendar end = Calendar.getInstance();
        end.set(year + 1, 0, 0 );
        while (start.compareTo(end) <= 0){
            allDate.add(start.getTime());
            start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
        }
        return allDate;
    }

    /**
     * 获取指定日期星期数
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case 1:
                return SUNDAY;
            case 2:
                return MONDAY;
            case 3:
                return TUESDAY;
            case 4:
                return WEDNESDAY;
            case 5:
                return THURSDAY;
            case 6:
                return FRIDAY;
            case 7:
                return SATURDAY;
            default:
                return "";
        }
    }

    /**
     * 指定日期增加天数（为负数则为减少天数）
     * @param days
     * @return
     */
    public static Date addDays(Date date, Integer days){
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static void main(String[] args) {

    }
}
