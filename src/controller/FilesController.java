package controller;

import model.FileItem;
import view.FileTreeCellRenderer;
import view.FilesView;
import view.RootTree;

public class FilesController {
    private FilesView view;
    private FileItem file;
    private RootTree rootTree;

    public FilesController(FilesView view,RootTree tree, FileItem file) {
        this.view = view;
        this.file = file;
        this.rootTree = tree;
        setTreeModel();
        view.getTree().expandRow(0);
    }

    public void setTreeModel() {
        rootTree = new RootTree();
        rootTree.setRootTree();
        view.getTree().setModel(rootTree.getTreeModel());
        view.getTree().setCellRenderer(new FileTreeCellRenderer());
    }
}
