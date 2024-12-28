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

    private int queueSize = 15;

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
        view.refresh();
    }

    @Override
    public void addToQueue(List<Music> musicList) {
        musicModel.getQueueList().addAll(musicList);
        view.refresh();
    }

    @Override
    public void removeFromQueue(Music music) {
        musicModel.getQueueList().remove(music);
        view.refresh();
    }

    @Override
    public void spikNQueue(int n) {
        for (int i = 0;i < n;i++) {
            musicModel.getQueueList().removeLast();
        }
        view.refresh();
    }

    @Override
    public void fillQueue() {
        if (musicModel.getQueueList().size() >= queueSize) {
            return;
        }

        int nr = queueSize - musicModel.getQueueList().size();

        RandomRequest r = new RandomRequest();
        r.send(nr+"");

        List<Music> tmp = r.receive();

        tmp.forEach(music -> {
            musicModel.getQueueList().addFirst(music);
        });

        view.refresh();
    }

    @Override
    public void playNextSong() {
        if (musicModel.getQueueList().isEmpty()) {
            return;
        }

        setActiveMusic(musicModel.getQueueList().removeLast());
        getSongSource(musicModel.getActiveModel().getPath());
        player.start();
        fillQueue();
        view.refresh();
    }

    @Override
    public void setPosition(float pos) {
        musicModel.setProgress(pos);
    }
}
