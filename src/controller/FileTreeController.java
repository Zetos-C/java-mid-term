package controller;

import model.FilesTreeModel;
import view.renderer.FileTreeCellRenderer;
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

        path = fileTableController.getPath();
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
    public FileTableController getFileTableController(){
        return this.fileTableController;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        try {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            path = selectedNode.getUserObject().toString();
            String pathTemp = path;
            fileTableController.getActionItems().setPathFile(path);
            fileTableController.getActionItems().updatePath(path);
            fileTableController.updatePathTextField();
            fileTableController.updateTable(selectedNode.getUserObject().toString());
        }
        catch (Exception f){
            System.out.println(f.toString());
        }
    }
}
