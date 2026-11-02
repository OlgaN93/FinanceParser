package transaction;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс, определяющий контракт парсинга транзакций
 */
public interface TransactionParser {
    /**
     * Метод считывает транзакции из источника
     * @return список транзакций
     * @throws IOException если возникают ошибки при чтении файла
     */
    List<Transaction> transactionsRead() throws IOException;
}
