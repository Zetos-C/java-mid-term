package view;

import javax.swing.*;
import java.awt.*;

public class FilesView extends JFrame {
    private JPanel contentPane = new JPanel();
    private JSplitPane splitPane;
    private JScrollPane treeScroll;
    private JTree tree;

    public FilesView() {
        tree = new JTree();
        treeScroll = new JScrollPane(tree);
        tree.setRootVisible(false);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, new JPanel());
        Dimension preferredSize = treeScroll.getPreferredSize();
        Dimension widePreferred = new Dimension(200, (int) preferredSize.getHeight());
        treeScroll.setPreferredSize(widePreferred);
        init();
    }

    private void init() {
        contentPane.setLayout(new BorderLayout());
        contentPane.add(splitPane, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setContentPane(contentPane);
    }

    public JScrollPane getTreeScroll() {
        return treeScroll;
    }

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }
}
