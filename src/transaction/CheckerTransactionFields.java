package transaction;

import transaction.constants.TransactionConstants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Утилитарный класс, представляющий проверку полей класса Transaction
 */
public final class CheckerTransactionFields {
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile(TransactionConstants.ACCOUNT_NUMBER_REG);

    /**
     * Приватный конструктор
     * @throws UnsupportedOperationException при попытке создания объекта класса
     */
    private CheckerTransactionFields() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Проверяет поля транзакции и собирает все найденные ошибки в одно сообщение
     * @param accountType тип счёта
     * @param accountNumber номер счёта
     * @param currency валюта
     * @param date дата операции
     * @param reference референс проводки
     * @param description описание операции
     * @param income доход
     * @param expense расход
     * @throws IllegalArgumentException если хотя бы один из параметров не прошёл проверку
     */
    public static void check(String accountType, String accountNumber, String currency, LocalDate date,
                             String reference, String description, BigDecimal income, BigDecimal expense) {
        StringBuilder messageException = new StringBuilder();
        checkAccountType(accountType, messageException);
        checkAccountNumber(accountNumber, messageException);
        checkCurrency(currency, messageException);
        checkDate(date, messageException);
        checkReference(reference, messageException);
        checkDescription(description, messageException);
        checkIncome(income, messageException);
        checkExpense(expense, messageException);

        if (!messageException.isEmpty()) {
            throw new IllegalArgumentException(messageException.toString());
        }
    }

    /**
     * Проверяет, что тип счёта не равен null
     * @param accountType тип счёта
     * @param messageException конструктор, в который собирается сообщение об ошибке
     */
    private static void checkAccountType(String accountType, StringBuilder messageException) {
        if (accountType == null) {
            messageException.append(TransactionConstants.ACCOUNT_TYPE_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Проверяет, что номер счёта не равен null и соответстсвует шаблону
     * @param accountNumber номер счёта
     * @param messageException конструктор, в который собирается сообщение об ошибке
     */
    private static void checkAccountNumber(String accountNumber, StringBuilder messageException) {
        if (accountNumber == null) {
            messageException.append(String.format(TransactionConstants.ACCOUNT_NUMBER_EXCEPTION_MESSAGE, "null"));
        } else {
            Matcher accountNumberMatcher = ACCOUNT_NUMBER_PATTERN.matcher(accountNumber);
            if (!accountNumberMatcher.find()) {
                messageException.append(String.format(
                                TransactionConstants.ACCOUNT_NUMBER_EXCEPTION_MESSAGE, accountNumber
                        )
                );
            }
        }
    }

    /**
     * Проверяет, что валюта не равна null
     * @param currency валюта
     * @param messageException конструктор, в который собирается сообщение об ошибке
     */
    private static void checkCurrency(String currency, StringBuilder messageException) {
        if (currency == null) {
            messageException.append(TransactionConstants.CURRENCY_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Проверяет, что дата операции не равна null
     * @param date дата операции
     * @param messageException конструктор, в который собирается сообщение об ошибке
     */
    private static void checkDate(LocalDate date, StringBuilder messageException) {
        if (date == null) {
            messageException.append(TransactionConstants.DATE_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Проверяет, что референс проводки не равен null
     * @param reference референс проводки
     * @param messageException конструктор, в который собирается сообщение об ошибке
     */
    private static void checkReference(String reference, StringBuilder messageException) {
        if (reference == null) {
            messageException.append(TransactionConstants.REFERENCE_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Проверяет, что описание операции не равно null
     * @param description описание операции
     * @param messageException конструктор, в который собирается сообщение об ошибке
     */
    private static void checkDescription(String description, StringBuilder messageException) {
        if (description == null) {
            messageException.append(TransactionConstants.DESCRIPTION_EXCEPTION_MESSAGE);
        }
    }

    /**
     * Проверяет, что доход не равен null и не отрицателен
     * @param income доход
     * @param messageException конструктор, в который собирается сообщение об ошибке
     */
    private static void checkIncome(BigDecimal income, StringBuilder messageException) {
        if (income == null) {
            messageException.append(String.format(TransactionConstants.INCOME_EXCEPTION_MESSAGE, "null"));
        } else if (income.signum() < 0) {
            messageException.append(String.format(TransactionConstants.INCOME_EXCEPTION_MESSAGE, income));
        }
    }

    /**
     * Проверяет, что расход не равен null и не отрицателен
     * @param expense расход
     * @param messageException конструктор, в который собирается сообщение об ошибке
     */
    private static void checkExpense(BigDecimal expense, StringBuilder messageException) {
        if (expense == null) {
            messageException.append(String.format(TransactionConstants.EXPENSE_EXCEPTION_MESSAGE, "null"));
        } else if (expense.signum() < 0) {
            messageException.append(String.format(TransactionConstants.EXPENSE_EXCEPTION_MESSAGE, expense));
        }
    }
}
