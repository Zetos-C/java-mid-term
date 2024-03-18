package view;

import model.FileTableModel;

import javax.swing.*;

public class FileTablePanel extends JPanel {
    private JTable fileTable;
    private JScrollPane fileTableScroll;
    public FileTablePanel() {
        fileTable = new JTable(new FileTableModel());
        fileTableScroll = new JScrollPane(fileTable);
        init();
    }
    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        fileTableScroll.add(fileTable);
        this.add(fileTableScroll);
    }

}
