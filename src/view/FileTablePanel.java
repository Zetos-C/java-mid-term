package view;

import javax.swing.*;

public class FileTablePanel extends JPanel {
    private JTable fileTable;
    private JScrollPane fileTableScroll;
    public FileTablePanel() {
        fileTable = new JTable();
        fileTableScroll = new JScrollPane(fileTable);
        init();
    }
    private void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(fileTableScroll);
    }
    public JTable getFileTable() {
        return fileTable;
    }
    public void setFileTable(JTable fileTable) {
        this.fileTable = fileTable;
    }

}
