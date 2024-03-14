package test;

import controller.FilesController;
import model.FileModel;
import view.FilesView;

public class test {
    public static void main(String[] args) {
        FilesView view= new FilesView();
        FileModel model = new FileModel();
        FilesController controller = new FilesController(view, model);
        view.setVisible(true);
        view.pack();
    }
}
