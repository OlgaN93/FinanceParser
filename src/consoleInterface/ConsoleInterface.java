package consoleInterface;

import consoleInterface.constants.ConsoleInterfaceConstants;
import movements.Movements;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Консольный интерфейс для взаимодействия с пользователем
 */
public class ConsoleInterface {
    private final Movements movements;

    /**
     * Конструктор
     * @param movements объект класса, выполняющего команды
     */
    public ConsoleInterface(Movements movements) {
        this.movements = movements;
    }

    /**
     * Метод выводит сообщения, принимает команды из System.in, цикл завершается по команде EXIT,
     * при неверной команде выводится сообщение и запрос повторяется
     */
    public void consoleInterface() {
        System.out.println(ConsoleInterfaceConstants.WELCOME_MESSAGE);

        try (Scanner sc = new Scanner(System.in)) {
            Menu item;

            do {

                System.out.println(ConsoleInterfaceConstants.COMMAND_ENTER);
                String input = sc.nextLine();

                try {
                    item = Menu.getCommand(input);
                } catch (IllegalArgumentException iae) {
                    item = null;
                }

                switch (item) {
                    case EXPENSE_SUM -> System.out.println(movements.getExpenseSum());
                    case INCOME_SUM -> System.out.println(movements.getIncomeSum());
                    case EXPENSES_BY_COMPANY -> System.out.println(this.expensesByCompanyOutput());
                    case HELP -> System.out.println(ConsoleInterfaceConstants.HELP_MESSAGE);
                    case EXIT -> {}
                    case null -> System.out.println(ConsoleInterfaceConstants.COMMAND_IS_INVALID + " " +
                            ConsoleInterfaceConstants.WELCOME_MESSAGE
                    );
                }

            } while (item == null || !(item.equals(Menu.EXIT)));

            System.out.println(ConsoleInterfaceConstants.FAREWELL_MESSAGE);
        }
    }

    /**
     * Метод приводит список компаний и их расходов к формату, требующемуся для вывода информации на экран
     * @return строка вывода
     */
    private String expensesByCompanyOutput() {
        Map<String, BigDecimal> expensesByCompany = movements.getExpensesByCompany();
        return expensesByCompany.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e ->
                        String.format(ConsoleInterfaceConstants.DISPLAYING_FORMAT_EXPENSES_BY_COMPANY,
                                e.getKey(), e.getValue()
                        )
                )
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
