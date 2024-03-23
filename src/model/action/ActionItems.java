package model.action;

import model.FileItem;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ActionItems {
    private Stack<String> pathParents = new Stack<>();
    private Stack<String> pathCurrent = new Stack<>();
    private String pathFile;
    private String pathFolder;
    private ArrayList<String> pathFiles;
    private Stack<String> pathChilds = new Stack<>();
    private Boolean isCopy = false;
    private Boolean isCut = false;

    public ActionItems() {

    }

    public void openFile() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new java.io.File(pathFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyFile() {
        ClipboardUtil.setFileToSystemClipboard(pathFile);
        isCopy = true;
        isCut = false;
    }

    // Copy multiple files
    public void copyFile(ArrayList<String> pathFiles) {
            this.pathFiles = pathFiles;
            ClipboardUtil.setFilesToSystemClipboard(pathFiles);
    }

    public void cutFile(String pathFile) {
        this.pathFile = pathFile;
        isCopy = false;
        isCut = true;
    }

    public void pasteFile() {
        List<File> files = ClipboardUtil.getFilesFromSystemClipboard();
        for (File file : files) {
            if (file.isDirectory()) {
                try {
                    FileUtils.copyDirectory(file.toPath(), Paths.get(pathCurrent.peek(), file.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Files.copy(file.toPath(), Paths.get(pathCurrent.peek(), file.getName()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}

    public void deleteFile() {
        // Delete file
        try {
            Files.delete(new File(pathFile).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void renameFile() {
        // Rename file
    }

    public void propertiesFile() {
        // Properties file
        JPanel panel = new JPanel();
        File file = new File(pathFile);
        FileItem fileItem = new FileItem(file);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Name: " + file.getName()));
        panel.add(new JLabel("Path: " + file.getPath()));
        panel.add(new JLabel("Size: " + fileItem.getSize()));
        panel.add(new JLabel("Type: " + fileItem.getType()));
        panel.add(new JLabel("Date modified: " + fileItem.getDateModified()));
        JOptionPane.showMessageDialog(null, panel, "Properties", JOptionPane.INFORMATION_MESSAGE);
    }
    //
    public void updatePath(String newPath) {
        if (!pathCurrent.isEmpty()) {
            pathParents.push(pathCurrent.pop());
        }
        pathCurrent.push(newPath);
        pathChilds.clear();
    }

    public String getPathParent() {
        if (pathParents.isEmpty()) {
            return null; // or throw an exception
        }
        pathChilds.push(pathCurrent.pop());
        pathCurrent.push(pathParents.peek());
        return pathParents.pop();
    }

    public String getPathChild() {
        if (pathChilds.isEmpty()) {
            return null; // or throw an exception
        }
        pathParents.push(pathCurrent.pop());
        pathCurrent.push(pathChilds.peek());
        return pathChilds.pop();
    }
    public String getPathCurrent() {
        return pathCurrent.peek();
    }

    public void setPathParent(String newPathParent) {
        pathParents.push(pathCurrent.pop());
        pathCurrent.push(newPathParent);
        pathChilds.clear();
    }

    public boolean canGoBack() {
        return !pathParents.isEmpty();
    }

    public boolean canGoForward() {
        return !pathChilds.isEmpty();
    }

    //    public void setPathChild(String pathChild) {
//        this.pathChild = pathChild;
//    }
    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFiles = null;
        this.pathFile = pathFile;
    }
    public ArrayList<String> getPathFiles() {
        return pathFiles;
    }
    public void setPathFiles(ArrayList<String> pathFiles) {
        this.pathFile = null;
        this.pathFiles = pathFiles;
    }
}
