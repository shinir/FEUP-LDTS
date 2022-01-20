package snake;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Object Apple is placed in Board to be eaten by snake
 */
public class Apple extends Base {
    /**
     * Constructor of class
     * @param i Position in X axis
     * @param j Position in Y axis
     */
    public Apple(int i, int j) { super(i,j); }

    /**
     * Draws apple in board
     */
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#CA002A"));
        graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('o')[0]);
    }
}
