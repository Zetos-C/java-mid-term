package view.actionPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**********************************************
 * @className ActionPanel
 * @description ActionPanel class for the ActionPanel
 * @see javax.swing.JPanel
 * *******************************************
 * @methods
 * 1. init() : void
 * *******************************************
 * @properties
 * 1. forwardButton : JButton
 * 2. backButton : JButton
 * 3. searchField : JTextField
 * 4. pathTextField : PathTextField
 * *****************************************/

public class ActionPanel extends JPanel {
    private JButton forwardButton;
    private JButton backButton;
    private JButton refreshButton;
    private JTextField searchField;
    private PathTextField pathTextField;

    public ActionPanel(PathTextField pathLabel) {
        this.pathTextField = pathLabel;
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        init();
    }

    private void init() {

        searchField = new JTextField(30);
        forwardButton = new JButton();
        backButton = new JButton();
        refreshButton = new JButton();

        searchField.setBorder(new EmptyBorder(5, 5, 5, 5));

        backButton.setSize(30, 30);
        backButton.setIcon(new ImageIcon("src/Resource/ActionPanel/left-arrow.png"));
        backButton.setPreferredSize(new Dimension(30, 30));
        backButton.setMinimumSize(new Dimension(30, 30));

        forwardButton.setPreferredSize(new Dimension(30, 30));
        forwardButton.setMinimumSize(new Dimension(30, 30));
        forwardButton.setIcon(new ImageIcon("src/Resource/ActionPanel/right-arrow.png"));

        refreshButton.setPreferredSize(new Dimension(30, 30));
        refreshButton.setMinimumSize(new Dimension(30, 30));

        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START; // Align components to the left
        this.add(backButton, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START; // Align components to the left
        this.add(forwardButton, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.LINE_START; // Align components to the left
        this.add(refreshButton, gbc);

        gbc.gridx = 3;
        gbc.weightx = 1.0; // Allow pathTextField to resize horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        this.add(pathTextField, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0; // Prevent searchField from resizing horizontally
        gbc.fill = GridBagConstraints.NONE; // Do not fill
        this.add(searchField, gbc);
    }

    public JButton getForwardButton() {
        return forwardButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public PathTextField getPathTextField() {
        return pathTextField;
    }

    public JTextField getSearchField() {
        return searchField;
    }

}
