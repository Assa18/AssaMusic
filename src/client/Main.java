package client;

import client.control.MusicControl;
import client.model.MusicPlayer;
import client.model.MusicPlayerFactory;
import client.view.MusicView;

public class Main {
    public static void main(String[] args) {
        MusicView view = new MusicView();
        MusicPlayer player = MusicPlayerFactory.getMusicPlayer();
        MusicControl control = new MusicControl(view, player);

        view.setVisible(true);
    }
}
