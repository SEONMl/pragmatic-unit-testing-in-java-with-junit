package iloveyouboss;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreCollectionTest {
    @Test
    public void answerArithmeticMeanOfTwoNumbers(){
        // given
        ScoreCollection collection = new ScoreCollection();
        collection.add(()->4);
        collection.add(()->10);

        // when
        int actualResult = collection.arithmeticMean();

        // then
        // JUnit5 부터 assertThat deprecated
        assertEquals(actualResult, 7);
    }

}