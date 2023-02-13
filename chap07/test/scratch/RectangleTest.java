package scratch;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleTest {
    private Rectangle rectangle;

    @Test
    public void answerArea() {
        rectangle=new Rectangle(new Point(5,5), new Point(15,10));
        assertEquals(rectangle.area(), 50);
    }

    @Test
    public void allowsDynamicallyChangingSize() {
        rectangle=new Rectangle(new Point(5,5));
        rectangle.setOppositeCorner(new Point(130,130));
        assertEquals(rectangle.area(), 15625);
    }
}
