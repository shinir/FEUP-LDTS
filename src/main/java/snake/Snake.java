package snake;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.LinkedList;

/**
 * Snake controlled by the user
 */
public class Snake {
    // SIZE OF THE SNAKE
    public int size = 3;

    // SNAKE POSITION
    // Initial position will be the center of the screen
    public LinkedList<Position> body;
    private Direction way;

    // IF TRUE IS ALIVE, ELSE IS DEAD
    public boolean status;

    /**
     * constructor of the class
     * @param start Direction to where the snake is moving
     */
    public Snake(Direction start) {
        body = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            body.add(new Position(i + 3, 15));
        }
        way = start;
        status = true;
    }

    /**
     * Draws snake into the board
     */
    public void drawSnake(TextGraphics graphics) {
        Position head = getHead();

        for(Position pos : getBody()) {
            if(!pos.equals(head)) {
                graphics.putString(pos.getX(), pos.getY(), "o");
            }
            else {
                graphics.putString(pos.getX(), pos.getY(), "*");
            }
        }
    }

    /**
     * Moves snake to direction currently selected
     */
    public void move() {
        getHead();
        Position head;
        body.removeFirst();

        switch (way) {
            case UP : {
                head = moveUp();
                break;
            }
            case DOWN : {
                head = moveDown();
                break;
            }
            case RIGHT : {
                head = moveRight();
                break;
            }
            case LEFT : {
                head = moveLeft();
                break;
            }
            default : {
                throw new IllegalArgumentException("snake.Direction unavailable");
            }
        }
        body.addLast(head);
    }

    /**
     * Moves snake downwards
     * @return Current position of snake's head
     */
    public Position moveDown() {
        return new Position(getHead().getX(), getHead().getY() + 1);
    }
    /**
     * Moves snake upwards
     * @return Current position of snake's head
     */
    public Position moveUp() {
        return new Position(getHead().getX(), getHead().getY() - 1);
    }
    /**
     * Moves snake to the left
     * @return Current position of snake's head
     */
    public Position moveLeft() {
        return new Position(getHead().getX() - 1, getHead().getY());
    }
    /**
     * Moves snake to the right
     * @return Current position of snake's head
     */
    public Position moveRight() { return new Position(getHead().getX() + 1, getHead().getY()); }

    /**
     * Increases snake's size when it eats an apple
     */
    public void increase() {
        size++;
        Position tail = getTail();
        body.addFirst(new Position(tail.getX(), tail.getY()));
    }

    /**
     * Getter for linked list with snake's body
     * @return Linked list containing all positions of the body
     */
    public LinkedList<Position> getBody() {
        return body;
    }
    /**
     * Getter for snake's head
     * @return Position of snake's head
     */
    public Position getHead() {
        return body.getLast();
    }
    /**
     * Getter for snake's tail
     * @return Position of snake's tail
     */
    public Position getTail() {
        return body.getFirst();
    }

    /**
     * Setter for direction of snake's movement
     */
    public void setDirection(Direction direction) { this.way = direction; }
}