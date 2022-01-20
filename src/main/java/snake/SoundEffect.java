package snake;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Plays sound effects depending on user's input
 */
public class SoundEffect {

    /**
     * Plays sound effect of specific file
     * @param name Name of the file containing the wanted sound effect
     */
    public void inputSound(String name) {

        File f = new File("src/main/resources/" + name);
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
