package client.view;

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
    public QueuePanel() {
        setBackground(Color.lightGray);

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
        lblCurTitle = new JLabel("");
        lblNext = new JLabel("Next:");
        queueList = new JList();

        curPanel.add(lblCur);
        curPanel.add(lblCurTitle);

        nextPanel.add(lblNext);
        nextPanel.add(queueList);

        add(curPanel, BorderLayout.NORTH);
        add(nextPanel, BorderLayout.CENTER);
    }
}
