package view;

import view.actionPanel.ActionPanel;

import javax.swing.*;
import java.awt.*;

/*********************************************
 * This class is the view for the files panel.
 * It contains the file tree panel, file table
 * panel and the action panel.
 ********************************************/
public class FilesView extends JFrame {
    private JPanel contentPane = new JPanel();
    private JSplitPane splitPane;
    private JTree tree;
    private JTable fileTable;
    private JPanel fileTreePanel;
    private ActionPanel actionPanel;

    public FilesView(ActionPanel actionPanel,FileTreePanel fileTreePanel, FileTablePanel fileTablePanel) {
        tree = new JTree();
        this.actionPanel = actionPanel;
        this.fileTreePanel = fileTreePanel;
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, fileTreePanel, fileTablePanel);
        init();
    }

    private void init() {
        contentPane.setPreferredSize(new Dimension(1200, 500));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(splitPane, BorderLayout.CENTER);
        contentPane.add(actionPanel, BorderLayout.NORTH);
        this.setTitle("File Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
