package controller;

import model.action.ActionItems;
import view.OptionMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class OptionMenuController implements ActionListener {
    private OptionMenu optionMenu;
    private String path;
    private ActionItems actionItems;
    public OptionMenuController(OptionMenu optionMenu, ActionItems actionItems){
        this.optionMenu = optionMenu;
        this.actionItems = actionItems;
        init();
    }
    private void init(){
        this.optionMenu.getOpenMenuItem().addActionListener(this);
        this.optionMenu.getCopyMenuItem().addActionListener(this);
        this.optionMenu.getCutMenuItem().addActionListener(this);
        this.optionMenu.getPasteManuItem().addActionListener(this);
        this.optionMenu.getDeleteMenuItem().addActionListener(this);
        this.optionMenu.getRenameMenuItem().addActionListener(this);
        this.optionMenu.getPropertiesMenuItem().addActionListener(this);
        this.optionMenu.getNewFolderMenuItem().addActionListener(this);
        this.optionMenu.getNewFileMenuItem().addActionListener(this);
    }
    public void show(MouseEvent e){
        optionMenu.show(e.getComponent(),e.getX(),e.getY());
    }
    public void hideMenu(){
        optionMenu.hide();
    }
    public void setPath(String path){
        this.path = path;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "Open":
                actionItems.openFile();
                break;
            case "Copy":
                if(actionItems.getPathFile() != null){
                    actionItems.copyFile();
                } else if (actionItems.getPathFiles() != null) {
                    actionItems.copyFile(actionItems.getPathFiles());
                }
                break;
            case "Cut":
                if(actionItems.getPathFile() != null){
                    actionItems.cutFile();
                } else if (actionItems.getPathFiles() != null) {
                    actionItems.cutFile(actionItems.getPathFiles());
                }
                break;
            case "Paste":
                actionItems.pasteFile();
                break;
            case "Delete":
                actionItems.deleteFile();
                break;
            case "Rename":
                actionItems.renameFile();
                break;
            case "Properties":
                actionItems.propertiesFile();
                break;
            case "Folder":
                actionItems.newFolder();
                break;
            case "File":
                actionItems.newFile();
                break;
        }
    }
}
