import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class BoardMenu {
    Screen screen;
    TextGraphics textGraphics;

    public BoardMenu() {
        try {
            // configuring the terminal
            TerminalSize terminalSize = new TerminalSize(40, 10);
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
    }

    private void printMenu() throws IOException {
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        textGraphics.putString(1, 1, "Are you sure you wanna exit your game?");
        textGraphics.putString(17, 3, "Yes");
        textGraphics.putString(17, 5, "Noo");
        //textGraphics.drawRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 10), '*');
        screen.refresh();
        doInputOver(screen.readInput());
        screen.refresh();
    }

    private void doInput(KeyStroke key) throws IOException {
        switch (key.getCharacter()) {
            case 'y' -> {
                System.exit(0);
            }
            case 'n' -> {
                screen.close();
            }
            default -> System.out.println("Unknown error");
        }
    }

    public void run() throws IOException{
        screen.clear();
        printMenu();
        screen.refresh();
        doInput(screen.readInput());
        screen.refresh();
    }

    public void gameOverMenu() throws IOException {
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#008000"));
        textGraphics.putString(10, 1, "Game Over");
        textGraphics.putString(1, 3, "Would you like to try again? ");
        textGraphics.drawRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 10), '*');
        textGraphics.putString(1, 4, "Yes.");
        textGraphics.putString(1, 5, "No.");
        screen.refresh();
        doInputOver(screen.readInput());
        screen.refresh();
    }

    private void doInputOver(KeyStroke key) throws IOException {
        switch (key.getCharacter()) {
            case 'y' -> {
                Menu menu = new Menu(40, 10);
                menu.run();
            }
            case 'n' -> {
                System.exit(0);
            }
            default -> System.out.println("Unknown error");
        }
    }
}
