package client.view;

import client.MusicEventListener;
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
    private Music musicModel;

    private MusicPlayer player;

    private BufferedImage image;
    private JPanel controlPanel;

    private JLabel lblInfo;
    private JButton btnStart;
    private JPanel imagePanel;
    public MusicPanel(Music musicModel) {
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        lblInfo = new JLabel("this is the music");
        add(lblInfo, BorderLayout.NORTH);

        controlPanel = new JPanel();
        btnStart = new JButton("Start");
        controlPanel.add(btnStart);

        imagePanel = new JPanel();
        try {
            image = ImageIO.read(new File("res/photo/hatter.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //imagePanel.getGraphics().drawImage(image,0,0,null);
        add(imagePanel, BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);

        this.musicModel = musicModel;
        this.player = MusicPlayerFactory.getMusicPlayer();

        btnStart.addActionListener(e -> {
            File input = listener.getSongSource(musicModel.getId());
            player.load(input);
            player.start();
        });
    }

    public void addListener(MusicEventListener listener) {
        this.listener = listener;
    }
}
