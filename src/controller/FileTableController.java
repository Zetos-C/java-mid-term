package controller;

import model.action.ActionItems;
import model.FileTableModel;
import view.FileTablePanel;
import view.actionPanel.PathTextField;
import view.renderer.FileCenterTextRenderer;
import view.renderer.FileIconRenderer;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

public class FileTableController implements MouseListener {
    private FileTablePanel fileTablePanel;
    private FileTableModel fileTableModel;
    private JTable fileTable;
    private OptionMenuController optionMenuController;
    private ActionItems actionItems;
    private String path;
    private PathTextField pathTextField;

    public FileTableController(FileTablePanel fileTablePanel, FileTableModel fileTableModel, OptionMenuController optionMenuController, ActionItems actionItems, PathTextField pathTextField) {
        this.fileTablePanel = fileTablePanel;
        this.fileTableModel = fileTableModel;
        this.pathTextField = pathTextField;
        this.actionItems = actionItems;
        fileTable = fileTablePanel.getFileTable();
        this.optionMenuController = optionMenuController;
        init();
    }

    private void init() {
        fileTable.addMouseListener(this);
        fileTablePanel.getFileTableScroll().addMouseListener(this);
        fileTable.setModel(fileTableModel);
        customTable();
    }

    public void updateTable(String path) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                fileTableModel.displayFilesInFolder(path);
                fileTablePanel.repaint();
            }
        });
        thread.start();
    }

    public void showDisks(String path) {

    }

    public void customTable() {
        fileTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        fileTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        fileTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        fileTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        fileTable.getColumnModel().getColumn(3).setCellRenderer(new FileCenterTextRenderer());
        fileTable.getColumnModel().getColumn(0).setCellRenderer(new FileIconRenderer());
        fileTable.setRowHeight(30);
        fileTablePanel.repaint();
    }

    public String getPath() {
        return path;
    }

    public ActionItems getActionItems() {
        return actionItems;
    }
    public PathTextField getPathTextField() {
        return pathTextField;
    }
    public void updatePathTextField(){
        pathTextField.setPath(actionItems.getPathCurrent());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2&&e.getSource()==fileTable) {
            int row = fileTable.rowAtPoint(e.getPoint());
            path = fileTable.getValueAt(row, 0).toString();
            actionItems.setPathFile(path);
            if (new File(path).isDirectory()) {
                actionItems.updatePath(path);
                updatePathTextField();
                updateTable(path);

            } else {
                actionItems.openFile();
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Right Click");
            optionMenuController.show(e);
            int row = fileTable.rowAtPoint(e.getPoint());
            int rowSelected = fileTable.getSelectedRowCount();
            if(rowSelected == 1){
                path = fileTable.getValueAt(row, 0).toString();
                actionItems.setPathFile(path);
            }
            else {
                setFilesInActionItems();
            }
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
    private void setFilesInActionItems(){
        int[] rows = fileTable.getSelectedRows();
        ArrayList<String> files = new ArrayList<>();
        for (int row : rows) {
            files.add(fileTable.getValueAt(row,0).toString());
        }
        actionItems.setPathFiles(files);
    }
}
