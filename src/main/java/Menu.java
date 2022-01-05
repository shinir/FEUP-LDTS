import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.*;
import java.net.URL;

public class Menu {
    private Screen screen;
    private Game game;
    private Board board;
    private boolean val;

    public Menu(int width, int height) {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

        } catch (IOException e) {
            e.printStackTrace();
        }
        board = new Board(width, height);
    }

    private void printMenu() {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(6, 1, "Main Menu");
        textGraphics.putString(2, 3, "Play Game");
        textGraphics.putString(2, 5, "Instructions");
        textGraphics.putString(2, 7, "Exit");
        textGraphics.putString(21, 3, "1");
        textGraphics.putString(21, 5, "2");
        textGraphics.putString(21, 7, "0");
        textGraphics.drawRectangle(new TerminalPosition(0, 0), new TerminalSize(27, 10), '*');
    }

    private int getInput() {
        int choice;

        while (true) {
            System.out.println("Enter your choice: ");
            try {
                KeyStroke key = screen.readInput();
                if (key.getKeyType() != KeyType.Character) {
                    System.out.println("Input invalid. Please try again.");
                }
                else if (key.getCharacter() == '0' || key.getCharacter() == '1' || key.getCharacter() == '2') {
                    choice = key.getCharacter();
                    return choice;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doInput(int choice) throws FileNotFoundException {
        switch (choice) {
            case 1 -> {
                board.draw(screen.newTextGraphics());
            }
            case 2 -> {
                URL path = Menu.class.getResource("Instructions.txt");
                File f = new File(path.getFile());
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            }
            case 0 -> {
                val = true;
                break;
            }
            default -> System.out.println("Unknown error");
        }
    }

    private void draw() throws IOException {
        screen.clear();
        board.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        board.processKey(key);
    }

    public void run() throws IOException {
        while (!val) {
            printMenu();
            int choice = getInput();
            doInput(choice);
        }

        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                screen.close();
            }
            if (key.getKeyType() == KeyType.EOF) {
                break;
            }
        }
    }
}

