package client.model;

import java.awt.event.WindowStateListener;

public class MusicPlayerFactory {
    static private MusicPlayer instance = null;
    public static MusicPlayer getMusicPlayer() {
        if (instance == null) {
            return new MPlayerBuiltin();
        }
        return instance;
    }
}
