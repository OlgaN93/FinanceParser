package consoleInterface;

public enum Command {
    EXPENSE_SUM ("сумма расходов"),
    INCOME_SUM ("сумма доходов"),
    EXPENSES_BY_COMPANY("расходы по организациям"),
    HELP ("помощь"),
    EXIT ("закончить программу");

    private final String title;

    Command(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Command getCommand(String title) {
        for (Command command : Command.values()) {
            if (command.getTitle().equalsIgnoreCase(title)) {
                return command;
            }
        }

        throw new IllegalArgumentException(String.format("Неккоректная команда '%s'", title));
    }
}
