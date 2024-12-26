package client;

import server.Music;

import java.io.File;
import java.util.List;

public interface MusicEventListener {
    void getMusicModels(String searchString);
    void getSongSource(String path);
    void setActiveMusic(Music music);
    void addToQueue(Music music);
}
