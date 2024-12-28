package client.model;

import server.Music;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MusicModel {
    private Music activeModel;
    private List<Music> searchList;
    private List<Music> queueList;
    private static MusicModel instance = null;

    private float progress=0;

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public static MusicModel getInstance() {
        if (instance == null){
            instance = new MusicModel();
        }
        return instance;
    }

    public Music getActiveModel() {
        return activeModel;
    }

    public void setActiveModel(Music activeModel) {
        this.activeModel = activeModel;
    }

    public List<Music> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Music> searchList) {
        this.searchList = searchList;
    }

    public List<Music> getQueueList() {
        return queueList;
    }

    public void setQueueList(List<Music> queueList) {
        this.queueList = queueList;
    }

    private MusicModel() {
        activeModel = new Music();
        searchList = new LinkedList<>();
        queueList = new LinkedList<>();
    }
}
