package transmission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransmissionTest {
    private Transmission transmission;
    private Car car;

    @BeforeEach
    public void create() {
        car = new Car();
        transmission = new Transmission(car);
    }

    @Test
    public void remainsInDriveAfterAcceleration(){
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(35);
        assertEquals(transmission.getGear(), Gear.DRIVE);
    }

    @Test
    public void ignoresShiftToParkWithInDrive(){
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(30);

        transmission.shift(Gear.PARK);

        assertEquals(transmission.getGear(),Gear.DRIVE);
    }

    @Test
    public void allowsShiftToParkWhenNotMoving() {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(30);
        car.brakeToStop();

        transmission.shift(Gear.PARK);

        assertEquals(transmission.getGear(), Gear.PARK);
    }
}