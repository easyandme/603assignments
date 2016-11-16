/**
 *  Assignment 5 - CS603
 *  Created by Max (Jialu) Xu on 11/15/2016.
 *  Flight class - each flight has a flight number, departure and
 *  arrival cities, a list of seats, and a number of rows,
 *  with 5 seats per row.
 *
 *  The main method includes code for adding seats to a flight using numbers
 *  for the row and letters for the seat locations (such as 1A, 1B, 2A for
 *  seats in row 1 locations A and B, row 2 location A, etc.)
 */
import java.util.Arrays;

public class Flight {
    private String flightNumber;
    private String departure;
    private String arrival;
    private int numSeats; // maximum number of seats on flight
    private Seat[] seats;
    private int numAddedSeats; // actual number of seats added to flight

    /**
     *  4-arg constructor for setting flight number, departure and arrival locations,
     *  and the maximum number of seats on the plane, which is also used for initializing the
     *  size of the array representing seats on the plane
     */
    public Flight(String flightNumber, String departure, String arrival, int numSeats){
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.numSeats = numSeats;
        this.seats = new Seat[this.numSeats];

    }

    /** add seat to array of seats if have room in array */
    public void addSeat(Seat s){
        if (this.numAddedSeats < this.numSeats){
            this.seats[this.numAddedSeats++] = s;
        }
    }

    /**
     *  getSpecialSeats() returns an array containing the index values
     *  of all seats in the Seat array that have passengers getting special
     *  meals. The size of this array must be EXACTLY equal to the number seats
     *  on the plane in which passengers have special meals.
     */
    public int[] getSpecialSeats() {
        // Using this.numAddedSeats for the length of the specialSeats array is better than
        // using this.numSeats or even this.getSeats().length here, since there will be no 'special seats'
        // if we don't add seats first and then assign passengers to them.
        int[] specialSeats = new int[this.numAddedSeats];
        int   k = 0;
        for (int i = 0; i < this.numAddedSeats; i++) {
            // If I use something else for the length, I may have to add something like 'this.getSeats()[i] != null &&'
            // in the following condition to prevent a potential NullPointerException.
            if (this.getSeats()[i].hasSpecialMeal()) {
                specialSeats[k] = i;  // fill index values
                k++;
            }
        }
        return Arrays.copyOfRange(specialSeats, 0, k);  // return a new array with indexes of special seats only
    }

    public String toString(){
        return "Flight " + this.flightNumber + " from " + this.departure + " to "
                + this.arrival;
    }

    // standard accessor and mutator methods
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Seat[] getSeats(){
        return this.seats;
    }

    public void setSeats(Seat[] seats){
        this.seats = seats;
    }


    // Testing code
    public static void main(String[] args){
        Flight f = new Flight("1544", "BOS Boston", "AUS Austin", 10);
        System.out.println(f); // print flight into

        // add 3 seats to the flight
        Seat s1 = new Seat(1, "A");
        Seat s2 = new Seat(1, "B");
        Seat s3 = new Seat(2, "E");
        f.addSeat(s1);
        f.addSeat(s2);
        f.addSeat(s3);

        // add passengers to seats
        Passenger p1 = new Passenger("Jones", "Mary", true);
        Passenger p2 = new Passenger("Smith", "John", false);
        Passenger p3 = new Passenger("Henderson", "Jill", true);
        s1.addPassenger(p1);
        s2.addPassenger(p2);
        s3.addPassenger(p3);

        // add code to print seat indices for seats with special meals of f
        System.out.println("Index values of the special seats: " + Arrays.toString(f.getSpecialSeats()));

        // additional test with f2
        Flight f2 = new Flight("456", "BOS Boston", "CTS Sapporo", 20);
        Seat s21 = new Seat(1, "C");
        Seat s22 = new Seat(2, "D");
        Seat s23 = new Seat(3, "E");
        Seat s24 = new Seat(3, "F");
        f2.addSeat(s21);
        f2.addSeat(s22);
        f2.addSeat(s23);
        f2.addSeat(s24);
        Passenger p21 = new Passenger("Xu", "Max", true);
        Passenger p22 = new Passenger("A", "Baby", true);
        Passenger p23 = new Passenger("B", "Puppy", false);
        Passenger p24 = new Passenger("C", "Stranger", "Total", true);
        s21.addPassenger(p21);
        s22.addPassenger(p22);
        s23.addPassenger(p23);
        s24.addPassenger(p24);
        System.out.println(f2);
        System.out.println("Index values of the special seats: " + Arrays.toString(f2.getSpecialSeats()));
    }
}