package snaketest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import snake.Menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    Menu menu;
    int w = 10, h = 10;

    @BeforeEach
    public void createMenu() {
        menu = new Menu(w,h);
    }

    @Test
    public void checkValues() {
        assertEquals(menu.width, w);
        assertEquals(menu.height, h);
    }
}
