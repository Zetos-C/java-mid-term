package controller;

import model.FileModel;
import model.RootTree;
import view.FilesView;

public class FilesController {
    private FilesView view;
    private FileModel file;
    private RootTree rootTree;

    public FilesController(FilesView view,RootTree tree, FileModel file) {
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
    }
}
