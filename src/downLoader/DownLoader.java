package downLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

/**
 * Утилитарный класс для скачивания файлов
 */
public class DownLoader {
    /**
     * Метод создает файл с переданным именем и директорию для него и вызывает метод downLoad
     * @param link ссылка на файл
     * @param dirPathString путь к директории в строковом виде
     * @param fileName название файла
     * @return путь до файла
     * @throws IOException при проблемах с созданием директории, созданием файла или в работе метода downLoad
     */
    public static Path save(String link, String dirPathString, String fileName) throws IOException {
        Path dirPath = Paths.get(dirPathString);
        Files.createDirectories(dirPath);

        Path filePath = dirPath.resolve(fileName);
        Files.createFile(filePath);

        downLoad(link, filePath);

        return filePath;
    }

    /**
     * Метод создает временный файл и запускает метод downLoad
     * @param link ссылка на файл
     * @return путь к временному файлу
     * @throws IOException при проблемах с созданием файла или в работе метода downLoad
     */
    public static Path saveToTempFile(String link) throws IOException {
        Path tempFile = Files.createTempFile("temp", ".csv");
        downLoad(link, tempFile);

        return tempFile;
    }

    /**
     * Метод создает объект URL, на основе которого открывает поток, и копирует данные из него в файл
     * @param link ссылка на файл
     * @param filePath путь до файла
     * @throws IOException при некорректной ссылке/URI или при копировании данных
     */
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
