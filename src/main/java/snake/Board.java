package snake;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the board where the user plays
 */
public class Board extends JFrame {
    // SIZE OF THE BOARD
    private final int width;
    private final int height;

    // VARIABLES RELATED TO THE GAME
    public int points = 0;
    public BoardMenu boardMenu;
    public Snake baby;
    private List<Wall> walls;
    private List<Apple> apples = new ArrayList<>();
    int speed;
    public boolean available = true;
    public int size;
    public boolean retrieved = false;
    SoundEffect sound = new SoundEffect();

    /**
     * Constructor of the class
     * @param width Size in the X axis
     * @param height Size in the Y axis
     * @param speed Speed the snake moves
     */
    public Board(int width, int height, int speed) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.apples = createApples();
        this.speed = speed;
        baby = new Snake(Direction.UP);
        this.size = 3;
    }

    /**
     * Creates wall around the board
     * @return List with all walls
     */
    public List<Wall> createWalls() {
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

    /**
     * Makes the snake move based on the input
     * @param key User's input
     */
    public void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp : {
                baby.setDirection(Direction.UP);
                break;
            }
            case ArrowDown : {
                baby.setDirection(Direction.DOWN);
                break;
            }
            case ArrowLeft : {
                baby.setDirection(Direction.LEFT);
            }
            case ArrowRight : {
                baby.setDirection(Direction.RIGHT);
                break;
            }
            case Escape : {
                boardMenu = new BoardMenu();
                boardMenu.run();
                break;
            }
            default : {
                moveSnake();
                break;
            }
        }
    }

    /**
     * Moves the snake, and, if possible, plays sound
     * @return Boolean dependant on whether it's possible to move to the position in question
     */
    public boolean moveSnake() {
        if (canSnakeMove(baby.getHead())) {
            for (int i = 1; i <= speed; i++) {
                baby.move();
                retrieved = false;
                retrieveApples();
                if (apples.isEmpty()) createApples();
            }
            return true;
        }
        else {
            sound.inputSound("mixkit-retro-arcade-game-over-470.wav");
            retrieved = false;
            retrieveApples();
            if(apples.isEmpty()) createApples();
            return false;
        }
    }

    /**
     * Verifies if it's possible to move the snake to the wanted position
     * @param position Where the snake is going to
     * @return Boolean dependant on whether it's possible to move to the position in question
     */
    private boolean canSnakeMove(Position position) {

        for(int i = 0; i < baby.getBody().size() - 1; i++)
            if (baby.getBody().get(i).equals(baby.getHead()))
                return false;
        for (Wall wall : walls) {
            if (wall.getPosition().equals(baby.getHead()))
                return false;
        }
        if (position.getX() <= 0 || position.getX() >= width)
            return false;
        else return position.getY() > 0 || position.getY() < height;
    }

    /**
     * Places apple on board
     * @return List with all apples
     */
    public List<Apple> createApples() {
        Random random = new Random();
        apples.add(new Apple(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return apples;
    }

    /**
     * Checks if snake eats apple and, if so, removes it from board and plays sound
     */
    public void retrieveApples() {
        for(Apple apple : apples)
            if(apple.getPosition().equals(baby.getHead())) {
                apples.remove(apple);
                baby.increase();
                points++;
                retrieved = true;
                sound.inputSound("The_Office_US_Kevin_is_Cookie_Monster_Trim (online-audio-converter.com).wav");
                break;
            }
    }

    /**
     * Draws board
     */
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#45733C"));
        baby.drawSnake(graphics);

        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for(Apple apple : apples)
            apple.draw(graphics);
    }

    /**
     * Getter for speed
     * @return Speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Getter for width, overriden from JFrame
     * @return Width
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Getter for height, overriden from JFrame
     * @return Height
     */
    @Override
    public int getHeight() {
        return height;
    }
}