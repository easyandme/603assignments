/**
 *  Assignment 5 - CS603
 *  Created by Max (Jialu) Xu on 11/15/2016.
 *  Passenger on a flight
 *  Passenger has first and last name with optional
 *  middle name. Also keeps track of if the passenger
 *  requires a special meal
 */

public class Passenger {

    private String fname;
    private String lname;
    private String mname;
    private boolean specialMeal;

    /**
     *  4-arg constructor used for instantiating a passenger
     *  for whom all three names have been specified.
     *  For full credit,  invoke the appropriate SET METHODS
     *  to ensure proper formatting.
     */
    public Passenger (String lname, String fname, String mname, boolean specialMeal){
        this.setLname(lname);
        this.setFname(fname);
        this.setMname(mname);
        this.specialMeal = specialMeal; // there's a set method for specialMeal though, but there's no extra logic in it
    }
    /**
     *  3-arg constructor used for instantiating a passenger
     *  for whom no middle name has been specified.
     *  Make use of your 4-arg constructor for full credit.
     */
    public Passenger (String lname, String fname, boolean specialMeal) {
        this(lname, fname, "", specialMeal);
    }

    /**
     * Static formatName(String) - static method that returns the value of the String
     * parameter with the first letter in uppercase and the remaining letters
     * in lowercase. Example, if passed "aBcD" it returns "Abcd"
     */
    public static String formatName(String name) {
        String formattedName = name;
        // if the string is not empty, format it
        if (!name.equals("")) {
            formattedName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
        return formattedName;
    }

    /**
     *  Provide mutator methods for setting the name instance variables to
     *  instance variable to the FORMATTED value of the string parameter. No values
     *  are returned
     */
    public void setFname(String firstName) {
        this.fname = formatName(firstName);
    }

    public void setMname(String middleName) {
        this.mname = formatName(middleName);
    }

    public void setLname(String lastName) {
        this.lname = formatName(lastName);
    }

    /**
     *  toString()  returns Lastname,<sp>Firstname<sp>Middlename (if exists)
     *  followed by ** special meal ** if that option has been specified
     */
    public String toString() {
        String mealInfo = this.getSpecialMeal() ? "** special meal **" : "";
        return this.getLname() + ", " + this.getFname() + (this.getMname().equals("") ? "" : " ") +
                this.getMname() + (this.getSpecialMeal() ? " " : "") + mealInfo;
    }


    // standard accessor methods
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getMname() {
        return mname;
    }

    public void setSpecialMeal(boolean meal){
        this.specialMeal = meal;
    }

    public boolean getSpecialMeal(){
        return this.specialMeal;
    }


    // testing code
    public static void main(String[] args){
        // add code to create Passenger objects using the two constructors
        // print the objects you have created
        Passenger p1 = new Passenger("xU", "mAx", true);
        Passenger p2 = new Passenger("xU", "mAx", "whAtEVer", false);
        Passenger p3 = new Passenger("X", "MAX", "m", true);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p2.getSpecialMeal());
        System.out.println(p3);
    }

}