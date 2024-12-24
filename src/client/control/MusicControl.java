package client.control;

import client.MusicEventListener;
import client.view.MusicView;
import client.model.MusicPlayer;
import server.Music;

import java.io.File;
import java.util.List;

public class MusicControl implements MusicEventListener {
    private MusicView view;

    private MusicPlayer player;
    private Music musicModel;

    public MusicControl(MusicView view, MusicPlayer player, Music musicModel) {
        this.view = view;
        this.player = player;
        this.musicModel = musicModel;
        view.addListener(this);
    }

    @Override
    public List<String> getSongNames(String searchString) {
        return null;
    }

    @Override
    public File getSongSource(String id) {
        PlayRequest r = new PlayRequest();
        r.send(id);

        return r.receive();
    }
}
