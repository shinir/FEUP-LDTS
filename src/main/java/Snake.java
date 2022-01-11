import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Snake extends Base {

    public Snake(int x, int y) {
        super(x,y);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#03AC13"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "x");
    }

    public Position moveDown() {
        setPosition(new Position(position.getX(),position.getY() + 1));
        return new Position(position.getX(),position.getY());
    }

    public Position moveUp() {
        setPosition(new Position(position.getX(),position.getY() - 1));
        return new Position(position.getX(),position.getY());
    }

    public Position moveLeft() {
        setPosition(new Position(position.getX() - 1,position.getY()));
        return new Position(position.getX(),position.getY());
    }

    public Position moveRight() {
        setPosition(new Position(position.getX() + 1,position.getY()));
        return new Position(position.getX(),position.getY());
    }
}
