package model.action;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.*;

public class FileUtils {

    public static void copyDirectory(Path source, Path target) throws IOException {
        Files.walk(source)
                .forEach(sourcePath -> {
                    Path targetPath = target.resolve(source.relativize(sourcePath));
                    try {
                        Files.copy(sourcePath, targetPath,
                                JOptionPane.showConfirmDialog(null, "Do you want to replace the file?", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION
                                ? StandardCopyOption.REPLACE_EXISTING : StandardCopyOption.COPY_ATTRIBUTES);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                });
    }

    public static void deleteDirectory(Path path) throws IOException {
        Files.walk(path)
                .forEach(p -> {
                    try {
                        Files.delete(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                });
    }
}
