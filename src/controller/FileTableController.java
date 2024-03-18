package controller;

import model.FileTableModel;
import view.FileTablePanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class FileTableController implements MouseListener {
    private FileTablePanel fileTablePanel;
    private FileTableModel fileTableModel;
    private JTable fileTable;

    public FileTableController(FileTablePanel fileTablePanel, FileTableModel fileTableModel) {
        this.fileTablePanel = fileTablePanel;
        this.fileTableModel = fileTableModel;
        fileTable = fileTablePanel.getFileTable();
        init();
    }
    private void init(){
        fileTable.addMouseListener(this);
    }
    public void updateTable(String path){
        fileTableModel.displayFilesInFolder(new File(path));
        fileTablePanel.repaint();
        fileTable.setModel(fileTableModel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
