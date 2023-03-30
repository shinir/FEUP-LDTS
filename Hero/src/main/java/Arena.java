import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;

import javax.swing.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10,10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    private void configureScreen(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }

    public void draw(TextGraphics graphics) throws IOException {
        configureScreen(graphics);
        hero.draw(graphics);
        for(Wall wall : walls)
            wall.draw(graphics);
        for(Coin coin : coins)
            coin.draw(graphics);
        for(Monster monster : monsters)
            monster.draw(graphics);
    }

    public int z = 0;
    public int range;

    public void processKey(KeyStroke key) throws IOException{
        // if else statements to move my character
        if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(hero.moveUp());
            range = 1;
        } else if (key.getKeyType() == KeyType.ArrowUp) {
            moveHero(hero.moveDown());
            range = 2;
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(hero.moveLeft());
            range = 3;
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(hero.moveRight());
            range = 4;
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            z = 1;
        } else if (key.getKeyType() == KeyType.EOF) {
            z = 1;
        }
    }

    public void moveHero(Position position) {
        if(canHeroMove(position)) {
            hero.setPosition(position);
        }
        else if(monsterCollision == false){
            System.out.println("You hit a monster, game over.");
            z = 1;
        }
        retrieveCoins(position);
        moveMonsters();
    }

    public void moveMonsters() {
        for(Monster monster : monsters) {
            if(canMonsterMove(monster.position))
                monster.setPosition(monster.move(range));
        }
    }

    public boolean monsterCollision;

    public boolean canMonsterMove(Position position) {
        for(Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;
        return true;
    }

    public boolean canHeroMove(Position position) {
        for(Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;
        monsterCollision = verifyMonsterCollisions(position);
        return monsterCollision;
    }

    public boolean verifyMonsterCollisions(Position position) {
        for(Monster monster : monsters)
            if(monster.getPosition().equals(position))
                return false;
        return true;
    }

    // Walls for my mini-game
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        }
        return monsters;
    }

    private void retrieveCoins(Position position) {
        for(Coin coin : coins)
            if(coin.getPosition().equals(hero.getPosition())) {
                coins.remove(coin);
                break;
            }
    }
}