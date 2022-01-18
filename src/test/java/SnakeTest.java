import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeTest {
    Snake snake;

    @BeforeEach
    public void createSnake() {
        snake = new Snake(Direction.UP);
    }

    @Test
    public void testDown() {
        int ogY = snake.getHead().getY();
        assertEquals(ogY + 1, snake. moveDown().getY());
    }

    @Test
    public void testUp() {
        int ogY = snake.getHead().getY();
        assertEquals(ogY - 1, snake.moveUp().getY());
    }

    @Test
    public void testLeft() {
        int ogX = snake.getHead().getX();
        assertEquals(ogX - 1, snake.moveLeft().getX());
    }

    @Test
    public void testRight() {
        int ogX = snake.getHead().getX();
        assertEquals(ogX + 1, snake.moveRight().getX());
    }

    @Test
    public void testIncrease() {
        int ogSize = snake.size;
        snake.increase();
        assertEquals(++ogSize, snake.size);
    }
}