import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {

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
