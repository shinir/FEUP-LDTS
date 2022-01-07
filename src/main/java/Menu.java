import com.googlecode.lanterna.TerminalPosition;
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
    private Screen screen;
    private Game game;
    private Board board;
    TextGraphics textGraphics;
    private boolean val;

    public Menu(int width, int height) {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            textGraphics = screen.newTextGraphics();

        } catch (IOException e) {
            e.printStackTrace();
        }
        board = new Board(width, height);
    }

    private void printMenu() {
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#008000"));
        textGraphics.putString(6, 1, "Main Menu");
        textGraphics.putString(2, 3, "Play Game");
        textGraphics.putString(2, 5, "Instructions");
        textGraphics.putString(2, 7, "Exit");
        textGraphics.putString(21, 3, "1");
        textGraphics.putString(21, 5, "2");
        textGraphics.putString(21, 7, "0");
        textGraphics.drawRectangle(new TerminalPosition(0, 0), new TerminalSize(27, 10), '*');
    }

    private char getInput() {
        char choice;

        while (true) {
            System.out.println("Enter your choice: ");
            try {
                KeyStroke key = screen.readInput();

                if (key.getKeyType() == KeyType.EOF) return '*';
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();

                if (key.getCharacter() == '0' || key.getCharacter() == '1' || key.getCharacter() == '2') {
                    choice = key.getCharacter();
                    return choice;
                } else {
                    System.out.println("Input invalid. Please try again.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doInput(int choice) throws IOException {
        switch (choice) {
            case '1' -> {
                board.draw(screen.newTextGraphics());
            }
            case '2' -> {
                printInstructions();
                val = true;
            }
            case '0' -> {
                val = true;
            }
            default -> System.out.println("Unknown error");
        }
    }

    private void draw() throws IOException {
        screen.clear();
        screen.refresh();
    }

    private void drawMenu() throws IOException {
        screen.clear();
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        board.processKey(key);
    }

    public void run() throws IOException {
        while (!val) {
            printMenu();
            screen.refresh();
            int choice = getInput();
            if (choice == '*') break;
            doInput(choice);
        }
    }

    private void printInstructions() throws IOException {
        try {
            File f = new File("Instructions.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.print(line);
                System.out.printf("%n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

