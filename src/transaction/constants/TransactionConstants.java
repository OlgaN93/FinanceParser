package transaction.constants;

public class TransactionConstants {
    private TransactionConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String ACCOUNT_TYPE_EXCEPTION_MESSAGE = "Тип счёта не может быть null\n";
    public static final String ACCOUNT_NUMBER_EXCEPTION_MESSAGE = "Номер счёта '%s' неверен\n";
    public static final String CURRENCY_EXCEPTION_MESSAGE = "Валюта не может быть null\n";
    public static final String DATE_EXCEPTION_MESSAGE = "Дата не может быть null\n";
    public static final String REFERENCE_EXCEPTION_MESSAGE = "Референс не может быть null\n";
    public static final String DESCRIPTION_EXCEPTION_MESSAGE = "Описание не может быть null\n";
    public static final String INCOME_EXCEPTION_MESSAGE = "Приход '%s' неверен\n";
    public static final String EXPENSE_EXCEPTION_MESSAGE = "Расход '%s' неверен\n";

    public static final String ACCOUNT_NUMBER_REG = "(\\d{20})";

    public static final int SCALE = 2;
}
