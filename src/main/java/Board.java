import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private int width;
    private int height;
    private Snake baby;
    private List<Wall> walls;
    public boolean available = true;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        baby = new Snake(width / 2,height / 2);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int w = 0; w < width; w++) {
            walls.add(new Wall(w, 0));
            walls.add(new Wall(w, height - 1));
        }
        for (int i = 1; i < height - 1; i++) {
            walls.add(new Wall(0, i));
            walls.add(new Wall(width - 1, i));
        }
        return walls;
    }

    public void processKey(KeyStroke key) {

        switch (key.getKeyType()) {
            case ArrowUp -> moveSnake(baby.moveUp());
            case ArrowDown -> moveSnake(baby.moveDown());
            case ArrowLeft -> moveSnake(baby.moveLeft());
            case ArrowRight -> moveSnake(baby.moveRight());
        }

    }

    public void moveSnake(Position position) {
        if (canSnakeMove(position)) {
            baby.setPosition(position);
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#008000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        baby.draw(graphics);

        for (Wall wall : walls) {
            wall.draw(graphics);
        }
    }

    private boolean canSnakeMove(Position position) {
        if (position.getX() < 0) return false;
        else if (position.getX() > width) return false;
        else if (position.getY() < 0) return false;
        else if (position.getY() > height) return false;
        for ( Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    /*public void processKey(KeyStroke key) throws IOException{
        // if else statements to move my character
        if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(baby.moveUp());
        } else if (key.getKeyType() == KeyType.ArrowUp) {
            moveHero(baby.moveDown());
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(baby.moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(baby.moveRight());
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            available = false;
        } else if (key.getKeyType() == KeyType.EOF) {
            available = false;
        }
    }

    public void moveHero(Position position) {
        if(position.getX() > 0 || position.getX() < width || position.getY() > 0 || position.getY() < height) {
            baby.setPosition(position);
        }
        else {
            System.out.println("You went out of boundaries!");
        }
    }*/
}