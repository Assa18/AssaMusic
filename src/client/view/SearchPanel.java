package client.view;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    private JPanel searchToolPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel resultsPanel;

    private JButton playButton;
    private JButton addQueueButton;

    private JPanel buttonPanel;
    private JList results;
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

        results = new JList();
        resultsPanel.add(results,BorderLayout.CENTER);


        buttonPanel.add(playButton);
        buttonPanel.add(addQueueButton);

        resultsPanel.add(buttonPanel, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(searchToolPanel, BorderLayout.NORTH);
        add(resultsPanel, BorderLayout.CENTER);
    }
}
