package model.action;

import java.io.IOException;
import java.nio.file.*;

public class FileUtils {

    public static void copyDirectory(Path source, Path target) throws IOException {
        Files.walk(source)
                .forEach(sourcePath -> {
                    Path targetPath = target.resolve(source.relativize(sourcePath));
                    try {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                });
    }
}
