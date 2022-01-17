import com.googlecode.lanterna.graphics.TextGraphics;

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