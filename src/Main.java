import consoleInterface.ConsoleInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
//            Path filePath = Paths.get("D:\\For_Olga\\Java_learn\\shorts_projects\\Parser\\movementList.csv");
            Path filePath = downLoader.DownLoader.saveToTempFile(
                    "https://drive.google.com/uc?export=download&id=1p-4eyyAA1lIi3lzpqR4CmGIehPUwnA9z"
            );
//            Path filePath = downLoader.DownLoader.save(
//                    "https://drive.google.com/uc?export=download&id=1p-4eyyAA1lIi3lzpqR4CmGIehPUwnA9z",
//                    "D:\\For_Olga\\Java_learn\\shorts_projects\\Parser",
//                    "movementList.csv"
//            );
            ConsoleInterface.consoleInterface(filePath);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

//Todo:
// 1. реализоавть паттерн builder для создания транзакции.
// 2. Реализовать паттер Command (создать интерфейс Command, классы для каждой команды, класс CommandControl
// и распределить movements по командам. Пример: https://javarush.com/quests/lectures/ru.javarush.java.core.lecture.level20.lecture05
// 3. Добавить документацию
// 4. Добавить логирование
// 5. Добавить тесты
