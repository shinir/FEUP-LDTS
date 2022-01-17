import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Board extends JFrame {
    // SIZE OF THE BOARD
    private final int width;
    private final int height;

    // VARIABLES RELATED TO THE GAME
    private int points = 0;
    public BoardMenu boardMenu;
    public Snake baby;
    private List<Wall> walls;
    private List<Apple> apples = new ArrayList<>();
    int speed = 1;
    public boolean available = true;
    public int size;
    public boolean retrieved = false;


    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.apples = createApples();
        baby = new Snake(Direction.UP);
        this.size = 3;
    }

    private List<Wall> createWalls() {
        walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    public void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp -> baby.setDirection(Direction.UP);
            case ArrowDown -> baby.setDirection(Direction.DOWN);
            case ArrowLeft -> baby.setDirection(Direction.LEFT);
            case ArrowRight -> baby.setDirection(Direction.RIGHT);
            case Escape -> {
                boardMenu = new BoardMenu();
                boardMenu.run();
            }
        }
    }

    public void moveSnake() throws IOException {
        if (canSnakeMove(baby.getHead()) ) {
            baby.move();
        }
        else {
            boardMenu = new BoardMenu();
            boardMenu.gameOverMenu(points);
        }
        retrieved = false;
        retrieveApples();
        if(apples.isEmpty()) createApples();
    }

    private boolean canSnakeMove(Position position) {
        for(Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        if (position.getX() <= 0 || position.getX() >= width)
            return false;
        else return position.getY() > 0 || position.getY() < height;
    }

    private List<Apple> createApples() {
        Random random = new Random();
        apples.add(new Apple(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return apples;
    }

    private void retrieveApples() {
        for(Apple apple : apples)
            if(apple.getPosition().equals(baby.getHead())) {
                apples.remove(apple);
                baby.increase();
                points++;
                retrieved = true;
                break;
            }
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#45733C"));
        drawSnake(graphics);

        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for(Apple apple : apples)
            apple.draw(graphics);
    }

    public void drawSnake(TextGraphics graphics) {
        Position head = baby.getHead();

        for(Position pos : baby.getBody()) {
            if(!pos.equals(head)) {
                graphics.putString(pos.getX(), pos.getY(), "o");
            }
            else {
                graphics.putString(pos.getX(), pos.getY(), "*");
            }
        }
    }
}