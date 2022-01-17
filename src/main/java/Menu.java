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

public class Menu {
    // SIZE OF THE MENU
    int width, height;

    private Screen screen;
    TextGraphics textGraphics;
    private boolean val, settings;
    int speed = 1;

    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
        try {
            // configuring the terminal
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            // configuring the screen
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            textGraphics = screen.newTextGraphics();

        } catch (IOException e) {
            e.printStackTrace();
        }
        new Board(width, height, speed);
    }

    public void run() throws IOException {
        while (!val) {
            screen.clear();
            printMenu();
            screen.refresh();
            int choice = getInput();
            doInput(choice);
        }
    }

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
        //textGraphics.TextColor.Factory.fromString(String.valueOf(Color.red);
        textGraphics.drawLine(width, 0, width, height, ' ');
    }

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

    private void doInput(int choice) throws IOException {
        switch (choice) {
            case '1' -> {
                Game snake = new Game(screen, speed);
                screen = snake.screen;
                snake.run();
            }
            case '2' -> {
                printInstructions();
                val = true;
            }
            case '3' -> {
                printSettings();
                val = true;
            }
            case '0' -> {
                val = true;
                System.exit(0);
            }
            default -> System.out.println("Unknown error");
        }
    }

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
            if (key.getKeyType() == KeyType.Enter) run();
            else if (key.getKeyType() == KeyType.Escape) System.exit(0);
            else textGraphics.putString(0, pos, "Input invalid. Please try again.");
            pos += 1;
        }
    }

    private void printSettings() throws IOException {
        settings = true;
        screen.clear();
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#bf0f0f"));
        textGraphics.putString(3, 5, "< Increase Speed >");
        textGraphics.drawLine(width, 0, width, height, ' ');
        textGraphics.putString(11, 7, String.valueOf(speed));
        screen.refresh();

        while (settings) {
            KeyStroke key = screen.readInput();
            inputSettings(key);
            textGraphics.putString(11, 7, String.valueOf(speed));
            screen.refresh();
        }
    }

    private void inputSettings(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowRight -> speed++;
            case ArrowLeft -> speed--;
            case Escape -> {
                settings = false;
                run();
            }

            default -> System.out.println("Unknown error");
        }
    }
}