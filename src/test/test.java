package test;

import controller.FileTreeController;
import controller.FilesController;
import model.FileItem;
import model.FilesTreeModel;
import view.FileTablePanel;
import view.FileTreePanel;
import view.FilesView;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class test {
    public static void main(String[] args) {

        FileTreePanel treePanel = new FileTreePanel();
        FilesTreeModel treeModel = new FilesTreeModel();
        FileTreeController treeController = new FileTreeController(treePanel,treeModel);

        FileTablePanel tablePanel = new FileTablePanel();

        FilesView view= new FilesView(treePanel,tablePanel);
        FilesTreeModel tree = new FilesTreeModel();
        FilesController controller = new FilesController(view,tree,new FileItem());
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        view.setVisible(true);
        view.pack();
    }
}
