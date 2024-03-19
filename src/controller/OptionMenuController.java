package controller;

import view.OptionMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionMenuController implements ActionListener {
    private OptionMenu optionMenu;
    public OptionMenuController(OptionMenu optionMenu){
        this.optionMenu = optionMenu;
        this.optionMenu.addActionListener(this);
    }
    public void showMenu(){
        optionMenu.setVisible(true);
    }
    public void hideMenu(){
        optionMenu.hide();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Open")){
            System.out.println("Open");
        }
        else if(e.getActionCommand().equals("Copy")){
            System.out.println("Copy");
        }
        else if(e.getActionCommand().equals("Cut")){
            System.out.println("Cut");
        }
        else if(e.getActionCommand().equals("Paste")){
            System.out.println("Paste");
        }
        else if(e.getActionCommand().equals("Delete")){
            System.out.println("Delete");
        }
        else if(e.getActionCommand().equals("Rename")){
            System.out.println("Rename");
        }
        else if(e.getActionCommand().equals("Properties")){
            System.out.println("Properties");
        }
    }
}
