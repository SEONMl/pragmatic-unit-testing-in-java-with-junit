package iloveyouboss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreCollectionTest {

    private ScoreCollection collection;

    @BeforeEach
    public void setup(){
        collection = new ScoreCollection();
    }

    @Test
    public void answersArithmeticMeanOfTwoNumbers() {
        // Arrange
        collection.add(()-> 5);
        collection.add(()-> 7);

        // Act
        int actualResult = collection.arithmeticMean();

        // Assert
        assertEquals(actualResult, 6);
    }

    @Test
    public void throwsExceptionWhenAddingNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            collection.add(null);
        });
    }

    @Test
    public void dealsWithIntegerOverflow() {
        collection.add(()->Integer.MAX_VALUE);
        collection.add(()->1);

        assertEquals(collection.arithmeticMean(), -1073741824); // 오버플로우 발생
    }

    @Test
    public void doseNotProperlyHandleIntegerOverflow() {
        collection.add(()->Integer.MAX_VALUE);
        collection.add(()->1);

        assertTrue(collection.arithmeticMean() < 0);
    }
}
