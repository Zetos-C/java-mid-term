package view.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
/**************************************************************************************************
 * @className FileCenterTextRenderer
 * @description FileCenterTextRenderer class for the FileCenterTextRenderer
 * @see javax.swing.table.DefaultTableCellRenderer
 * ************************************************************************************************
 * @methods
 * 1. getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int) : Component
 * ************************************************************************************************
 * This class is used to render the text in the center of the table cell.
 * ************************************************************************************************/
public class FileCenterTextRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
}
