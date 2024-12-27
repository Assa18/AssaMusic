package client.view;

import client.MusicEventListener;
import client.model.MusicModel;
import client.model.MusicPlayer;
import client.model.MusicPlayerFactory;

import javax.swing.*;
import java.awt.*;

public class QueuePanel extends JPanel {
    private JLabel lblCur;
    private JLabel lblCurTitle;
    private JLabel lblNext;
    private JList queueList;

    private JPanel contentPanel;
    private JPanel buttonPanel;
    private JButton btnRemove;
    private JButton btnPlay;

    private JPanel curPanel;
    private JPanel nextPanel;

    private MusicModel musicModel = MusicModel.getInstance();
    private DefaultListModel<String> listModel;

    private MusicEventListener listener;

    private MusicPlayer player = MusicPlayerFactory.getMusicPlayer();
    public QueuePanel() {
        setBackground(Color.lightGray);


        listModel = new DefaultListModel<>();
        queueList = new JList(listModel);

        btnPlay = new JButton("Play");
        btnRemove = new JButton("Remove from queue");
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.add(btnPlay);
        buttonPanel.add(btnRemove);

        setLayout(new BorderLayout());
        add(buttonPanel,BorderLayout.EAST);

        curPanel = new JPanel();
        nextPanel = new JPanel();

        lblCur = new JLabel("Current:");
        lblCurTitle = new JLabel(musicModel.getActiveModel().getTitle()+"-"+musicModel.getActiveModel().getAuthor());
        lblNext = new JLabel("Next:");

        curPanel.add(lblCur);
        curPanel.add(lblCurTitle);

        nextPanel.add(lblNext);
        nextPanel.add(queueList);

        add(curPanel, BorderLayout.NORTH);
        add(nextPanel, BorderLayout.CENTER);

        btnRemove.addActionListener(e -> {
            listener.removeFromQueue(musicModel.getQueueList().get(listModel.size() - queueList.getSelectedIndex()-1));
            listener.fillQueue();
        });

        btnPlay.addActionListener(e -> {
            int selectedIndex = queueList.getSelectedIndex();
            listener.getSongSource(musicModel.getQueueList().get(listModel.size() - selectedIndex-1).getPath());
            player.start();
            listener.setActiveMusic(musicModel.getQueueList().get(listModel.size() - selectedIndex-1));
            listener.spikNQueue(selectedIndex+1);
            listener.fillQueue();
        });
    }

    public void addListener(MusicEventListener listener) {
        this.listener = listener;
    }

    public void refreshListModel() {
        listModel.clear();
        if (!musicModel.getQueueList().isEmpty()) {
            for (int i = musicModel.getQueueList().size()-1; i >=0; i--) {
                listModel.addElement(musicModel.getQueueList().get(i).getTitle()+"-"+musicModel.getQueueList().get(i).getAuthor());
            }
        }
    }
    public void refresh() {
        lblCurTitle.setText(musicModel.getActiveModel().getTitle()+"-"+musicModel.getActiveModel().getAuthor());
    }
}
