package test;

import controller.FileTableController;
import controller.FileTreeController;
import model.FileTableModel;
import model.FilesTreeModel;
import view.FileTablePanel;
import view.FileTreePanel;
import view.FilesView;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class test {
    public static void main(String[] args) {
        FileTableModel fileTableModel = new FileTableModel();
        FileTablePanel fileTablePanel = new FileTablePanel();
        FileTableController tableController = new FileTableController(fileTablePanel,fileTableModel);

        FileTreePanel treePanel = new FileTreePanel();
        FilesTreeModel treeModel = new FilesTreeModel();
        FileTreeController treeController = new FileTreeController(treePanel,treeModel,tableController);

        FilesView view= new FilesView(treePanel,fileTablePanel);
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        view.setVisible(true);
        view.pack();
    }
}
