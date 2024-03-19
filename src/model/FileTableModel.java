package model;

import javax.swing.table.DefaultTableModel;
import java.io.File;

public class FileTableModel extends DefaultTableModel {
    public FileTableModel() {
        super(new Object[]{"Icon","Name", "Date Modified", "Type","Size"}, 0);
    }
    public void addRow(File file) {
        FileItem fileItem = new FileItem(file);
        addRow(new Object[]{fileItem.getPath(),fileItem.getName(),fileItem.getDateModified(), fileItem.getType(), fileItem.getSize()});
    }

    public void removeRow(int row) {
        super.removeRow(row);
    }

    public void reset() {
        setRowCount(0);
    }
    public void updateRow(int row, File file) {
        FileItem fileItem = new FileItem(file);
        setValueAt(fileItem.getIcon(),row,0);
        setValueAt(fileItem.getName(), row, 1);
        setValueAt(fileItem.getDateModified(), row, 2);
        setValueAt(fileItem.getType(), row, 3);
        setValueAt(fileItem.getSize(), row, 4);
    }
    public void displayFilesInFolder(File folder) {
        reset();
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
