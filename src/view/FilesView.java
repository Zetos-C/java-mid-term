package view;

import javax.swing.*;
import java.awt.*;

public class FilesView extends JFrame {
    private JPanel contentPane = new JPanel();
    private JSplitPane splitPane;
    private JTree tree;
    private JTable fileTable;
    private JPanel fileTreePanel;

    public FilesView(FileTreePanel fileTreePanel, FileTablePanel fileTablePanel) {
        tree = new JTree();
        this.fileTreePanel = fileTreePanel;
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fileTreePanel, fileTablePanel);
        init();
    }

    private void init() {
        contentPane.setLayout(new BorderLayout());
        contentPane.add(splitPane, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setContentPane(contentPane);
    }

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }
    public void setFileTable(JTable fileTable) {
        this.fileTable = fileTable;
    }
    public JTable getFileTable() {
        return fileTable;
    }
    public void setFileTreePanel(JPanel fileTreePanel) {
        this.fileTreePanel = fileTreePanel;
    }
}
