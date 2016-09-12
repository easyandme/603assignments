/**
 * Problem 2 - CS603
 * Created by Jialu (Max) XU
 * on 2016/9/11.
 */

import java.util.Scanner;

public class LongDistanceCall {

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        final int    NIGHTBEGINS      = 18;           // night time discount beginning time
        final int    NIGHTENDS        =  8;           // night time discount ending time
        final int    LONGENOUGH       = 60;           // long enough (>=60min) to get discount
        final double REGRATE          = .4;           // regular rate
        final double NIGHTDISCOUNT    = .5;           // night time discount
        final double LONGCALLDISCOUNT = .15;          // long call discount
        final double FEDERALTAX       = .04;          // Federal Tax

        System.out.print("This program reads in the start time and the length in minutes for a phone call.\nIt prints the gross cost and the net cost after discounts and taxes. \n\nEnter the starting hour of the call using a 24-hour clock (0-23): ");
        int startinghr = kb.nextInt();                // get starting hour

        System.out.print("Enter the length of the call in minutes: ");
        double lengthofcall = kb.nextDouble();        // get length of call

        double grosscost = REGRATE * lengthofcall;    // gross cost
        double netcost   = grosscost;                 // initialize net cost

        if (startinghr >= NIGHTBEGINS || startinghr < NIGHTENDS) {
            netcost *= (1 - NIGHTDISCOUNT);           // subtract night time discount
        }
        if (lengthofcall > LONGENOUGH) {
            netcost *= (1 - LONGCALLDISCOUNT);        // subtract long call discount
        }
        netcost *= (1 + FEDERALTAX);                  // apply Federal Tax

        /* round currency values to 2 decimals (not perfectly accurate though) */
        System.out.println("Gross cost: $" + (double)Math.round(grosscost * 100d) / 100d);
        System.out.println("Net cost after discount and tax: $" + (double)Math.round(netcost * 100d) / 100d);

        kb.close();
    }

}