package model;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileItem {
    private String name;
    private String path;
    private String size;
    private boolean isDirectory;
    private FileSystemView fileSystemView;
    private File file;
    public FileItem() {
    }
    public FileItem(File file, FileSystemView fileSystemView) {
        this.file = file;
        this.name = file.getName();
        this.path = file.getPath();
        this.size = file.length() + " bytes";
        this.isDirectory = file.isDirectory();
        this.fileSystemView = fileSystemView;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getSize() {
        return size;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
    public Icon getSystemIcon() {
        return fileSystemView.getSystemIcon(file);
    }
}
