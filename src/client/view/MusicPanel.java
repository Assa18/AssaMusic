package client.view;

import client.MusicEventListener;

import javax.swing.*;
import java.awt.*;

public class MusicPanel extends JPanel {
    private JButton btnPlay;
    private MusicEventListener listener;
    public MusicPanel() {
        setBackground(Color.lightGray);
        btnPlay = new JButton("Play");
        add(btnPlay);
        btnPlay.addActionListener(e -> listener.getSongSource("Bon jovi"));
    }

    public void addListener(MusicEventListener listener) {
        this.listener = listener;
    }
}
