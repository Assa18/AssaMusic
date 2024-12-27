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
    private JButton btnNext;
    private ImagePanel imagePanel;
    private JPanel panelCurSize;
    private JPanel panelProgress;

    public MusicPanel() {
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        lblInfo = new JLabel(musicModel.getActiveModel().getTitle() + "-" + musicModel.getActiveModel().getAuthor());
        add(lblInfo, BorderLayout.NORTH);

        controlPanel = new JPanel();
        btnStart = new JButton("Stop");
        btnNext = new JButton("Next");
        controlPanel.add(btnStart);
        controlPanel.add(btnNext);

        imagePanel = new ImagePanel();

        add(imagePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        this.musicModel = musicModel;
        this.player = MusicPlayerFactory.getMusicPlayer();

        panelProgress = new JPanel();
        panelProgress.setLayout(null);
        panelProgress.setBackground(Color.gray);
        panelProgress.setPreferredSize(new Dimension(200,20));
        panelProgress.setBorder(BorderFactory.createLineBorder(Color.black));

        panelCurSize = new JPanel();
        panelCurSize.setBackground(Color.green);
        panelCurSize.setBounds(0,0,0,20);

        panelProgress.add(panelCurSize);

        controlPanel.add(panelProgress);

        new Thread(() -> {
           while (true) {
               if (player.isRunning()) {
                   panelCurSize.setBounds(0,0,(int)(musicModel.getProgress()*200),20);
                   System.out.println((int)(musicModel.getProgress()*200)+"--");
                   panelProgress.repaint();
               }
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        }).start();

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

        btnNext.addActionListener(e -> {
            listener.playNextSong();
        });
    }

    public void addListener(MusicEventListener listener) {
        this.listener = listener;
    }
    public void refresh() {
        lblInfo.setText(musicModel.getActiveModel().getTitle()+"-"+musicModel.getActiveModel().getAuthor());
    }
    // temporary
    public JButton getBtn() {
        return this.btnStart;
    }
}
