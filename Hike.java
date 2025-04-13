// Hike represents a hike object with hike-specific features such as
// a name, length in miles, and a review on a scale of 5
// Hike implements the Comparable<Hike> interface

import java.util.Scanner;

public class Hike implements Comparable<Hike> {
    private final String name;
    private final double length; // in miles
    private final double review; // on scale of 5

    // Behavior:
       //   Constructs a Hike object with the specified name, length, and review
    // Parameters:
       //   String name - the name of the hike
       //   double length - the length of the hike
       //   double review - the review score of the hike
    // Exceptions:
       // throws an IllegalArgumentException if review is less than 0, greater than 5,
       // length is less than 0, or name is null
    public Hike(String name, double length, double review) {
        if (review < 0 || review > 5 || length < 0 || name == null) {
            throw new IllegalArgumentException();
        }     
            
        this.name = name;
        this.length = length;
        this.review = review;
    }

    // Behavior:
       //   gets the name of the hike
    // Returns:
       //   String name - the name of the hike
    public String getName(){
        return this.name;
    }

    // Behavior:
       //   gets the length of the hike
    // Returns:
       //    double length - the length of the hike
    public double getLength(){
        return this.length;
    }

    // Behavior:
       //   gets the review score of the hike
    // Returns:
       //   double review - the review score of the hike
    public double getReview(){
        return this.review;
    }
   
    // Behavior:
       //   Creates a new hike object based on a user-specified name, length, and review
    // Parameters:
       //   Scanner input - used to read user input
    // Returns:
        //  Hike object with the provided attributes from user input
    // Exceptions:
       // throws an IllegalArgumentException if input is null
    public static Hike parse(Scanner input) {
        if(input == null){
            throw new IllegalArgumentException("input is null");
        }

        System.out.print("What is the name of your hike? ");
        String name = input.nextLine();

        System.out.print("What is the length of your hike in miles? ");
        double length = Double.parseDouble(input.nextLine());

        System.out.print("What is the review of your hike (out of five stars)? ");
        double review = Double.parseDouble(input.nextLine());

        return new Hike(name, length, review);
    }

    // Behavior:
       //   Creates a String representation of the hike object in the following format
       //   HIKE: [name], [length] miles, ([review]/5 stars)
    // Returns:
       //   String representation of the hike object
    public String toString() {
        return "HIKE: " + this.name + ", " + this.length + " miles, (" 
                                          + this.review + "/5 stars)";
    }

    // Behavior:
       //   Compares this Hike object to another Hike object by length, review, then name
    // Parameters:
       //   Hike other - the other hike object to compare to this hike object
    // Returns:
       //   an integer, negative zero or positive as this object is 
       //   less than, equal to, or greater than the specified object respectively
    public int compareTo(Hike other) {
        if (this.length != other.length) {
            return Double.compare(other.length, this.length);
        } else if (this.review != other.review){
            return Double.compare(other.review, this.review);
        } else {
            return this.name.compareTo(other.name);
        }
    }

    // Behavior:
       //   Checks if this Hike object is equal to another Hike object
    // Parameters:
       //   Object o - the other object to compare to this hike object
    // Returns:
       //   a boolean, true if the objects are equal, false otherwise
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Hike) {
            Hike otherHike = (Hike) o;
            return this.name.equals(otherHike.name)
                    && this.length == otherHike.length
                    && this.review == otherHike.review;
        } else {
            return false;
        }
    }

    // Behavior:
       //   Creates a hash code value for this Hike object
    // Returns:
       //   an int hash code value for this Hike object
    public int hashCode() {
        return 31 * this.name.hashCode();
        //can't use for fields that are doubles?
    }
}