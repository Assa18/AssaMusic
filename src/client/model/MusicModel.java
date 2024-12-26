package client.model;

import server.Music;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MusicModel {
    private Music activeModel;
    private List<Music> searchList;
    private Queue<Music> queueList;
    private static MusicModel instance = null;

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

    public Queue<Music> getQueueList() {
        return queueList;
    }

    public void setQueueList(Queue<Music> queueList) {
        this.queueList = queueList;
    }

    private MusicModel() {
        activeModel = new Music();
        searchList = new LinkedList<>();
        queueList = new LinkedList<>();
        System.out.println("Musicmodel initialized!");
    }
}
