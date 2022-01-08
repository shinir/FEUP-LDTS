import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Apple extends Base{
    public Apple(int i, int j) { super(i,j); }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#CA002A"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#a61919"));
        graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('o')[0]);
    }
}
