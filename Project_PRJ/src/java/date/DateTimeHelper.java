/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package date;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public class DateTimeHelper {

    public static ArrayList<Date> getListDate(Date from, Date to) {
        ArrayList<Date> dates = new ArrayList<>();
        Date loop = from;
        while (loop.compareTo(to) <= 0) {
            dates.add(loop);
            java.util.Date loop_util = toUtilDate(loop);
            loop = toSQLDate(addDays(loop_util, 1));
        }
        return dates;
    }

    public static java.sql.Date toSQLDate(java.util.Date value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(value);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static java.util.Date addDays(java.util.Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static java.util.Date toUtilDate(java.sql.Date value) {
        return new java.util.Date(value.getTime());
    }

    public static ArrayList<Date> getWeekFromDate(String dateString) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(dateFormat.parse(dateString).getTime());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

            ArrayList<Date> weekDays = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                Date weekDay = new Date(calendar.getTimeInMillis());
                weekDays.add(weekDay);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            return weekDays;
        } catch (ParseException ex) {
            Logger.getLogger(DateTimeHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static void main(String[] args) {
        ArrayList<Date> date = getWeekFromDate("2023-03-06");
        for (Date dated : date) {
            System.out.println(dated);
        }
    }
}
