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
    private String path;

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
        tree.expandRow(14);
        System.out.println(tree.getRowCount());
        tree.addTreeSelectionListener(this);
    }

    public FileTreeController() {

    }
    public void updateTree(){
        tree.setModel(treeModel);
        tree.setCellRenderer(new FileTreeCellRenderer());
        treeModel.setRootTree();
    }
    public FileTreePanel getTreePanel() {
        return treePanel;
    }

    public JTree getTree() {
        return tree;
    }
    public String getPath(){
        return path;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        try {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            System.out.println(selectedNode.getUserObject().toString());
            path = selectedNode.getUserObject().toString();
            fileTableController.updateTable(selectedNode.getUserObject().toString());
        }
        catch (Exception f){
            System.out.println(f.toString());
        }
    }
}
