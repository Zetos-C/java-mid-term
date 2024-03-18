package controller;

import model.FileItem;
import view.FileTreeCellRenderer;
import view.FilesView;
import model.FilesTreeModel;

public class FilesController {
    private FilesView view;
    private FileItem file;
    private FilesTreeModel rootTree;

    public FilesController(FilesView view, FilesTreeModel tree, FileItem file) {
        this.view = view;
        this.file = file;
        this.rootTree = tree;
    }

    public void setTreeModel() {
        rootTree = new FilesTreeModel();
        rootTree.setRootTree();
        view.getTree().setModel(rootTree);
        view.getTree().setCellRenderer(new FileTreeCellRenderer());
    }
}
