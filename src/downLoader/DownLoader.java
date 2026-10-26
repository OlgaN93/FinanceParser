package downLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

public class DownLoader {
    public static Path save(String link, String dirPathString, String fileName) throws IOException {
        Path dirPath = Paths.get(dirPathString);
        Files.createDirectories(dirPath);

        Path filePath = dirPath.resolve(fileName);
        Files.createFile(filePath);

        downLoad(link, filePath);

        return filePath;
    }

    public static Path saveToTempFile(String link) throws IOException {
        Path tempFile = Files.createTempFile("temp", ".csv");
        downLoad(link, tempFile);

        return tempFile;
    }

    private static void downLoad(String link, Path filePath) throws IOException {
        URL url;
        try {
            url = new URI(link).toURL();
        } catch (URISyntaxException use) {
            throw new IOException(String.format("Неверный URI '%s'", link), use);
        }

        try (InputStream in = url.openStream()) {
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
