package snake;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu(40, 20);
        menu.runMenu();
    }
}