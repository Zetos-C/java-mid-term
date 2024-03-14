package controller;

import model.FileModel;
import view.FilesView;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class FilesController {
    private FilesView view;
    private FileModel model;

    public FilesController(FilesView view, FileModel model) {
        this.view = view;
        this.model = model;
        view.getTree().setModel(setTreeModel());
        view.getTree().expandRow(0);
    }

    public DefaultTreeModel setTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        for (File file : model.getFiles()) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
            root.add(node);
        }
        return new DefaultTreeModel(root);
    }
}
