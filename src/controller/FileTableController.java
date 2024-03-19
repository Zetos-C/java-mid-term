package controller;

import model.FileTableModel;
import view.FileIconRenderer;
import view.FileTablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class FileTableController implements MouseListener {
    private FileTablePanel fileTablePanel;
    private FileTableModel fileTableModel;
    private JTable fileTable;
    private OptionMenuController optionMenuController;

    public FileTableController(FileTablePanel fileTablePanel, FileTableModel fileTableModel, OptionMenuController optionMenuController) {
        this.fileTablePanel = fileTablePanel;
        this.fileTableModel = fileTableModel;
        fileTable = fileTablePanel.getFileTable();
        this.optionMenuController = optionMenuController;
        init();
    }
    private void init(){
        fileTable.addMouseListener(this);
    }
    public void updateTable(String path){
        System.out.println(path);
        fileTableModel.displayFilesInFolder(new File(path));
        fileTablePanel.repaint();
        fileTable.setModel(fileTableModel);
        customTable();
    }
    public void showDisks(String path){

    }
    public void customTable(){
        fileTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        fileTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        fileTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        fileTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        fileTable.getColumnModel().getColumn(0).setCellRenderer(new FileIconRenderer());
        fileTable.setRowHeight(30);
        fileTablePanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() ==MouseEvent.BUTTON1 && e.getClickCount() == 2){
            int row = fileTable.rowAtPoint(e.getPoint());
            String path = fileTable.getValueAt(row,0).toString();
            System.out.println(path);
            if(new File(path).isDirectory()){
                updateTable(path);

            }
            else {
                try {
                    Desktop.getDesktop().open(new File(path));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON3){
            System.out.println("Right Click");
            optionMenuController.showMenu();
        }
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
