package snake;

/**
 * Position of any given object in theboard
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructor of the class
     * @param i Position of object in the X axis
     * @param j Position of object in the Y axis
     */
    public Position(int i, int j) {
        setX(i);
        setY(j);
    }

    /**
     * Checks if two objects are in the same place
     * @param o Second object to compare position
     * @return Boolean dependent on whether two objects are in the same place
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }

    /**
     * Getter for position of object in the X axis
     * @return Position of object in the X axis
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for position of object in the X axis
     * @param x New position of object in the X axis
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for position of object in the Y axis
     * @return Position of object in the Y axis
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for position of object in the Y axis
     * @param y New position of object in the Y axis
     */
    public void setY(int y) {
        this.y = y;
    }
}