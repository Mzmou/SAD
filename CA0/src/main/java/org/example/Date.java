package org.example;

import java.util.Objects;

public class Date implements Comparable <Date>  {
    private final int day;
    private final int month;
    private final int year;
    private static final int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public Date(int day, int month, int year) {
        if (year < 0 || month < 1 || month > 12 || day < 1 || day > daysOfMonth(month, year))
        {

            throw new IllegalArgumentException("Invalid date");
        }


        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date nextDay() {
        int d = day;
        int m = month;
        int y = year;

        d++;
        if (d > daysOfMonth(m, y)) {
            d = 1;
            m++;
            if (m > 12) {
                m = 1;
                y++;
            }
        }
        return new Date(d, m, y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    private static int daysOfMonth(int month, int year) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Invalid value for month");
        if (month < 7)
            return 31;
        else if (month < 12)
            return 30;
        else
            return isLeapYear(year) ? 30 : 29;
    }
    private static boolean isLeapYear_gregory(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    private static boolean isLeapYear(int year) {
        int r = year % 33;
        return r==1 || r==5 || r==9 || r==13 || r==17 || r==22 || r==26 || r==30;
    }
    @Override
    public int compareTo(Date other) {
        int year_comparison = Integer.compare(this.year, other.year);
        if (year_comparison != 0) {
            return year_comparison;
        }
        int month_comparison = Integer.compare(this.month, other.month);
        if (month_comparison != 0) {
            return month_comparison;
        }
        return Integer.compare(this.day, other.day);



    }
    public int calculate_range_days(Date start) {
        if (start.compareTo(this) > 0) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        int days = 0;
        Date temp = start;

        while (temp.compareTo(this) < 0) {
            temp = temp.nextDay();
            days++;
        }

        return days+1;
    }
    public  int get_day(){return  this.day;}
    public int get_month(){return this.month;}
    public int get_year(){return  this.year;}

    public int calculate_range_days_gregory(Date start) {
        if (start.compareTo(this) > 0) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        int days_between=0;
        var start_day=start.get_day();
        var start_month=start.get_month();
        var start_year=start.get_year();
        var start_month_length=daysInMonth[start_month];
        var end_month_length=daysInMonth[this.month];
        days_between+=start_month_length-start_day+1;
        days_between+=this.day;
        if(isLeapYear_gregory(this.year ) && start_month==2)
        {
            days_between+=1;
        }
        if(start_year==this.year){


            for(int i=start_month+1;i<this.month;i++){
                days_between+=daysInMonth[i];
                if(i==2 && isLeapYear_gregory(this.year))
                    days_between+=1;
            }
            System.out.println("hi:"+days_between);
            return days_between;
        }
     else{
         for (int year=start_year+1;year<this.year;year++){
             days_between+= isLeapYear_gregory(year) ? 366 : 365;
         }
            for(int i=start_month+1;i<=12;i++){
                days_between+=daysInMonth[i];
            }

         return days_between;
        }








    }
}
