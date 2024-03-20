package view;

import javax.swing.*;

public class OptionMenu extends JPopupMenu {
    private JMenuItem openMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem pasteManuItem;
    private JMenuItem deleteMenuItem;
    private JMenuItem renameMenuItem;
    private JMenuItem propertiesMenuItem;

    public OptionMenu() {
        init();
    }

    private void init() {
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

    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    public JMenuItem getCopyMenuItem() {
        return copyMenuItem;
    }

    public JMenuItem getCutMenuItem() {
        return cutMenuItem;
    }

    public JMenuItem getPasteManuItem() {
        return pasteManuItem;
    }

    public JMenuItem getDeleteMenuItem() {
        return deleteMenuItem;
    }

    public JMenuItem getRenameMenuItem() {
        return renameMenuItem;
    }

    public JMenuItem getPropertiesMenuItem() {
        return propertiesMenuItem;
    }
}
