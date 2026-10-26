package movements;

import transaction.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {
    private final List<Transaction> transactions;
    private static final String NAME_GROUP = "name";
    private static final String COMPANY_REG = String.format("(?<%s>[\\\\/].+?)(?=\\s{2,}\\d{2}\\.\\d{2}\\.\\d{2})", NAME_GROUP);
    private static final Pattern COMPANY_PATTERN = Pattern.compile(COMPANY_REG);

    public Movements(List<Transaction> transactions) {
        //TODO реализуйте сохранение строк в объекты в конструкторе
        this.transactions = new ArrayList<>(transactions);
    }

    public BigDecimal getExpenseSum() {
        //TODO метод получения расходов

        BigDecimal expenseSum = BigDecimal.ZERO;

        for (Transaction tr : transactions) {
            expenseSum = expenseSum.add(tr.expense());
        }

        return expenseSum;
    }

    public BigDecimal getIncomeSum() {
        //TODO метод получения доходов

        BigDecimal incomeSum = BigDecimal.ZERO;

        for (Transaction tr : transactions) {
            incomeSum = incomeSum.add(tr.income());
        }

        return incomeSum;
    }

    public Map<String, BigDecimal> getExpensesByCompany() throws IOException {
        //TODO метод получения расходов по организациям в виде строк

        Map<String, BigDecimal> expensesByCompany = new HashMap<>();

        for (Transaction tr : transactions) {
            Matcher transactionDescription = Movements.COMPANY_PATTERN.matcher(tr.description());

            if (!transactionDescription.find()) {
                System.out.printf("Описание транзакции не содержит название компании '%s'%n", tr.description());
            }

            String companyName = transactionDescription.group(NAME_GROUP);
            expensesByCompany.compute(companyName, (key, oldVal) ->
                    (oldVal == null) ? tr.expense() : oldVal.add(tr.expense()));
        }

        return expensesByCompany;
    }
}

