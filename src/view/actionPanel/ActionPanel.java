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
    private JTextField searchField;
    private PathTextField pathTextField;

    public ActionPanel(PathTextField pathLabel) {
        this.pathTextField = pathLabel;
        init();
    }

    private void init() {

        searchField = new JTextField(20);
        forwardButton = new JButton();
        backButton = new JButton();

        searchField.setBorder(new EmptyBorder(5, 5, 5, 5));

        backButton.setSize(30, 30);
        backButton.setIcon(new ImageIcon("src/Resource/ActionPanel/left-arrow.png"));
        backButton.setPreferredSize(new Dimension(30, 30));
        backButton.setMinimumSize(new Dimension(30, 30));

        forwardButton.setPreferredSize(new Dimension(30, 30));
        forwardButton.setMinimumSize(new Dimension(30, 30));
        forwardButton.setIcon(new ImageIcon("src/Resource/ActionPanel/right-arrow.png"));

        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(backButton, gbc);

        gbc.gridx = 1;
        this.add(forwardButton, gbc);

        gbc.gridx = 2;
        this.add(pathTextField, gbc);

        gbc.gridx = 3;
        this.add(searchField, gbc);
    }

    public JButton getForwardButton() {
        return forwardButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public PathTextField getPathTextField() {
        return pathTextField;
    }

    public JTextField getSearchField() {
        return searchField;
    }

}
