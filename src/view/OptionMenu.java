package view;

import javax.swing.*;

public class OptionMenu extends JMenu {
    private JMenuItem openMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem pasteManuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem renameMenuItem;
    private JMenuItem propertiesMenuItem;
    public OptionMenu(){
        init();
    }
    private void init(){
        openMenuItem = new JMenuItem("Open");
        copyMenuItem = new JMenuItem("Copy");
        cutMenuItem = new JMenuItem("Cut");
        pasteManuItem = new JMenuItem("Paste");
        deleteMenuItem = new JMenuItem("Delete");
        renameMenuItem = new JMenuItem("Rename");
        propertiesMenuItem = new JMenuItem("Properties");
        add(openMenuItem);
        add(copyMenuItem);
        add(cutMenuItem);
        add(pasteManuItem);
        add(deleteMenuItem);
        add(renameMenuItem);
        add(propertiesMenuItem);
    }

}
