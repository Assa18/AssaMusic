package client.view;

import client.MusicEventListener;
import client.model.MusicPlayer;
import client.model.MusicPlayerFactory;

import javax.swing.*;
import java.awt.*;

public class MusicView extends JFrame {
    private JPanel controlPanel;
    private JButton btnSettings;
    private JButton btnSearch;
    private JButton btnMusic;
    private JButton btnQueue;

    private JPanel contentPanel;

    private MusicPanel musicPanel;
    private SearchPanel searchPanel;
    private SettingsPanel settingsPanel;
    private QueuePanel queuePanel;

    private MusicPlayer player;
    public MusicView() {
        contentPanel = new JPanel();
        controlPanel = new JPanel();

        btnSearch = new JButton("Search");
        btnSettings = new JButton("Settings");
        btnMusic = new JButton("Details");
        btnQueue = new JButton("Queue");
        controlPanel.add(btnSearch);
        controlPanel.add(btnMusic);
        controlPanel.add(btnQueue);
        controlPanel.add(btnSettings);

        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(controlPanel, BorderLayout.NORTH);


        CardLayout layout = new CardLayout();
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(layout);
        contentPanel.add(cardPanel, BorderLayout.CENTER);

        musicPanel = new MusicPanel();
        settingsPanel = new SettingsPanel();
        searchPanel = new SearchPanel();
        queuePanel = new QueuePanel();

        cardPanel.add(searchPanel, "searchP");
        cardPanel.add(musicPanel, "musicP");
        cardPanel.add(queuePanel, "queueP");
        cardPanel.add(settingsPanel, "settingsP");

        btnSearch.addActionListener(e -> layout.show(cardPanel, "searchP"));
        btnMusic.addActionListener(e -> layout.show(cardPanel, "musicP"));
        btnQueue.addActionListener(e -> layout.show(cardPanel, "queueP"));
        btnSettings.addActionListener(e -> layout.show(cardPanel, "settingsP"));

        player = MusicPlayerFactory.getMusicPlayer();

        setContentPane(contentPanel);
        setBounds(20,20,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void addListener(MusicEventListener listener) {
        musicPanel.addListener(listener);
    }
}
