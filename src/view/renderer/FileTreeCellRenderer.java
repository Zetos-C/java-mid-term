package view.renderer;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;

public class FileTreeCellRenderer extends DefaultTreeCellRenderer {
    private FileSystemView fileSystemView;
    private JLabel label;

    public FileTreeCellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }
    // This method is called by JTree to draw a value.
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object obj = node.getUserObject();
        if (obj instanceof File) {
            File file = (File) obj;
            label.setIcon(fileSystemView.getSystemIcon(file));
            label.setText(fileSystemView.getSystemDisplayName(file));
            label.setToolTipText(file.getPath());
            if (selected) {
                label.setBackground(backgroundSelectionColor);
                label.setForeground(textSelectionColor);
            } else {
                label.setBackground(backgroundNonSelectionColor);
                label.setForeground(textNonSelectionColor);
            }
        } else {
            label.setIcon(null);
            label.setText("" + obj);
            label.setToolTipText(null);
        }
        return label;
    }


}
