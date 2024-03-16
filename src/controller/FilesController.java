package controller;

import model.FileItem;
import view.FileTreeCellRenderer;
import view.FilesView;
import view.ThisPcTree;

public class FilesController {
    private FilesView view;
    private FileItem file;
    private ThisPcTree rootTree;

    public FilesController(FilesView view, ThisPcTree tree, FileItem file) {
        this.view = view;
        this.file = file;
        this.rootTree = tree;
        setTreeModel();
        view.getTree().expandRow(1);
    }

    public void setTreeModel() {
        rootTree = new ThisPcTree();
        rootTree.setRootTree();
        view.getTree().setModel(rootTree.getTreeModel());
        view.getTree().setCellRenderer(new FileTreeCellRenderer());
    }
}
