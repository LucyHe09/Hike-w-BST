import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

public class Testing {
    // Hike/ITEM CLASS ///////////////////////////////////////////////

    @Test
    @DisplayName("Hike Constructor with name, length, review TEST CASE")
    public void hikeConstructor() {
        Hike hike = new Hike("name", 1, 2);
        assertEquals("name", hike.getName());
        assertEquals(1, hike.getLength());
        assertEquals(2, hike.getReview());
        assertEquals(true, hike.equals(hike));
    }

    @Test
    @DisplayName("Hike get Name TEST CASE")
    public void hikeName() {
        Hike hike = new Hike("name", 1, 2);
        assertEquals("name", hike.getName());
    }

    @Test
    @DisplayName("Hike get Length TEST CASE")
    public void hikeLength() {
        Hike hike = new Hike("name", 1, 2);
        assertEquals(1, hike.getLength());
    }

    @Test
    @DisplayName("Hike get Review TEST CASE")
    public void hikeReview() {
        Hike hike = new Hike("name", 1, 2);
        assertEquals(2, hike.getReview());
    }

    @Test
    @DisplayName("Hike to String TEST CASE")
    public void hikeString() {
        Hike hike = new Hike("name", 1, 2);
        assertEquals("HIKE: name, 1.0 miles, (2.0/5 stars)", hike.toString());
    }

    @Test
    @DisplayName("Hike compare TEST CASE")
    public void hikeCompare() {
        Hike hike = new Hike("name", 1, 2);
        Hike greaterHike = new Hike("name2", 10, 3);
        assertEquals(0, hike.compareTo(hike));
        assertEquals(-1, greaterHike.compareTo(hike)); //larger hikes come first means negative
        assertEquals(1, hike.compareTo(greaterHike));
    }

    @Test
    @DisplayName("Hike equals TEST CASE")
    public void hikeEquals() {
        Hike hike = new Hike("name", 1, 2);
        Hike otherHike = new Hike("name2", 10, 3);
        assertEquals(true, hike.equals(hike));
        assertEquals(false, hike.equals(otherHike));
    }

    @Test
    @DisplayName("Hike hash TEST CASE")
    public void hikeHash() {
        Hike hike = new Hike("name", 1, 2);
        assertEquals(104584917, hike.hashCode());
    }

    // CollectionManager CLASS ///////////////////////////////////////////////

    @Test
    @DisplayName("Collection Manager empty constructor TEST CASE")
    public void CollectionManagerConstructor1() {
        CollectionManager test = new CollectionManager();
        assertEquals("", test.toString());
    }

    @Test
    @DisplayName("Collection Manager Scanner constructor TEST CASE") // TODO THIS
    public void CollectionManagerConstructor2() throws FileNotFoundException {
        File inFile = new File("temp.txt");

        PrintStream output = new PrintStream(inFile);

        output.println("Hike: name");
        output.println("Miles: 1.0");
        output.println("Review: 2.0");

        CollectionManager test = new CollectionManager(new Scanner(inFile));
        assertEquals("HIKE: name, 1.0 miles, (2.0/5 stars)\n", test.toString());
    }

    @Test
    @DisplayName("Collection Manager add TEST CASE")
    public void CollectionManagerAdd() {
        CollectionManager test = new CollectionManager();
        Hike hike = new Hike("name", 1, 2);

        test.add(hike);
        assertEquals("HIKE: name, 1.0 miles, (2.0/5 stars)\n", test.toString());
    }

    @Test
    @DisplayName("Collection Manager contains TEST CASE")
    public void CollectionManagerContains() {
        CollectionManager test = new CollectionManager();
        Hike hike = new Hike("name", 1, 2);
        Hike otherHike = new Hike("name2", 10, 3);

        test.add(hike);
        assertEquals(true, test.contains(hike));
        assertEquals(false, test.contains(otherHike));
    }

    @Test
    @DisplayName("Collection Manager to String TEST CASE")
    public void CollectionManagerToString() {
        CollectionManager test = new CollectionManager();
        Hike hike = new Hike("name", 1, 2);

        test.add(hike);
        assertEquals("HIKE: name, 1.0 miles, (2.0/5 stars)\n", test.toString());
    }

    @Test
    @DisplayName("Collection Manager Save TEST CASE")
    public void CollectionManagerSave() throws FileNotFoundException{
        CollectionManager test = new CollectionManager();
        Hike hike = new Hike("name", 1, 2);
        test.add(hike);
        
        File outFile = new File("temp.txt");

        PrintStream out = new PrintStream(outFile);
        test.save(out);

        Scanner scanner = new Scanner(outFile);

        CollectionManager test2 = new CollectionManager(scanner);
        //create new collection manager with the saved file

        assertEquals(test.toString(), test2.toString());
    }

    @Test
    @DisplayName("Collection Manager Filter TEST CASE") ///TODO this
    public void CollectionManagerFilter() throws FileNotFoundException {
        File inFile = new File("temp.txt");

        PrintStream output = new PrintStream(inFile);

        output.println("Hike: one");
        output.println("Miles: 5.0");
        output.println("Review: 3.0");
        output.println("Hike: three");
        output.println("Miles: 6.0");
        output.println("Review: 4.0");
        output.println("Hike: two");
        output.println("Miles: 5.0"); 
        output.println("Review: 2.0");
        output.println("Hike: four");
        output.println("Miles: 5.0"); 
        output.println("Review: 1.0");
        output.println("Hike: five");
        output.println("Miles: 6.0"); 
        output.println("Review: 5.0");
        output.println("Hike: six");
        output.println("Miles: 3.0"); 
        output.println("Review: 5.0");

        CollectionManager test = new CollectionManager(new Scanner(inFile));
        
        assertEquals("[HIKE: three, 6.0 miles, (4.0/5 stars), HIKE: five, 6.0 miles, (5.0/5 stars)]", test.filter(6).toString());
    }
}
