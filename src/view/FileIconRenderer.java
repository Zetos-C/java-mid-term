package view;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;

public class FileIconRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            File file = new File((String) value);
            Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
            label.setIcon(icon);
            label.setText(FileSystemView.getFileSystemView().getSystemDisplayName(file));
//            label.setHorizontalAlignment(SwingConstants.CENTER);
//            label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}
