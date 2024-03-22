package controller;

import model.ActionItems;
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
                System.out.println("Copy");
                actionItems.copyFile(path);
                break;
            case "Cut":
                System.out.println("Cut");
                break;
            case "Paste":
                System.out.println("Paste");
                break;
            case "Delete":
                System.out.println("Delete");
                break;
            case "Rename":
                System.out.println("Rename");
                break;
            case "Properties":
                System.out.println("Properties");
                actionItems.propertiesFile();
                break;
        }
    }
}
