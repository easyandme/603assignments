/**
 * Seat on a plane - represented by a row number and a seatID (String) Each seat
 * has one passenger - can assign passenger to and remove passenger from a seat
 */

public class Seat {
    private int row;
    private String seatID;
    private Passenger passenger;

    /** Seat represented by row and seat ID, such as 1A, 5C, etc. */
    public Seat(int row, String seatID) {
        this.row = row;
        this.seatID = seatID;
    }

    /** Add passenger to a seat */
    public void addPassenger(Passenger p) {
        this.passenger = p;
    }

    /** Remove passenger by setting field to null */
    public void removePassenger() {
        this.passenger = null;
    }

    /**
     *  hasSpecialMeal() returns true if seat has a passenger and that
     *  passenger has a special meal. Otherwise, false is returned.
     */
    public boolean hasSpecialMeal() {
            return this.getPassenger() != null && this.getPassenger().getSpecialMeal();
    }

    /**
     *  toString() returns the row and seat ID ("2A" for ex.)
     *  and includes a description of the passenger if there one in the seat
     */
    public String toString() {
        String passengerInfo = "";
        if (this.getPassenger() != null) {
            passengerInfo = " " + this.getPassenger().toString();
        }
        return this.getRow() + this.getSeatID() + passengerInfo;
    }


    // standard accessor and mutator methods
    public Passenger getPassenger() {
        return this.passenger;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return this.row;
    }

    public void setSeatID(String id) {
        this.seatID = id;
    }

    public String getSeatID() {
        return this.seatID;
    }

    // testing code
    public static void main(String[] args) {
        Seat s = new Seat(1, "G");
        System.out.println("seat: " + s);
        s.addPassenger(new Passenger("Jones", "Mary", "Ann", true));
        System.out.println("After adding passenger:\n" + s);
        // test hasSpecialMeal()
        System.out.println("This passenger has special meal:" + s.hasSpecialMeal());
        // add additional testing code
        s.removePassenger();
        System.out.println("Seat info after removing passenger:\n" + s);
        System.out.println("Meal Info after removing passenger:\n" + s.hasSpecialMeal());
        s.addPassenger(new Passenger("Xu", "Max", false));
        System.out.println("Seat info after adding another passenger:\n" + s);
        System.out.println("This passenger has special meal:" + s.hasSpecialMeal());
    }
}