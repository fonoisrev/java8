package wuhang.java8.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // date
        LocalDate date = LocalDate.of(2017, 3, 18);
        
        System.out.println(date.getYear());
        
        Month month = date.getMonth();
        System.out.println(month.getDisplayName(TextStyle.FULL, Locale.CHINA));
        System.out.println(month.getValue());
        
        System.out.println(date.getDayOfMonth());
        
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale
                .CHINA));
        System.out.println(dayOfWeek.getValue());
        
        System.out.println(date.lengthOfMonth());
        System.out.println(date.lengthOfYear());
        System.out.println(date.isLeapYear());
        
        // time
        LocalTime time = LocalTime.of(14, 55, 25);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
        
        
        
    }
}
