import consoleInterface.ConsoleInterface;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            Path filePath = downLoader.DownLoader.saveToTempFile(
                    "https://drive.google.com/uc?export=download&id=1p-4eyyAA1lIi3lzpqR4CmGIehPUwnA9z"
            );
            ConsoleInterface.consoleInterface(filePath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

