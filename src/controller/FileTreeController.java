package controller;

import model.FilesTreeModel;
import view.FileTreeCellRenderer;
import view.FileTreePanel;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTreeController implements TreeSelectionListener {
    private FileTreePanel treePanel;
    private FilesTreeModel treeModel;
    private JTree tree;
    private FileTableController fileTableController;

    public FileTreeController(FileTreePanel treePanel, FilesTreeModel treeModel,FileTableController fileTableController) {
        this.treePanel = treePanel;
        this.treeModel = treeModel;
        this.fileTableController = fileTableController;
        tree = treePanel.getTree();
        updateTree();
        init();
    }
    private void init(){
        tree.expandRow(0);
        tree.addTreeSelectionListener(this);
    }

    public FileTreeController() {

    }
    public void updateTree(){
        tree.setModel(treeModel);
        tree.setCellRenderer(new FileTreeCellRenderer());
        treeModel.setRootTree();
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        System.out.println(selectedNode.getUserObject().toString());
        fileTableController.updateTable(selectedNode.getUserObject().toString());
    }
}
