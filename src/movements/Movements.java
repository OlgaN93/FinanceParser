package movements;

import transaction.Transaction;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Сервис для расчёта сумм доходов/расходов по списку транзакций
 */
public class Movements {
    private final List<Transaction> transactions;
    private static final String NAME_GROUP = "name";
    private static final String COMPANY_REG = String.format("(?<%s>[\\\\/].+?)(?=\\s{2,}\\d{2}\\.\\d{2}\\.\\d{2})", NAME_GROUP);
    private static final Pattern COMPANY_PATTERN = Pattern.compile(COMPANY_REG);

    /**
     * Конструктор
     * @param transactions список транзакций
     */
    public Movements(List<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    /**
     * Метод рассчитывает сумму всех расходов всех компаний
     * @return сумма расходов
     */
    public BigDecimal getExpenseSum() {
        BigDecimal expenseSum = BigDecimal.ZERO;

        for (Transaction tr : transactions) {
            expenseSum = expenseSum.add(tr.expense());
        }

        return expenseSum;
    }

    /**
     * Метод рассчитывает сумму всех доходов всех компаний
     * @return сумма доходов
     */
    public BigDecimal getIncomeSum() {
        BigDecimal incomeSum = BigDecimal.ZERO;

        for (Transaction tr : transactions) {
            incomeSum = incomeSum.add(tr.income());
        }

        return incomeSum;
    }

    /**
     * Метод рассчитывает сумму расходов у каждой компании по отдельности
     * @return ассоциативный массив компаний и их расходов
     */
    public Map<String, BigDecimal> getExpensesByCompany() {
        Map<String, BigDecimal> expensesByCompany = new HashMap<>();

        for (Transaction tr : transactions) {
            Matcher transactionDescription = Movements.COMPANY_PATTERN.matcher(tr.description());

            if (!transactionDescription.find()) {
                throw  new IllegalArgumentException(String.format(
                        "Описание транзакции не содержит название компании '%s'%n", tr.description()
                ));
            }

            String companyName = transactionDescription.group(NAME_GROUP);
            expensesByCompany.compute(companyName, (key, oldVal) ->
                    (oldVal == null) ? tr.expense() : oldVal.add(tr.expense()));
        }

        return expensesByCompany;
    }
}

