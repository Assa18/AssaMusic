package client.model;

import client.MusicEventListener;

import java.io.File;

public interface MusicPlayer {

    void load(File file);
    void start();
    void stop();
    void resume();

    boolean isRunning();
    void setListener(MusicEventListener listener);

    float getPosition();
}
