package client.view;

import client.MusicEventListener;
import client.model.MusicModel;
import client.model.MusicPlayer;
import client.model.MusicPlayerFactory;
import server.Music;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SearchPanel extends JPanel {

    private JPanel searchToolPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel resultsPanel;

    private JButton playButton;
    private JButton addQueueButton;

    private JPanel buttonPanel;
    private JList results;
    private DefaultListModel<String> listModel;
    private MusicModel musicModel = MusicModel.getInstance();
    private MusicPlayer player = MusicPlayerFactory.getMusicPlayer();

    private MusicEventListener listener;
    public SearchPanel() {
        setBackground(Color.lightGray);
        searchToolPanel = new JPanel();
        searchToolPanel.setBackground(new Color(1,1,1,0));
        resultsPanel = new JPanel();
        resultsPanel.setBackground(new Color(255,255,255, 50));

        searchButton = new JButton("Search");
        // searchButton.setBackground(new Color(0,0,0,0));
        // searchButton.setBorderPainted(false);
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200,20));
        searchToolPanel.add(searchField);
        searchToolPanel.add(searchButton);

        resultsPanel.setLayout(new BorderLayout());
        playButton = new JButton("Play");
        addQueueButton = new JButton("Add to queue");
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));

        listModel = new DefaultListModel<>();
        results = new JList<>(listModel);
        resultsPanel.add(results,BorderLayout.CENTER);


        buttonPanel.add(playButton);
        buttonPanel.add(addQueueButton);

        resultsPanel.add(buttonPanel, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(searchToolPanel, BorderLayout.NORTH);
        add(resultsPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> {
            listener.getMusicModels(searchField.getText());

            listModel.clear();
            musicModel.getSearchList().forEach(m -> listModel.addElement(m.getTitle() + "-" + m.getAuthor()));
        });

        playButton.addActionListener(e -> {
            listener.setActiveMusic(musicModel.getSearchList().get(results.getSelectedIndex()));
            listener.getSongSource(musicModel.getActiveModel().getPath());
            player.start();
        });

        addQueueButton.addActionListener(e -> {

        });
    }

    public void addListener(MusicEventListener listener) {
        this.listener = listener;
    }
}
