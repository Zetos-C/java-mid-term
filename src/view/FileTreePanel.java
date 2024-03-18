package view;

import javax.swing.*;

public class FileTreePanel extends JPanel {
    private JScrollPane scrollPane;
    private JTree tree;
    public FileTreePanel() {
        tree = new JTree();
        tree.setRootVisible(false);
        scrollPane = new JScrollPane(tree);
        this.add(scrollPane);
        tree.expandRow(0);
    }
    public JScrollPane getScrollPane() {
        return scrollPane;
    }
    public JTree getTree() {
        return tree;
    }
    public void setTree(JTree tree) {
        this.tree = tree;
    }
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
}
