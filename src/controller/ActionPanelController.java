package controller;

import view.ActionPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanelController implements ActionListener {
    private ActionPanel actionPanel;
    String path;
    private FileTreeController treeController;

    public ActionPanelController(ActionPanel actionPanel, FileTreeController treeController) {
        this.actionPanel = actionPanel;
        this.treeController = treeController;
        init();
    }

    private void init() {
        actionPanel.getBackButton().addActionListener(this);
        actionPanel.getForwardButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        path = treeController.getPath();
        actionPanel.getPathLabel().setText("Path: " + path);
        if (e.getSource() == actionPanel.getBackButton()) {
            System.out.println("Back");
            actionPanel.getPathLabel().setText("Path: " + path);
        }
        if (e.getSource() == actionPanel.getForwardButton()) {
            System.out.println("Forward");
        }
    }
}
