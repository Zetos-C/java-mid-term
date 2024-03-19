package view;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {
    private JButton forwardButton;
    private JButton backButton;
    private JTextField pathLabel;
    private JTextField searchField;

    public ActionPanel() {
        init();
    }
    private void init() {
        pathLabel = new JTextField("Path: ");
        pathLabel.setPreferredSize(new Dimension(200, 20));
        pathLabel.setEditable(false);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        forwardButton = new JButton("Forward");
        backButton = new JButton("Back");
        add(backButton);
        add(forwardButton);
        add(pathLabel);
        searchField = new JTextField(20);
        add(searchField);
    }
    public JButton getForwardButton() {
        return forwardButton;
    }
    public JButton getBackButton() {
        return backButton;
    }
    public JTextField getPathLabel() {
        return pathLabel;
    }
    public JTextField getSearchField() {
        return searchField;
    }

}
