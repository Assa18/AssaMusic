package client.model;

import java.awt.event.WindowStateListener;

public class MusicPlayerFactory {
    static private MusicPlayer instance = null;
    public static MusicPlayer getMusicPlayer() {
        if (instance == null) {
            System.out.println("MPlayer initialized!");
            instance = new MPlayerJLayer();
        }
        return instance;
    }
}
