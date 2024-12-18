package client.control;

import client.MusicEventListener;
import client.view.MusicView;
import client.model.MusicPlayer;

import java.io.File;
import java.util.List;

public class MusicControl implements MusicEventListener {
    private MusicView view;

    private MusicPlayer player;

    public MusicControl(MusicView view, MusicPlayer player) {
        this.view = view;
        this.player = player;
        view.addListener(this);
    }

    @Override
    public List<String> getSongNames(String searchString) {
        return null;
    }

    @Override
    public File getSongSource(String name) {
        MusicRequest r = new PlayRequest();
        r.send(name);
        return null;
    }
}
