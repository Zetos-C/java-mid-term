package FilesManager;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import controller.*;
import model.action.ActionItems;
import model.FileTableModel;
import model.FilesTreeModel;
import view.*;
import view.actionPanel.ActionPanel;
import view.actionPanel.PathTextField;

import javax.swing.*;

public class FilesMangerApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Model
        FileTableModel fileTableModel = new FileTableModel();
        FilesTreeModel treeModel = new FilesTreeModel();

        //Vỉew
        FileTablePanel fileTablePanel = new FileTablePanel();
        FileTreePanel treePanel = new FileTreePanel();
        PathTextField pathTextField = new PathTextField();
        ActionPanel actionPanel = new ActionPanel(pathTextField);
        OptionMenu optionMenu = new OptionMenu();
        FilesView view= new FilesView(actionPanel,treePanel,fileTablePanel);

        //Items
        ActionItems actionItems = new ActionItems(fileTableModel);

        //Controller
        OptionMenuController optionMenuController = new OptionMenuController(optionMenu,actionItems);
        FileTableController tableController = new FileTableController(fileTablePanel,fileTableModel,optionMenuController,actionItems, pathTextField);
        FileTreeController treeController = new FileTreeController(treePanel,treeModel,tableController);
        ActionPanelController actionPanelController = new ActionPanelController(actionPanel,treeController);
        FilesController controller = new FilesController(view,treeModel,actionItems);

        view.setVisible(true);
        view.pack();
    }
}
