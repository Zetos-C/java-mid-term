package view;

import model.FileItem;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class ThisPcTree {
    private FileSystemView fileSystemView;
    private File[] roots;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode();
    private FileItem fileItem;

    public ThisPcTree() {
        fileSystemView = FileSystemView.getFileSystemView();
        roots = File.listRoots();
        roots = fileSystemView.getRoots();
    }
    // This method is called to set the root of the tree.
    public void setRootTree() {
//        DefaultMutableTreeNode thisPcNode = new DefaultMutableTreeNode("This PC");
//        root.add(thisPcNode);
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
