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
    private int thisPCRow;

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
            int condition = 1;
            for (File file : files) {
                condition++;
                if(fileSystemView.getSystemDisplayName(file).equals("This PC")){
                    DefaultMutableTreeNode thisPCNode = new DefaultMutableTreeNode(file);
                    root.add(thisPCNode);
                    File[] disks = File.listRoots();
                    for(File disk: disks) {
                        thisPCNode.add(new DefaultMutableTreeNode(disk));
                    }
                }
                else if (file.isDirectory()) {
                    node.add(new DefaultMutableTreeNode(file));
                }
            }
            thisPCRow = condition;
            System.out.println("This PC: " + thisPCRow);
        }
        setRoot(root);
    }
    // This method is called to show the children of the selected node.
    private boolean checkThisPC(File file){
        return fileSystemView.getSystemDisplayName(file).equals("This PC");

    }
    public void showChildren(DefaultMutableTreeNode node) {
        node.removeAllChildren();
        File file = (File) node.getUserObject();
        File[] files = fileSystemView.getFiles(file, true);
        for (File child : files) {
            if (child.isDirectory()) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
                node.add(childNode);
            }
        }
    }
    public int getThisPCRow(){
        return thisPCRow;
    }
}