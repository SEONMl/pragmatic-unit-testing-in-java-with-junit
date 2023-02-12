package iloveyouboss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
