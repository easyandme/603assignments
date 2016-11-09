/**
 * Assignment 4 - CS603
 * Created by Max (Jialu) Xu on 11/3/2016.
 */
public class Flight {

    private String flightCode;
    private String from;
    private String to;
    private int length;

    public Flight(String flightCode, String from, String to, int length) {
        this.setFlightCode(flightCode);
        this.setFrom(from);
        this.setTo(to);
        this.length = length;
        // A series of "this.setAttribute(argument);" statements made me wonder if I can use method chaining like
        // "this.setA(a).setB(b).setC(c)", and I found the answer is yes as long as I return the object itself in the setters
    }

    // Regular Expressions tend to be dangerous because it's easy to make a mistake without knowing it,
    // but I am quite confident with a simple case like this. The start and end anchors are probably unnecessary though.
    public static String validateCode(String flightNum) {
        if (!flightNum.matches("^[a-zA-Z]{2} [0-9]{2,4}$")) {
            return "unassigned";
        } else {
            return flightNum.toUpperCase();
        }
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = validateCode(flightCode);
    }

    public void setFrom(String from) {
        this.from = from.toUpperCase();
    }

    public void setTo(String to) {
        this.to = to.toUpperCase();
    }

    public String getFlightCode() {
        return this.flightCode;
    }

    public boolean includesMeal() {
        boolean longEnough = false;
        if (this.length >= 120) {
            longEnough = true;
        }
        return longEnough;
    }

    public String toString() {
        String mealInfo;
        if (this.includesMeal()) {
            mealInfo = "Meal Included";
        } else {
            mealInfo = "No Meal";
        }
        return "Flight code: " + this.getFlightCode() + " From: " + this.from + " To: " + this.to + " Length: "
                + this.length + " minutes - " + mealInfo;
    }

    // equals() method is used here because we only want to compare the values, whereas "==" compares the references
    // the substring method is used to extract the first two letters of the flight code.
    public boolean connectsTo(Flight flight) {
        boolean connecting = false;
        if (this.to.equals(flight.from) && this.getFlightCode().substring(0, 2).equals(flight.getFlightCode().substring(0, 2))) {
            connecting = true;
        }
        return connecting;
    }

}
