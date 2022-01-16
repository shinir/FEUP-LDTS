import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    Position here;

    @BeforeEach
    public void creatPosition() { here = new Position(0,0); }

    @Test
    public void xGet() {
        assertEquals(0, here.getX());
    }

    @Test
    public void yGet() {
        assertEquals(0, here.getY());
    }
}
