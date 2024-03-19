package model;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.text.SimpleDateFormat;

public class FileItem {
    private Icon icon;
    private String name;
    private String path;
    private String size;
    private String dateModified;
    private String type;
    private boolean isDirectory;
    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private File file;

    public FileItem() {
    }

    public FileItem(File file) {
        this.file = file;
    }

    public String getName() {
        this.name = fileSystemView.getSystemDisplayName(file);
        return name;
    }

    public String getPath() {
        this.path = file.getPath();
        return path;
    }

    public String getSize() {
        this.size = ((long) file.length() / 1024) + " KB";
        return size;
    }

    public String getDateModified() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateModified = dateFormat.format(file.lastModified());
        return dateModified;
    }
    public String getType() {
        this.type = fileSystemView.getSystemTypeDescription(file);
        return type;
    }

    public boolean isDirectory() {
        this.isDirectory = file.isDirectory();
        return isDirectory;
    }
    public Icon getIcon(){
        this.icon = fileSystemView.getSystemIcon(file);
        return icon;
    }
}
