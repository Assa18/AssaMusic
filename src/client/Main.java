package client;

import client.control.MusicControl;
import client.model.MusicPlayer;
import client.model.MusicPlayerFactory;
import client.view.MusicView;
import server.Music;

public class Main {
    public static void main(String[] args) {
        Music musicModel = new Music();
        MusicView view = new MusicView(musicModel);
        MusicPlayer player = MusicPlayerFactory.getMusicPlayer();
        MusicControl control = new MusicControl(view, player, musicModel);

        view.setVisible(true);
    }
}
