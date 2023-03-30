package snake;

import com.googlecode.lanterna.graphics.TextGraphics;

/**
 * Superclass of Apple and Wall
 */
abstract class Base {
    protected Position position;

    public Base (int i, int j) {
        position = new Position(i,j);
    }

    public Position getPosition () {
        return position;
    }

    abstract void draw(TextGraphics graphics);
}