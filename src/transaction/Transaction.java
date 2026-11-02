package transaction;

import transaction.constants.TransactionConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Запись, представляющая денежную транзакцию
 * @param accountType тип счёта
 * @param accountNumber номер счёта
 * @param currency валюта
 * @param date дата операции
 * @param reference референс проводки
 * @param description описание операции
 * @param income доход
 * @param expense расход
 */
public record Transaction(String accountType, String accountNumber, String currency, LocalDate date, String reference,
                          String description, BigDecimal income, BigDecimal expense) {
    /**
     * Конструктор проверяет поля через метод CheckerTransactionFields.check(...),
     * приводит income и expense к заданному масштабу
     * @param accountType тип счёта
     * @param accountNumber номер счёта
     * @param currency валюта
     * @param date дата операции
     * @param reference референс проводки
     * @param description описание операции
     * @param income доход
     * @param expense расход
     * @exception IllegalArgumentException если хотя бы один из параметров не прошёл проверку
     */
    public Transaction {
        CheckerTransactionFields.check(accountType, accountNumber, currency, date,
                reference, description, income, expense);
        income = income.setScale(TransactionConstants.SCALE, RoundingMode.UNNECESSARY);
        expense = expense.setScale(TransactionConstants.SCALE, RoundingMode.UNNECESSARY);
    }
}
