import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZooTest {
    private Zoo myZoo;
    private Dog testDog;

    @BeforeEach
    void setUp() {
        // Arrange: Initialize the objects needed for the tests
        myZoo = new Zoo("Test Zoo");
        testDog = new Dog("Buddy", 3, "Brown", 15.0, "Labrador");
    }

    @Test
    void addAnimal() {
        // Act: Perform the action
        myZoo.addAnimal(testDog);

        // Assert: Verify the result
        // We check if the animal we just added is actually in the zoo
        assertEquals(testDog, myZoo.getAnimal("Buddy"), "The animal should be added to the list.");
    }

    @Test
    void removeAnimal() {
        // Arrange: Add an animal so we have something to remove
        myZoo.addAnimal(testDog);

        // Act: Remove the animal
        myZoo.removeAnimal("Buddy");

        // Assert: Verify it's gone
        assertNull(myZoo.getAnimal("Buddy"), "The animal should be null after removal.");
    }

    @Test
    void getAnimal() {
        // Arrange
        myZoo.addAnimal(testDog);

        // Act
        Animal result = myZoo.getAnimal("Buddy");

        // Assert: Check for a correct match and a non-existent match
        assertNotNull(result, "Should find the animal by name.");
        assertEquals("Buddy", result.getName(), "The names should match.");
        assertNull(myZoo.getAnimal("Ghost"), "Should return null for a name that doesn't exist.");
    }
}