import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(int i, int j) { super(i,j);}

    public void draw(TextGraphics graphics) {
        graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('u')[0]);
    }

    public Position move(int range) {
        if(range == 1) return new Position(position.getX(),position.getY() + 1);
        else if(range == 2) return new Position(position.getX(),position.getY() - 1);
        else if(range == 3) return new Position(position.getX() + 1,position.getY());
        else if(range == 4) return new Position(position.getX() - 1,position.getY());
        return new Position(position.getX(),position.getY());
    }
}