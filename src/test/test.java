package test;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import controller.ActionPanelController;
import controller.FileTableController;
import controller.FileTreeController;
import controller.OptionMenuController;
import model.action.ActionItems;
import model.FileTableModel;
import model.FilesTreeModel;
import view.*;
import view.actionPanel.ActionPanel;
import view.actionPanel.PathTextField;

import javax.swing.*;

public class test {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PathTextField pathTextField = new PathTextField();

        ActionItems actionItems = new ActionItems();

        OptionMenu optionMenu = new OptionMenu();
        OptionMenuController optionMenuController = new OptionMenuController(optionMenu,actionItems);

        FileTableModel fileTableModel = new FileTableModel();
        FileTablePanel fileTablePanel = new FileTablePanel();
        FileTableController tableController = new FileTableController(fileTablePanel,fileTableModel,optionMenuController,actionItems, pathTextField);

        FileTreePanel treePanel = new FileTreePanel();
        FilesTreeModel treeModel = new FilesTreeModel();
        FileTreeController treeController = new FileTreeController(treePanel,treeModel,tableController);

        ActionPanel actionPanel = new ActionPanel(pathTextField);
        ActionPanelController actionPanelController = new ActionPanelController(actionPanel,treeController);

        FilesView view= new FilesView(actionPanel,treePanel,fileTablePanel);

        view.setVisible(true);
        view.pack();
    }
}
