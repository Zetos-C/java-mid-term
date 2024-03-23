package model.action;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ClipboardUtil {

    public static void setFileToSystemClipboard(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            FileTransferable selection = new FileTransferable(file);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
        }
    }


    public static void setFilesToSystemClipboard(List<String> filePaths) {
        List<File> files = new ArrayList<>();
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists()) {
                files.add(file);
            }
        }
        FileTransferable selection = new FileTransferable(files);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    public static List<File> getFilesFromSystemClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        if (contents != null && contents.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            try {
                return (List<File>) contents.getTransferData(DataFlavor.javaFileListFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static class FileTransferable implements Transferable {
        private List<File> files;

        public FileTransferable(File file) {
            files = new ArrayList<>();
            files.add(file);
        }
        public FileTransferable(List<File> files) {
            this.files = files;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.javaFileListFlavor};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(DataFlavor.javaFileListFlavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return files;
        }
    }
}