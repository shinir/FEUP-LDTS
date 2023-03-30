package snaketest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.Speed;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeedTest {
    Speed test;
    @BeforeEach
    public void pickSpeed() {
        test = new Speed(100,1);
    }

    @Test
    public void seeSpeed() {
        assertEquals(test.getSpeed(),100);
    }

    @Test
    public void seeShownSpeed() {
        assertEquals(test.getShowSpeed(),1);
    }

    @Test
    public void changeSpeed() {
        test.setShowSpeed(1);
        assertEquals(test.getShowSpeed(),2);
        assertEquals(test.getSpeed(), 100);
    }
}
