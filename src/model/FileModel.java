package model;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileModel {
    private String name;
    private String path;
    private String size;
    private boolean isDirectory;
    private FileSystemView fileSystemView;
    private File[] files;

    public FileModel() {
        fileSystemView= FileSystemView.getFileSystemView();
        files = fileSystemView.getRoots();

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

    public  File[] getFiles() {
        return files;
    }

    public FileSystemView getFileSystemView() {
        return fileSystemView;
    }
    public boolean isDirectory() {
        return isDirectory;
    }
}
