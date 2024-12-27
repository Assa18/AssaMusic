package client.model;

import client.MusicEventListener;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MPlayerBuiltin implements MusicPlayer {

    private File source;
    private Clip clip;
    @Override
    public void load(File file) {
        if (clip != null && clip.isOpen()) {
            clip.close();
        }
        source = file;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(source);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {
        clip.setFramePosition(0);
        clip.start();
    }

    @Override
    public void stop() {
        clip.stop();
    }

    @Override
    public void resume() {
        clip.start();
    }

    @Override
    public boolean isRunning() {
        return clip.isRunning();
    }

    @Override
    public void setListener(MusicEventListener listener) {

    }

    @Override
    public float getPosition() {
        return 0;
    }
}
