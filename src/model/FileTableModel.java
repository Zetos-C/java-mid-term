package model;

import javax.swing.table.DefaultTableModel;
import java.io.File;

public class FileTableModel extends DefaultTableModel {
    public FileTableModel() {
        super(new Object[]{"Name", "Path", "Size", "Date Modified", "Type"}, 0);
    }
    public void addRow(File file) {
        FileItem fileItem = new FileItem(file);
        addRow(new Object[]{fileItem.getName(), fileItem.getPath(), fileItem.getSize(), fileItem.isDirectory() ? "Folder" : fileItem.getDateModified(), fileItem.getType()});
    }

    public void removeRow(int row) {
        super.removeRow(row);
    }

    public void reset() {
        setRowCount(0);
    }
    public void updateRow(int row, File file) {
        FileItem fileItem = new FileItem(file);
        setValueAt(fileItem.getName(), row, 0);
        setValueAt(fileItem.getPath(), row, 1);
        setValueAt(fileItem.getSize(), row, 2);
        setValueAt(fileItem.isDirectory() ? "Folder" : fileItem.getDateModified(), row, 3);
        setValueAt(fileItem.getType(), row, 4);
    }
    public void displayFiles(File[] files) {
        reset();
        for (File file : files) {
            addRow(file);
        }
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}