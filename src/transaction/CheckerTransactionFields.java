package transaction;

import transaction.constants.TransactionConstants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckerTransactionFields {
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile(TransactionConstants.ACCOUNT_NUMBER_REG);

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

    private static void checkAccountType(String accountType, StringBuilder messageException) {
        if (accountType == null) {
            messageException.append(TransactionConstants.ACCOUNT_TYPE_EXCEPTION_MESSAGE);
        }
    }

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

    private static void checkCurrency(String currency, StringBuilder messageException) {
        if (currency == null) {
            messageException.append(TransactionConstants.CURRENCY_EXCEPTION_MESSAGE);
        }
    }

    private static void checkDate(LocalDate date, StringBuilder messageException) {
        if (date == null) {
            messageException.append(TransactionConstants.DATE_EXCEPTION_MESSAGE);
        }
    }

    private static void checkReference(String reference, StringBuilder messageException) {
        if (reference == null) {
            messageException.append(TransactionConstants.REFERENCE_EXCEPTION_MESSAGE);
        }
    }

    private static void checkDescription(String description, StringBuilder messageException) {
        if (description == null) {
            messageException.append(TransactionConstants.DESCRIPTION_EXCEPTION_MESSAGE);
        }
    }

    private static void checkIncome(BigDecimal income, StringBuilder messageException) {
        if (income == null) {
            messageException.append(String.format(TransactionConstants.INCOME_EXCEPTION_MESSAGE, "null"));
        } else if (income.signum() < 0) {
            messageException.append(String.format(TransactionConstants.INCOME_EXCEPTION_MESSAGE, income));
        }
    }

    private static void checkExpense(BigDecimal expense, StringBuilder messageException) {
        if (expense == null) {
            messageException.append(String.format(TransactionConstants.EXPENSE_EXCEPTION_MESSAGE, "null"));
        } else if (expense.signum() < 0) {
            messageException.append(String.format(TransactionConstants.EXPENSE_EXCEPTION_MESSAGE, expense));
        }
    }
}
