package scratch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BearingTest {
    @Test
    public void throwsOnNegativeNumber() {
        assertThrows(IllegalArgumentException.class, ()->{
            new Bearing(-1);
        });
    }

    @Test
    public void throwsWhenBearingTooLarge() {
        assertThrows(IllegalArgumentException.class, ()->{
            new Bearing(Bearing.MAX+1);
        });
    }

    @Test
    public void answersValidBearing(){
        assertEquals(new Bearing(Bearing.MAX).value(), Bearing.MAX);
    }

    @Test
    public void answersAngleBetweenItAndAnotherBearing() {
        assertEquals(new Bearing(15).angleBetween(new Bearing(12)), 3);
    }

    @Test
    public void angleBetweenIsNegativeWhenThisBearingSmaller() {
        assertEquals(new Bearing(12).angleBetween(new Bearing(15)), -3);
    }

}
