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
//        actionPanel.getBackButton().setEnabled(false);
//        actionPanel.getForwardButton().setEnabled(false);
        init();
    }

    private void init() {
        actionPanel.getBackButton().addActionListener(this);
        actionPanel.getForwardButton().addActionListener(this);
    }
    public void updatePathTextField(){
        treeController.getFileTableController().updatePathTextField();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionPanel.getBackButton()) {
            String pathParent = treeController.getFileTableController().getActionItems().getPathParent();
            if (pathParent == null) {
//                actionPanel.getBackButton().setEnabled(false);
            } else {
//                actionPanel.getForwardButton().setEnabled(true);
                updatePathTextField();
                treeController.getFileTableController().updateTable(pathParent);
            }
        }
        if (e.getSource() == actionPanel.getForwardButton()) {
            String pathForward = treeController.getFileTableController().getActionItems().getPathChild();
            if (pathForward == null) {
//                actionPanel.getForwardButton().setEnabled(false);
            } else {
//                actionPanel.getBackButton().setEnabled(true);
                updatePathTextField();
                treeController.getFileTableController().updateTable(pathForward);
            }
        }
    }
}
