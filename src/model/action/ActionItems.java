package model.action;

import model.FileItem;
import model.FileTableModel;
import view.actionPanel.ActionPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

public class ActionItems {
    private Stack<String> pathParents = new Stack<>();
    private Stack<String> pathCurrent = new Stack<>();
    private String pathFile;
    private String pathFolder;
    private ArrayList<String> pathFiles;
    private Stack<String> pathChilds = new Stack<>();
    private FileTableModel fileTableModel;
    private Boolean isCopy = false;
    private Boolean isCut = false;
    private ActionPanel actionPanel;

    public ActionItems(FileTableModel fileTableModel, ActionPanel actionPanel) {
        this.fileTableModel = fileTableModel;
        this.actionPanel = actionPanel;
        loadState();
    }

    public void newFolder() {
        String folderName = JOptionPane.showInputDialog("Enter folder name");
        String pathFolder = pathCurrent.peek() + File.separator + folderName;
        File file = new File(pathFolder);
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
            reloadTable();
        } else {
            JOptionPane.showMessageDialog(null, "Folder already exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void newFile() {
        String fileName = JOptionPane.showInputDialog("Enter file name");
        String pathFile = pathCurrent.peek() + File.separator + fileName;
        File file = new File(pathFile);
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
                reloadTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "File already exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        isCopy = true;
        isCut = false;
        ClipboardUtil.setFilesToSystemClipboard(pathFiles);
    }

    public void cutFile() {
        isCopy = false;
        isCut = true;
        ClipboardUtil.setFileToSystemClipboard(pathFile);
    }

    public void cutFile(ArrayList<String> pathFiles) {
        this.pathFiles = pathFiles;
        isCopy = false;
        isCut = true;
        ClipboardUtil.setFilesToSystemClipboard(pathFiles);
    }

    public void actionPasteFile(File file) {
        if (file.isDirectory()) {
            try {
                FileUtils.copyDirectory(file.toPath(), Paths.get(pathCurrent.peek(), file.getName()));
                if (isCut) {
                    FileUtils.deleteDirectory(file.toPath());
                }
                reloadTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Files.copy(file.toPath(), Paths.get(pathCurrent.peek(), file.getName()),
                        JOptionPane.showConfirmDialog(null, "Do you want to replace the file?", "Warning",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION
                                ? StandardCopyOption.REPLACE_EXISTING : StandardCopyOption.COPY_ATTRIBUTES);
                if (isCut) {
                    Files.delete(file.toPath());
                }
                reloadTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void pasteFile() {
        List<File> files = ClipboardUtil.getFilesFromSystemClipboard();
        for (File file : files) {
            if (isExistFile(file)) {
                int choice = showDialogIfFileExist();
                if (choice == 0) {
                    return;
                }
            }
            actionPasteFile(file);
        }
    }

    public void deleteFile() {
        int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this file?", "Delete", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.NO_OPTION) {
            return;
        }
        File file = new File(pathFile);
        if (file.isDirectory()) {
            try {
                FileUtils.deleteDirectory(file.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // Delete file
        try {
            Files.delete(file.toPath());
            reloadTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void renameFile() {
        // Rename file
        String newName = JOptionPane.showInputDialog("Enter new name");
        if (newName != null) {
            File file = new File(pathFile);
            File newFile = new File(file.getParent() + File.separator + newName);
            if (file.renameTo(newFile)) {
                reloadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error renaming file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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

    public void searchFile(String search) {
        fileTableModel.displayFilesSearch(pathCurrent.peek(), search);
    }


    //
    public void updatePath(String newPath) {
        if (!pathCurrent.isEmpty()) {
            pathParents.push(pathCurrent.pop());
        }
        pathCurrent.push(newPath);
        pathChilds.clear();
        if (!actionPanel.getBackButton().isEnabled()) {
            actionPanel.getBackButton().setEnabled(true);
        }
        actionPanel.getForwardButton().setEnabled(false);

    }

    public void updateStatusButton() {
        if (canGoBack()) {
            actionPanel.getBackButton().setEnabled(true);
        } else {
            actionPanel.getBackButton().setEnabled(false);
        }
        if (canGoForward()) {
            actionPanel.getForwardButton().setEnabled(true);
        } else {
            actionPanel.getForwardButton().setEnabled(false);
        }
    }

    public String getPathParent() {
        if (pathParents.isEmpty()) {
            return null; // or throw an exception
        }
        updateStatusButton();
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
        updateStatusButton();
        return pathChilds.pop();
    }

    public String getPathCurrent() {
        updateStatusButton();
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

    public void reloadTable() {
        fileTableModel.displayFilesInFolder(pathCurrent.peek());
    }

    public boolean isExistFile(File file) {
        return file.exists();
    }

    public int showDialogIfFileExist() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "File already exists. Do you want to replace it?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            return 1;
        } else {
            return 0;
        }
    }

    public void saveState() {
        Properties prop = new Properties();
        prop.setProperty("isCopy", isCopy.toString());
        prop.setProperty("isCut", isCut.toString());

        try (FileOutputStream fos = new FileOutputStream("state.properties")) {
            prop.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadState() {
        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream("state.properties")) {
            prop.load(fis);
            isCopy = Boolean.parseBoolean(prop.getProperty("isCopy"));
            isCut = Boolean.parseBoolean(prop.getProperty("isCut"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
