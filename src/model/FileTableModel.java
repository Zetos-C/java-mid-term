package model;

import javax.swing.table.DefaultTableModel;
import java.io.File;

public class FileTableModel extends DefaultTableModel {
    public FileTableModel() {
        super(new Object[]{"Name", "Date Modified", "Type","Size"}, 0);
    }
    public void addRow(File file) {
        FileItem fileItem = new FileItem(file);
        addRow(new Object[]{fileItem.getPath(),fileItem.getDateModified(), fileItem.getType(), fileItem.getSize()});
    }

    public void removeRow(int row) {
        super.removeRow(row);
    }

    public void reset() {
        setRowCount(0);
    }
    public void updateRow(int row, File file) {
        FileItem fileItem = new FileItem(file);
        setValueAt(fileItem.getPath(), row, 0);
        setValueAt(fileItem.getDateModified(), row, 1);
        setValueAt(fileItem.getType(), row, 2);
        setValueAt(fileItem.getSize(), row, 3);
    }
    public void displayFilesInFolder(String pathFolder) {
        reset();
        File folder = new File(pathFolder);
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            addRow(file);
        }
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
