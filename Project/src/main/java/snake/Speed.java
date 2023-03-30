package snake;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Speed {
    private int speed;
    private int showSpeed;

    /**
     * Constructor of the class
     * @param speed Custom speed of the snake
     * @param speedShown One of the 6 speeds available for the snake
     */
    public Speed(int speed, int speedShown) {
        this.speed = speed;
        showSpeed = speedShown;
    }

    /**
     * Checks if speed selected is valid
     * @param i Integer that added to current velocity determines inputted speed
     * @param textGraphics Where the output will be shown
     * @return Boolean dependent on whether max/min speed was reached
     */
    private boolean checkSpeed(int i, TextGraphics textGraphics) {
        if (showSpeed + i <= 0) {
            textGraphics.putString(1, 9, "Minimum speed reached");
            return true;
        }
        if (showSpeed + i >= 6) {
            textGraphics.putString(1, 9, "Maximum speed reached");
            return true;
        }
        return false;
    }

    /**
     * Intermediate to access checkSpeed
     * @param i Integer that added to current velocity determines inputted speed
     * @param textGraphics Where the output will be shown
     * @return checkSpeed output
     */
    public boolean checker(int i, TextGraphics textGraphics) {
        return checkSpeed(i, textGraphics);
    }

    /**
     * change speed according to the speed selected
     */
    private void doSpeed() {
        if (showSpeed == 5) speed = 40;
        if (showSpeed == 4) speed = 60;
        if (showSpeed == 3) speed = 80;
        if (showSpeed == 2) speed = 100;
        if (showSpeed == 1) speed = 120;
    }

    /**
     * Intermediate to access doSpeed
     */
    public void changeSpeed() {
        doSpeed();
    }

    /**
     * Getter for the speed value
     * @return speed value
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Getter to know which speed is selected
     * @return speed selected
     */
    public int getShowSpeed() {
        return showSpeed;
    }

    /**
     * Change the speed selected
     * @param i Integer that added to current velocity determines inputted speed
     */
    public void setShowSpeed(int i) {
        showSpeed += i;
    }
}
