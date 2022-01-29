package snake;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.*;

/**
 * Game's main menu
 */
public class Menu {
    public int width;
    public int height;
    private Screen screen;
    public TextGraphics textGraphics;
    private boolean val, settings;
    public Speed speed = new Speed(100,3);
    public SoundEffect sound;

    /**
     * Constructor of the class
     * @param width Size of menu on the X axis
     * @param height Size of menu on the Y axis
     */
    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            textGraphics = screen.newTextGraphics();

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Board(width, height, speed.getSpeed());
        this.sound = new SoundEffect();
    }

    /**
     * Initializes menu
     */
    public void runMenu() throws IOException {

        while (!val) {
            screen.clear();
            printMenu();
            screen.refresh();
            int choice = getInput();
            sound.inputSound("mixkit-unlock-game-notification-253.wav");
            doInput(choice);
        }
    }

    /**
     * Prints menu to frame
     */
    private void printMenu() {
        screen.clear();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#bf0f0f"));
        textGraphics.putString(15, 1, "S N A K E");
        textGraphics.putString(10, 4, "Play Game");
        textGraphics.putString(10, 6, "Instructions");
        textGraphics.putString(10,8, "Settings");
        textGraphics.putString(10, 10, "Exit");
        textGraphics.putString(25, 4, "1");
        textGraphics.putString(25, 6, "2");
        textGraphics.putString(25, 8, "3");
        textGraphics.putString(25, 10, "0");
        textGraphics.drawLine(width, 0, width, height, ' ');
    }

    /**
     * Receives a valid input from user
     * @return User's input
     */
    private char getInput() {
        int pos = 14;
        while (true) {
            try {
                KeyStroke key = screen.readInput();

                if (key.getKeyType() == KeyType.EOF) return '*';
                else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();

                else if (key.getCharacter() == '0' || key.getCharacter() == '1' || key.getCharacter() == '2' || key.getCharacter() == '3') {
                    return key.getCharacter();
                }
                textGraphics.putString(0, pos, "Input invalid. Please try again.");
                screen.refresh();

            } catch (IOException e) {
                e.printStackTrace();
            }
            pos += 1;
        }
    }

    /**
     * Processes user's input
     * @param choice User's input
     */
    private void doInput(int choice) throws IOException {
        switch (choice) {
            case '1' : {
                Game snake = new Game(screen, speed.getSpeed());
                screen = snake.screen;
                snake.runGame();
                break;
            }
            case '2' : {
                printInstructions();
                val = true;
                break;
            }
            case '3' : {
                printSettings();
                val = true;
                break;
            }
            case '0' : {
                val = true;
                System.exit(0);
                break;
            }
            default : {
                System.out.println("Unknown error");
                break;
            }
        }
    }

    /**
     * Prints Instructions.txt content to frame
     */
    private void printInstructions() throws IOException {
        int pos = 0;

        screen.clear();
        try {
            File f = new File("src/main/resources/Instructions.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                textGraphics.putString(0, pos, line);
                screen.refresh();
                pos += 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pos += 1;

        while (true) {
            textGraphics.putString(1, pos, "Enter to go back");
            textGraphics.putString(1, pos+1, "Esc to finish");
            pos += 1;
            screen.refresh();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Enter) {
                sound.inputSound("mixkit-quick-lock-sound-2854.wav");
                runMenu();
            }
            else if (key.getKeyType() == KeyType.Escape) System.exit(0);
            else textGraphics.putString(0, pos, "Input invalid. Please try again.");
            pos += 1;
        }
    }

    /**
     * Draws settings submenu
     */
    private void printSettings() throws IOException {
        settings = true;
        screen.clear();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#bf0f0f"));
        textGraphics.putString(9, 5, "< Increase Speed >");
        textGraphics.putString(9, 12, "Press Esc to return");
        textGraphics.drawLine(width, 0, width, height, ' ');
        textGraphics.putString(17, 7, String.valueOf(speed.getShowSpeed()));
        screen.refresh();

        while (settings) {
            textGraphics.putString(1, 9, "                        ");
            KeyStroke key = screen.readInput();
            inputSettings(key);
            speed.changeSpeed();
            textGraphics.putString(11, 7, String.valueOf(speed.getShowSpeed()));
            screen.refresh();
        }
        runMenu();
    }

    /**
     * Receives user's input while on settings' submenu
     * @param key User's input
     */
    private void inputSettings(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowRight : {
                sound.inputSound("mixkit-unlock-game-notification-253.wav");
                if (speed.checker(1,textGraphics)) break;
                speed.setShowSpeed(1);
                break;
            }
            case ArrowLeft : {
                sound.inputSound("mixkit-unlock-game-notification-253.wav");
                if (speed.checker(-1,textGraphics)) break;
                speed.setShowSpeed(-1);
                break;
            }
            case Escape : {
                settings = false;
                sound.inputSound("mixkit-quick-lock-sound-2854.wav");
                break;
            }
            default : {
                System.out.println("Unknown error");
                break;
            }
        }
    }
}