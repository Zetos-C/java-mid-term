package view;

import model.FileItem;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class RootTree {
    private FileSystemView fileSystemView;
    private File[] roots;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    private FileItem fileItem;

    public RootTree() {
        fileSystemView = FileSystemView.getFileSystemView();
        roots = fileSystemView.getRoots();
    }
    public void setRootTree() {
        root = new DefaultMutableTreeNode();
        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add(node);
            File[] files = fileSystemView.getFiles(fileSystemRoot, true);
            for (File file : files) {
                if (file.isDirectory()) {
                    node.add(new DefaultMutableTreeNode(file));
                }
            }
        }
        treeModel = new DefaultTreeModel(root);
    }
    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }
}
