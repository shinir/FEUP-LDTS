import java.util.LinkedList;

public class Snake {
    // SIZE OF THE SNAKE
    public int size = 3;

    // SNAKE POSITION
    // Initial position will be the center of the screen
    public LinkedList<Position> body;
    private Direction way;

    // IF TRUE IS ALIVE, ELSE IS DEAD
    public boolean status;

    // CONSTRUCTOR
    public Snake(Direction start) {
        body = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            body.add(new Position(i + 3, 15));
        }
        way = start;
        status = true;
    }

    // function to move the snake
    public void move() {
        Position head = getHead();
        body.removeFirst();

        switch (way) {
            case UP -> head = new Position(head.getX(), head.getY() - 1);
            case DOWN -> head = new Position(head.getX(), head.getY() + 1);
            case RIGHT -> head = new Position(head.getX() + 1, head.getY());
            case LEFT -> head = new Position(head.getX() - 1, head.getY());
            default -> throw new IllegalArgumentException("Direction unavailable");
        }
        body.addLast(head);
    }

    // everytime the snake eats an apple it should increase size
    public void increase() {
        Position tail = getTail();
        body.addFirst(new Position(tail.getX(), tail.getY()));
    }

    // returns the linked list containing all positions of the body
    public LinkedList<Position> getBody() {
        return body;
    }

    // return the last element on the list
    public Position getHead() {
        return body.getLast();
    }

    // return the first element on the list
    public Position getTail() {
        return body.getFirst();
    }

    // everytime the direction changes we have to set a new direction
    public void setDirection(Direction direction) {
        this.way = direction;
    }

    // returns the direction that the snake is currently following
    public Direction getDirection() {
        return way;
    }
}