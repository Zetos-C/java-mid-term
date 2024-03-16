package controller;

import model.FileTableModel;
import view.FileTablePanel;

public class FileTableController {
    private FileTablePanel fileTablePanel;
    private FileTableModel fileTableModel;
    public FileTableController(){
        fileTablePanel = new FileTablePanel();
        fileTableModel = new FileTableModel();
    }
    private void displayFilesInFolder(){
        fileTableModel.reset();

    }
}
