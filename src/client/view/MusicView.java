package client.view;

import client.MusicEventListener;
import client.model.MusicModel;
import client.model.MusicPlayer;
import client.model.MusicPlayerFactory;
import server.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MusicView extends JFrame {
    private JPanel controlPanel;
    private JButton btnSearch;
    private JButton btnMusic;
    private JButton btnQueue;

    private JPanel contentPanel;

    private MusicPanel musicPanel;
    private SearchPanel searchPanel;
    private QueuePanel queuePanel;


    public MusicView() {
        contentPanel = new JPanel();
        controlPanel = new JPanel();

        btnSearch = new JButton("Search");
        btnMusic = new JButton("Details");
        btnQueue = new JButton("Queue");
        controlPanel.add(btnSearch);
        controlPanel.add(btnMusic);
        controlPanel.add(btnQueue);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(controlPanel, BorderLayout.NORTH);


        CardLayout layout = new CardLayout();
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(layout);
        contentPanel.add(cardPanel, BorderLayout.CENTER);

        musicPanel = new MusicPanel();
        searchPanel = new SearchPanel();
        queuePanel = new QueuePanel();

        cardPanel.add(searchPanel, "searchP");
        cardPanel.add(musicPanel, "musicP");
        cardPanel.add(queuePanel, "queueP");

        btnSearch.addActionListener(e -> layout.show(cardPanel, "searchP"));
        btnMusic.addActionListener(e -> layout.show(cardPanel, "musicP"));
        btnQueue.addActionListener(e -> layout.show(cardPanel, "queueP"));


        setContentPane(contentPanel);
        setBounds(20,20,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public void addListener(MusicEventListener listener) {
        musicPanel.addListener(listener);
        searchPanel.addListener(listener);
        queuePanel.addListener(listener);
    }

    public void refresh() {
        musicPanel.refresh();
        queuePanel.refresh();
        queuePanel.refreshListModel();
    }
}
