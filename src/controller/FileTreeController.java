package controller;

import model.FilesTreeModel;
import view.FileTreeCellRenderer;
import view.FileTreePanel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class FileTreeController implements TreeSelectionListener {
    private FileTreePanel treePanel;
    private FilesTreeModel treeModel;
    private JTree tree;

    public FileTreeController(FileTreePanel treePanel, FilesTreeModel treeModel) {
        this.treePanel = treePanel;
        this.treeModel = treeModel;
        tree = new JTree();
        updateTree();
    }

    public FileTreeController() {

    }
    public void updateTree(){
        tree.setModel(treeModel);
        treeModel.setRootTree();
        treePanel.setTree(tree);
        treePanel.getTree().setCellRenderer(new FileTreeCellRenderer());
        treeModel.reload();
        treePanel.repaint();
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {

    }
}
