// CollectionManager represents a collection of Hike objects,
// organized by length, review score, and name

import java.io.*;
import java.util.*;

public class CollectionManager {
    private HikeNode overallRoot;

    // Behavior:
       //   Constructs a new CollectionManager with no Hike objects
    public CollectionManager(){
        overallRoot = null;
    }

    // Behavior:
       //   Constructs a new CollectionManager with a provided file with the formatting
       //   Hike: [Hike]
       //   Miles: [Miles]
       //   Review: [Review]
       //   Matches the output format of save(PrintStream output)
       //   CollectionManager(Scanner input) reads in hike objects using
       //   pre-order traversal to create nodes
    // Parameters:
       //   Scanner input - scans provided file
    // Exceptions:
       // throws an IllegalArgumentException if input is null
    public CollectionManager(Scanner input){
        if(input == null){
            throw new IllegalArgumentException("input is null");
        }

        while(input.hasNextLine()){
            createFromScanner(input);
        }
    }

    // Behavior:
       //   Constructs a new CollectionManager by creating a 
       //   binary search tree and using the add() method to add each new hike object to the tree
    // Parameters:
       //   Scanner input - scans provided file
    private void createFromScanner(Scanner input){
        if(input.hasNextLine()){
            String hikeLine = input.nextLine();

            if (hikeLine.startsWith("Hike: ")) {
                String lengthLine = input.nextLine();
                String reviewLine = input.nextLine();

                String name = hikeLine.substring("Hike: ".length());
                double length = Double.parseDouble(lengthLine.substring
                                                        ("Miles: ".length()));
                double review = Double.parseDouble(reviewLine.substring
                                                ("Review: ".length()));
                Hike newHike = new Hike(name, length, review);

                this.add(newHike);
            }
        }
        //same as not returning anything
    } 

    // Behavior:
       //   adds a hike object to the CollectionManager
    // Parameters:
       //   Hike hike - hike to add to the collection
    // Exceptions:
       // throws an IllegalArgumentException if Hike object hike is null
       // throws an IllegalArgumentException if collection already contains hike
    public void add(Hike hike){
        if(hike == null){
            throw new IllegalArgumentException("hike is null");
        }
        if(this.contains(hike)){
            throw new IllegalArgumentException("hike already exists");
        }
        overallRoot = add(hike, overallRoot);
    }

    // Behavior:
       //   adds a hike object to the CollectionManager
       //   if hike object is "less" than the current HikeNode,
       //   it will be placed to the left
       //   if hike object is "greater" than the current HikeNode,
       //   it will be placed to the right
    // Parameters:
       //   Hike hike - hike to add to the collection
       //   HikeNode curr to track nodes in the tree and to designate 
       //   where HikeNodes will be added
    // Returns:
       // HikeNode that will be returned to the tree
    private HikeNode add(Hike hike, HikeNode curr){
        if (curr == null) {
            return new HikeNode(hike);

        } else{
            int compare = hike.compareTo(curr.hike);
    
            if (compare < 0) {
                // lesser, left subtree
                curr.left = add(hike, curr.left);

            } else { //if (0 < compare) 
                // greater, right subtree
                curr.right = add(hike, curr.right);
            }
        }
        return curr;
    }

    // Behavior:
       //  checks if there is a specified hike in the collection
    // Parameters:
       //   Hike hike - hike to check if within the collection
    // Returns:
        // a boolean, true if the collection includes the hike, false otherwise
    // Exceptions:
       // throws an IllegalArgumentException if Hike object hike is null
    public boolean contains(Hike hike){
        if(hike == null){
            throw new IllegalArgumentException("hike is null");
        }

        return contains(hike, overallRoot);
    }

    // Behavior:
       //  checks if there is a specified hike in the collection
       //   by recursively checking the most efficient pathway in the tree
       //   based on the compareTo() method in the Hike class
       //   if hike object is "less" than the current HikeNode,
       //   it will be searched for on the left
       //   if hike object is "greater" than the current HikeNode,
       //   it will be searched for on the right
    // Parameters:
       //   Hike hike - hike to check if contained in the collection
       //   HikeNode curr to track nodes in the tree and to designate 
       //   where is being searched
    // Returns:
       // a boolean, true if the collection includes the hike, false otherwise
    private boolean contains(Hike hike, HikeNode curr){
        if (curr == null) {
            return false;
        } else if (hike.equals(curr.hike)) {
            return true;
        } else if (curr.hike.compareTo(hike) > 0) {
            return contains(hike, curr.left);
        } else {
            return contains(hike, curr.right);
        }
    } 

    // Behavior:
       //   Creates a String representation of the CollectionManager object, 
       //   with all Hike objects in the collection listed line by line
       //   Format: "HIKE: [name], [length] miles, ([review]/5 stars)"
       //   toString uses in-order traversal to print HikeNodes from
       //   greatest in length, review, or lexicographically first
    // Returns:
       //   String representation of the CollectionManager object
    public String toString() {
        return printOrder(overallRoot);
    }

    // Behavior:
       //   Creates a String representation of the CollectionManager object, 
       //   with all Hike objects in the collection listed line by line
       //   Uses in-order binary tree traversal
    // Parameters:
       //   HikeNode curr to track nodes in the tree and to designate 
       //   which HikeNode to print
    // Returns:
       //   String representation of the CollectionManager object
    private String printOrder(HikeNode curr) { //in Order
        if (curr != null) {
            return printOrder(curr.left) + curr.hike.toString() +  "\n" +
            printOrder(curr.right);
        }

        return "";
    }
    
    // Behavior:
       //   Saves a CollectionManager to a file with a user-specified name with the formatting
       //   Hike: [Hike]
       //   Miles: [Miles]
       //   Review: [Review]
       //   Matches the input format of CollectionManager(Scanner input) to
       //   write hike objects to a file using pre-order traversal
    // Parameters:
       //   PrintStream output - to output to a file
    // Exceptions:
       // throws an IllegalArgumentException if output is null
    public void save(PrintStream output){
        if(output == null){
            throw new IllegalArgumentException("output is null");
        }

        save(output, overallRoot);
    }
    
    // Behavior:
       //   Saves a CollectionManager to a file with a user-specified name
       //   by recursively going through the tree in a pre-order fashion
       //   Matches the input format of CollectionManager(Scanner input) to
       //   write hike objects to a file using pre-order traversal
    // Parameters:
       //   PrintStream output - to output to a file
       //   HikeNode curr to track nodes in the tree and to designate 
       //   which HikeNode to output to the file
    private void save(PrintStream output, HikeNode curr){
        if(curr != null){
            output.println("Hike: " + curr.hike.getName());
            output.println("Miles: " + curr.hike.getLength());
            output.println("Review: " + curr.hike.getReview());

            save(output, curr.left);
            save(output, curr.right);
        }
    }

    // Behavior:
       //   shows the user a list of hikes out of the collection that are the length chosen
    // Parameters:
       //   double chosenLength - the preferred length of the hike
    // Returns:
        // a List<Hike> including any hikes that satisfy the required length
        // returns an empty list if no hikes that are the length are in the collection
    // Exceptions:
       // throws an IllegalArgumentException if chosen length is negative
    public List<Hike> filter(double chosenLength){
        if(chosenLength < 0){
            throw new IllegalArgumentException("chosen length is negative");
        }

        return filter(new ArrayList<>(), chosenLength, overallRoot);
    }

    // Behavior:
       //   shows the user a list of hikes out of the collection that are the length they chose
       //   recursively goes through the tree to find any hikes that are the specified length
    // Parameters:
       //   List<Hike> chosen - list of hikes that gets added to when any hikes are found
       //   double chosenLength - the preferred length of the hike
       //   HikeNode curr to track nodes in the tree and to designate 
       //   which HikeNode is being checked
    // Returns:
        // a List<Hike> including any hikes that satisfy the required length
        // returns an empty list if no hikes that are the length are in the collection
    private List<Hike> filter(List<Hike> chosen, double chosenLength, HikeNode curr){
        if (curr != null) {
            //hits base case when gone through entire tree
            if (curr.hike.getLength() == chosenLength) {
                chosen.add(curr.hike);
            } 

            filter(chosen, chosenLength, curr.left);
            filter(chosen, chosenLength, curr.right);
        } 
        return chosen;
    }

    //HikeNode represents Hike object within a tree with left and right references

    private static class HikeNode {
        public final Hike hike;
        public HikeNode left;
        public HikeNode right;

        // Behavior:
            //   Constructs a HikeNode object with the specified Hike object 
            //    with a null left and right reference
        // Parameters:
            //  Hike object - the Hike object it represents
        public HikeNode(Hike hike) {
            this(hike, null, null);
        }

        // Behavior:
            //   Constructs a HikeNode object with the specified Hike object 
            //  and left and right reference
        // Parameters:
            //  Hike object - the Hike object it represents
            //  HikeNode object - the Hike object it references to the left
            //  HikeNode object - the Hike object it references to the right
        public HikeNode(Hike hike, HikeNode left, HikeNode right) {
            this.hike = hike;
            this.left = left;
            this.right = right;
        }
    }
}