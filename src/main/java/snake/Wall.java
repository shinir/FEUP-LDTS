package snake;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import snake.Base;

public class Wall extends Base {

    public Wall(int x, int y) {
        super(x, y);
    }

    public void draw (TextGraphics graphics) {
        graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('#')[0]);
    }
}
