package test;

import controller.FilesController;
import model.FileItem;
import view.ThisPcTree;
import view.FilesView;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class test {
    public static void main(String[] args) {
        FilesView view= new FilesView();
        ThisPcTree tree = new ThisPcTree();
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
