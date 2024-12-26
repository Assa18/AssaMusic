package client.view;

import client.MusicEventListener;
import client.model.MusicModel;
import client.model.MusicPlayer;
import client.model.MusicPlayerFactory;
import server.Music;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MusicPanel extends JPanel {
    private MusicEventListener listener;
    private MusicModel musicModel = MusicModel.getInstance();

    private MusicPlayer player;

    private BufferedImage image;
    private JPanel controlPanel;

    private JLabel lblInfo;
    private JButton btnStart;
    private ImagePanel imagePanel;

    public MusicPanel() {
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        lblInfo = new JLabel(musicModel.getActiveModel().getTitle() + "-" + musicModel.getActiveModel().getAuthor());
        add(lblInfo, BorderLayout.NORTH);

        controlPanel = new JPanel();
        btnStart = new JButton("Stop");
        controlPanel.add(btnStart);

        imagePanel = new ImagePanel();

        add(imagePanel, BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);

        this.musicModel = musicModel;
        this.player = MusicPlayerFactory.getMusicPlayer();

        btnStart.addActionListener(e -> {
            if (player.isRunning()) {
                btnStart.setText("Start");
                player.stop();
            }
            else{
                btnStart.setText("Stop");
                player.resume();
            }
        });
    }

    public void addListener(MusicEventListener listener) {
        this.listener = listener;
    }
    public void refresh() {
        lblInfo.setText(musicModel.getActiveModel().getTitle()+"-"+musicModel.getActiveModel().getAuthor());
    }
}
