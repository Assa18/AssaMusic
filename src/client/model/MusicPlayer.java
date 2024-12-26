package client.model;

import java.io.File;

public interface MusicPlayer {

    void load(File file);
    void start();
    void stop();
    void resume();

    boolean isRunning();
}
