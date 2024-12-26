package client.control;

import client.MusicEventListener;
import client.model.MusicModel;
import client.view.MusicView;
import client.model.MusicPlayer;
import server.Music;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MusicControl implements MusicEventListener {
    private MusicView view;

    private MusicPlayer player;
    private MusicModel musicModel;

    public MusicControl(MusicView view, MusicPlayer player) {
        this.view = view;
        this.player = player;
        this.musicModel = MusicModel.getInstance();
        view.addListener(this);
    }

    @Override
    public void getMusicModels(String searchString) {
        SearchRequest r = new SearchRequest();

        r.send(searchString);

        musicModel.setSearchList(r.receive());
    }

    @Override
    public void getSongSource(String path) {
        PlayRequest r = new PlayRequest();
        r.send(path);

        player.load(r.receive());
    }

    @Override
    public void setActiveMusic(Music music) {
        musicModel.setActiveModel(music);
        view.refresh();
    }

    @Override
    public void addToQueue(Music music) {
        musicModel.getQueueList().add(music);
    }
}
