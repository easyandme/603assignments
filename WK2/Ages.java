/**
 * Problem 1 - CS603
 * Jialu (Max) Xu
 * created on 9/9/16
 */

import java.util.Scanner;

public class Ages {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        final int    MONTHSPERYEAR  = 12;      // define months per year
        final double DAYSPERMONTH   = 30.44;   // define average days per month
        final int    HOURSPERDAY    = 24;      // define hours per day
        final int    MINUTESPERHOUR = 60;      // define minutes per hour
        final int    SECONDSPERMIN  = 60;      // define seconds per minute

        System.out.print("This program reads in a name and age in years and calculates the equivalent age in months, days, hours, minutes, and seconds. \n \nEnter a name: ");

        String name = kb.nextLine();           // input name

        System.out.print("Enter " + name + "'s age in years, including decimal values: ");

        double age = kb.nextDouble();          // input age

        /* self-explanatory calculations to get the whole numbers */
        int    wholemonths  = (int) (MONTHSPERYEAR * age);
        double decimalmonth = MONTHSPERYEAR * age - wholemonths;
        int    wholedays    = (int) (DAYSPERMONTH * decimalmonth);
        double decimalday   = DAYSPERMONTH * decimalmonth - wholedays;
        int    wholehours   = (int) (HOURSPERDAY * decimalday);
        double decimalhour  = HOURSPERDAY * decimalday - wholehours;
        int    wholemins    = (int) (MINUTESPERHOUR * decimalhour);
        double decimalmin   = MINUTESPERHOUR * decimalhour - wholemins;
        int    wholesecs    = (int) Math.round(SECONDSPERMIN * decimalmin);

        System.out.println(name + " has been alive for approximately " + wholemonths + " month(s), " + wholedays + " day(s), " + wholehours + " hour(s), " + wholemins + " minute(s) and " + wholesecs + " second(s).");

        kb.close();
    }

}
