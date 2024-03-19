package test;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import controller.FileTableController;
import controller.FileTreeController;
import controller.OptionMenuController;
import model.FileTableModel;
import model.FilesTreeModel;
import view.FileTablePanel;
import view.FileTreePanel;
import view.FilesView;
import view.OptionMenu;

import javax.swing.*;

public class test {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        OptionMenu optionMenu = new OptionMenu();
        OptionMenuController optionMenuController = new OptionMenuController(optionMenu);

        FileTableModel fileTableModel = new FileTableModel();
        FileTablePanel fileTablePanel = new FileTablePanel();
        FileTableController tableController = new FileTableController(fileTablePanel,fileTableModel,optionMenuController);

        FileTreePanel treePanel = new FileTreePanel();
        FilesTreeModel treeModel = new FilesTreeModel();
        FileTreeController treeController = new FileTreeController(treePanel,treeModel,tableController);

        FilesView view= new FilesView(treePanel,fileTablePanel);

        view.setVisible(true);
        view.pack();
    }
}
