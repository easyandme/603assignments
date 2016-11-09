/**
 * Assignment 4 - CS603
 * Created by Max (Jialu) Xu on 11/3/2016.
 */

/* In this program, the user is prompted to enter information of two flights,then it gives the user
 * whether in-flight meals are included and whether these two flights are connected.
 */

import java.util.Scanner;

public class AddFlights {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        Flight[] flightArray = new Flight[2];             // create an array of two Flight objects

        for(int i = 0; i < flightArray.length; i++) {

            String flightCode;
            String departureCity;
            String arrivalCity;
            int flightDuration;

            System.out.println("Please enter a flight code that begins with two letters followed by " +
                    "a space and then 2 to 4 digits: ");
            flightCode = kb.nextLine();

            System.out.println("Enter the departure city: ");
            departureCity = kb.nextLine();

            System.out.println("Enter the arrival city: ");
            arrivalCity = kb.nextLine();

            System.out.println("Enter the flight duration in minutes: ");
            flightDuration = kb.nextInt();
            kb.nextLine();

            // create a myFlight instance of Flight class
            Flight myFlight = new Flight(flightCode, departureCity, arrivalCity, flightDuration);
            System.out.println(myFlight);
            System.out.println();

            // equals() method is used here because we only want to compare the values, whereas "==" compares the references
            while (myFlight.getFlightCode().equals("unassigned")) {
                    System.out.println("The flight code for the newly created flight is invalid. Please re-enter: ");
                    flightCode = kb.nextLine();
                    if (!Flight.validateCode(flightCode).equals("unassigned")) {
                        // if the newly entered code is syntax valid, set it as the flightCode
                        myFlight.setFlightCode(flightCode);
                        // I realized that I don't need a boolean variable here when you mentioned it in the office.
                        System.out.println(myFlight);
                    }
            } // End of inner loop

            flightArray[i] = myFlight;     // store the new object into the array

        } // End of loop

        if (flightArray[0].connectsTo(flightArray[1])) {
            System.out.println("\nThe two flights are connecting.");
        } else {
            System.out.println("\nThe two flights are not connecting.");
        }


        kb.close();
    }

}
