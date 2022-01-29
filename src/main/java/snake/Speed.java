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

    public boolean checker(int i, TextGraphics textGraphics) {
        return checkSpeed(i, textGraphics);
    }

    private void doSpeed() {
        if (showSpeed == 5) speed = 40;
        if (showSpeed == 4) speed = 60;
        if (showSpeed == 3) speed = 80;
        if (showSpeed == 2) speed = 100;
        if (showSpeed == 1) speed = 120;
    }

    public void changeSpeed() {
        doSpeed();
    }
    public int getSpeed() {
        return speed;
    }
    public int getShowSpeed() {
        return showSpeed;
    }
    public void setShowSpeed(int i) {
        showSpeed += i;
    }
}
