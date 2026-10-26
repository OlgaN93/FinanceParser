package consoleInterface;

import consoleInterface.constants.ConsoleInterfaceConstants;
import movements.Movements;
import transaction.TransactionParserCSV;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleInterface {

    public static void consoleInterface(Path pathToFile) throws IOException {
        System.out.println(ConsoleInterfaceConstants.FILE_PROCESSING_MESSAGE);

        TransactionParserCSV parser = new TransactionParserCSV(pathToFile);
        Movements movements = new Movements(parser.transactionsRead());

        System.out.println(ConsoleInterfaceConstants.WELCOME_MESSAGE);

        try (Scanner sc = new Scanner(System.in)) {
            Command command;

            do {

                System.out.println(ConsoleInterfaceConstants.COMMAND_ENTER);
                String input = sc.nextLine();

                try {
                    command = Command.getCommand(input);
                } catch (IllegalArgumentException iae) {
                    command = null;
                }

                switch (command) {
                    case EXPENSE_SUM -> System.out.println(movements.getExpenseSum());
                    case INCOME_SUM -> System.out.println(movements.getIncomeSum());
                    case EXPENSES_BY_COMPANY -> {
                        Map<String, BigDecimal> expensesByCompany = movements.getExpensesByCompany();
                        String expensesByCompanyString = expensesByCompany.entrySet().stream()
                                .sorted(Map.Entry.comparingByKey())
                                .map(e ->
                                        String.format(ConsoleInterfaceConstants.DISPLAYING_FORMAT_EXPENSES_BY_COMPANY,
                                                e.getKey(), e.getValue()
                                        )
                                )
                                .collect(Collectors.joining(System.lineSeparator()));

                        System.out.println(expensesByCompanyString);
                    }
                    case HELP -> System.out.println(ConsoleInterfaceConstants.HELP_MESSAGE);
                    case EXIT -> {}
                    case null -> System.out.println(ConsoleInterfaceConstants.COMMAND_IS_INVALID + " " +
                            ConsoleInterfaceConstants.WELCOME_MESSAGE
                    );
                }

            } while (command == null || !(command.equals(Command.EXIT)));

            System.out.println(ConsoleInterfaceConstants.FAREWELL_MESSAGE);
        }
    }
}
