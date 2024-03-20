package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        searchField = new JTextField(20);
        forwardButton = new JButton();
        backButton = new JButton();

        pathLabel.setPreferredSize(new Dimension(200, 35));
        pathLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        pathLabel.setEditable(false);

        searchField.setBorder(new EmptyBorder(5, 5, 5, 5));

        backButton.setSize(30, 30);
        backButton.setIcon(new ImageIcon("src/Resource/ActionPanel/left-arrow.png"));
        backButton.setPreferredSize(new Dimension(30, 30));
        backButton.setMinimumSize(new Dimension(30, 30));

        forwardButton.setMinimumSize(new Dimension(30, 30));
        forwardButton.setIcon(new ImageIcon("src/Resource/ActionPanel/right-arrow.png"));

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(backButton);
        add(forwardButton);
        add(pathLabel);
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
