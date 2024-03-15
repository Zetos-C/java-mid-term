package test;

import controller.FilesController;
import model.FileModel;
import model.RootTree;
import view.FilesView;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class test {
    public static void main(String[] args) {
        FilesView view= new FilesView();
        RootTree tree = new RootTree();
        FilesController controller = new FilesController(view,tree,new FileModel());
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        view.setVisible(true);
        view.pack();
    }
}
