package snaketest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    Position here, there;

    @BeforeEach
    public void createPosition() {
        here = new Position(0,0);
        there = new Position(1,0);
    }

    @Test
    public void xGet() { assertEquals(0, here.getX()); }

    @Test
    public void yGet() {
        assertEquals(0, here.getY());
    }
}
