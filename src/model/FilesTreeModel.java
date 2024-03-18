package model;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class FilesTreeModel extends DefaultTreeModel {
    private FileSystemView fileSystemView;
    private File[] roots;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode();
    private FileItem fileItem;

    public FilesTreeModel() {
        super(new DefaultMutableTreeNode());
        fileSystemView = FileSystemView.getFileSystemView();
//        roots = File.listRoots();
        roots = fileSystemView.getRoots();
    }

    // This method is called to set the root of the tree.
    public void setRootTree() {
        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add(node);
            File[] files = fileSystemView.getFiles(fileSystemRoot, true);
            for (File file : files) {
                if(fileSystemView.getSystemDisplayName(file).equals("This PC")){
                    root.add(new DefaultMutableTreeNode(file));
                }
                else if (file.isDirectory()) {
                    node.add(new DefaultMutableTreeNode(file));
                }
            }
        }
        setRoot(root);
    }
}
