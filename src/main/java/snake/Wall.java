package snake;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Wall placed around the board
 */
public class Wall extends Base {

    /**
     * Constructor of the class
     * @param x Position in X axis
     * @param y Position in Y axis
     */
    public Wall(int x, int y) {
        super(x, y);
    }

    /**
     * Draws wall in specific position of board
     */
    public void draw (TextGraphics graphics) {
        graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('▒')[0]);
    }
}
