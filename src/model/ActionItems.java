package model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Stack;

public class ActionItems {
    private Stack<String> pathParents = new Stack<>();
    private Stack<String> pathTemps = new Stack<>();
    private String pathFile;
    private ArrayList<String> pathFiles;
    private Stack<String> pathChilds = new Stack<>();
    private Boolean isCopy = false;
    private Boolean isCut = false;
    private String test = "test";

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

    public void copyFile(String pathFile) {
        this.pathFile = pathFile;
        isCopy = true;
        isCut = false;
    }

    public void copyFile(ArrayList<String> pathFiles) {
        for (String pathFile : pathFiles) {
            this.pathFiles.add(pathFile);
        }
    }

    public void cutFile(String pathFile) {
        this.pathFile = pathFile;
        isCopy = false;
        isCut = true;
    }

    public void pasteFile(String pathChild) {
        if (isCopy) {
            // Copy file
            System.out.println("Copy file");
        }
        if (isCut) {
            // Cut file
            System.out.println("Cut file");
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

    public void renameFile(String pathFile) {
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

    public void updatePath(String newPath) {
        if (!pathTemps.isEmpty()) {
            pathParents.push(pathTemps.pop());
        }
        pathTemps.push(newPath);
        pathChilds.clear();
    }

    public String getPathParent() {
        if (pathParents.isEmpty()) {
            return null; // or throw an exception
        }
        pathChilds.push(pathTemps.pop());
        pathTemps.push(pathParents.peek());
        return pathParents.pop();
    }

    public String getPathChild() {
        if (pathChilds.isEmpty()) {
            return null; // or throw an exception
        }
        pathParents.push(pathTemps.pop());
        pathTemps.push(pathChilds.peek());
        return pathChilds.pop();
    }
    public String getPathCurrent() {
        return pathTemps.peek();
    }

    public void setPathParent(String newPathParent) {
        pathParents.push(pathTemps.pop());
        pathTemps.push(newPathParent);
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
        this.pathFile = pathFile;
    }
}
