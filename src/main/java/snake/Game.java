package snake;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import snake.Board;
import snake.BoardMenu;

import java.io.IOException;

public class Game {
    Screen screen;
    Board board;
    TextGraphics graphics;
    int speed;

    public Game(Screen screen, int speed) {
        try {
            this.screen = screen;
            this.speed = speed;
            // configuring the screen
            screen.refresh();

            graphics = screen.newTextGraphics();
            board = new Board(40,20, speed);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        board.processKey(key);
    }

    public void draw() throws IOException {
        screen.clear();
        board.draw(graphics);
        screen.refresh();
    }

    public void run() throws IOException {
        while(board.available) {
            draw();
            processKey(screen.readInput());
            if (!board.moveSnake()) break;
        }
        gameOver();
        screen.close();
    }

    private void gameOver() throws IOException {
        board.boardMenu = new BoardMenu();
        board.boardMenu.gameOverMenu(board.points);
    }
}
