package model;

import javax.swing.table.DefaultTableModel;
import java.io.File;

public class FileTableModel extends DefaultTableModel {
    public FileTableModel() {
        super(new Object[]{"Name", "Date Modified", "Type", "Size"}, 0);
    }

    private Thread threadBig;
    private ThreadGroup threadGroup = new ThreadGroup("Search");

    public void addRow(File file) {
        FileItem fileItem = new FileItem(file);
        addRow(new Object[]{fileItem.getPath(), fileItem.getDateModified(), fileItem.getType(), fileItem.getSize()});
    }

    public void removeRow(int row) {
        super.removeRow(row);
    }

    public void reset() {
        setRowCount(0);
    }

    public void updateRow(int row, File file) {
        FileItem fileItem = new FileItem(file);
        setValueAt(fileItem.getPath(), row, 0);
        setValueAt(fileItem.getDateModified(), row, 1);
        setValueAt(fileItem.getType(), row, 2);
        setValueAt(fileItem.getSize(), row, 3);
    }

    public void displayFilesInFolder(String pathFolder) {
        reset();
        File folder = new File(pathFolder);
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            addRow(file);
        }
    }

    public void displayFilesSearch(String pathFolder, String search) {
        reset();
        File folder = new File(pathFolder);
        closeAllThread();
        if (search.isEmpty()) {
            displayFilesInFolder(pathFolder);
            return;
        }
        displayFilesConsistent(folder, search);
    }

    private synchronized void displayFilesConsistent(File folder, String search) {
        Thread thread = new Thread(threadGroup,new Runnable() {
            @Override
            public void run() {
                File[] files = folder.listFiles();
                assert files != null;
                try {
                    for (File file : files) {
                        if (file.getName().toLowerCase().contains(search) || file.getName().toUpperCase().contains(search)) {
                            try {
                                addRow(file);
                            } catch (Exception e) {
                                System.out.println("Can't add row");
                            }
                        }
                        if (file.isDirectory()) {
                            displayFilesConsistent(file, search);
                        }
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            System.out.println("Thread interrupted");
                            e.printStackTrace();
                            return;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Can't display files");
                }
            }
        });
        thread.start();
    }
    //Close all thread in threadGroup
    private void closeAllThread(){
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (Thread thread : threads) {
            if (thread != null && !Thread.currentThread().equals(thread)) {
                thread.interrupt(); // Interrupt the thread
            }
        }
    }


    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
