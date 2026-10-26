package consoleInterface.constants;

import consoleInterface.Command;

public final class ConsoleInterfaceConstants {
    private ConsoleInterfaceConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String FILE_PROCESSING_MESSAGE = "Идет обработка файла";
    public static final String WELCOME_MESSAGE = String.format(
            "Для получения справки введите команду '%s'.", Command.HELP.getTitle()
    );
    public static final String COMMAND_ENTER = "Введите команду.";
    public static final String FAREWELL_MESSAGE = "Программа завершена.";
    public static final String COMMAND_IS_INVALID = "Команда неверна.";

    public static final String COMMAND_EXAMPLE_EXPENSE_SUM = String.format(
            "Пример команды для получения суммы расходов по всем операциям: '%s'", Command.EXPENSE_SUM.getTitle()
    );
    public static final String COMMAND_EXAMPLE_INCOME_SUM = String.format(
            "Пример команды для получения суммы доходов по операциям: '%s'", Command.INCOME_SUM.getTitle()
    );
    public static final String COMMAND_EXAMPLE_EXPENSES_BY_COMPANY = String.format(
            "Пример команды для получения расходов по организациям в виде строк: '%s'",
            Command.EXPENSES_BY_COMPANY.getTitle()
    );
    private static final String COMMAND_EXAMPLE_HELP = String.format(
            "Пример команды для получения справки по всем командам: '%s'", Command.HELP.getTitle()
    );
    private static final String COMMAND_EXAMPLE_EXIT = String.format(
            "Для выхода из программы введите: '%s'", Command.EXIT.getTitle()
    );

    public static final String HELP_MESSAGE = String.format(
            "%s;\n%s;\n%s;\n%s;\n%s;\n",
            COMMAND_EXAMPLE_EXPENSE_SUM, COMMAND_EXAMPLE_INCOME_SUM,
            COMMAND_EXAMPLE_EXPENSES_BY_COMPANY, COMMAND_EXAMPLE_HELP, COMMAND_EXAMPLE_EXIT
    );

    public static final String DISPLAYING_FORMAT_EXPENSES_BY_COMPANY = "%s -> %.2f руб.";
}
