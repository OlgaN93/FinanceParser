package consoleInterface;

/**
 * Перечисление вариантов команд
 */
public enum Menu {
    EXPENSE_SUM ("сумма расходов"),
    INCOME_SUM ("сумма доходов"),
    EXPENSES_BY_COMPANY("расходы по организациям"),
    HELP ("помощь"),
    EXIT ("закончить программу");

    private final String title;

    /**
     * Конструктор
     * @param title значение, которое вводит пользователь
     */
    Menu(String title) {
        this.title = title;
    }

    /**
     * Возвращает строковое представление команды
     * @return строковое представление команды
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод получения команды по ее значению
     * @param title строковое представление команды
     * @return команда
     * @throws IllegalArgumentException если команда с таким представлением не найдена
     */
    public static Menu getCommand(String title) {
        for (Menu command : Menu.values()) {
            if (command.getTitle().equalsIgnoreCase(title)) {
                return command;
            }
        }

        throw new IllegalArgumentException(String.format("Некорректная команда '%s'", title));
    }
}
