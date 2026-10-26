package transaction;

import transaction.constants.TransactionConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public record Transaction(String accountType, String accountNumber, String currency, LocalDate date, String reference,
                          String description, BigDecimal income, BigDecimal expense) {
    public Transaction {
        CheckerTransactionFields.check(accountType, accountNumber, currency, date,
                reference, description, income, expense);
        income = income.setScale(TransactionConstants.SCALE, RoundingMode.UNNECESSARY);
        expense = expense.setScale(TransactionConstants.SCALE, RoundingMode.UNNECESSARY);
    }
}
