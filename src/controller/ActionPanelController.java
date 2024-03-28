package controller;

import view.actionPanel.ActionPanel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*******************************************************
 * This class is the controller for the action panel.
 * It listens for the back and forward button clicks
 * and updates the path text field and the file table
 * accordingly.
 *******************************************************/
public class ActionPanelController implements ActionListener, DocumentListener {
    private ActionPanel actionPanel;
    String path;
    private FileTreeController treeController;

    public ActionPanelController(ActionPanel actionPanel, FileTreeController treeController) {
        this.actionPanel = actionPanel;
        this.treeController = treeController;
        actionPanel.getBackButton().setEnabled(false);
        actionPanel.getForwardButton().setEnabled(false);
        init();
    }

    private void init() {
        actionPanel.getBackButton().addActionListener(this);
        actionPanel.getForwardButton().addActionListener(this);
        actionPanel.getSearchField().getDocument().addDocumentListener(this);
        actionPanel.getRefeshButton().addActionListener(this);
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
        if (e.getSource() == actionPanel.getRefeshButton()){
            treeController.getFileTableController().getActionItems().reloadTable();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        searchFile();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        searchFile();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
    private void searchFile() {
        String search = actionPanel.getSearchField().getText();
        treeController.getFileTableController().getActionItems().searchFile(search);
    }
}
