package transaction;

import java.io.IOException;
import java.util.List;

public interface TransactionParser {
    List<Transaction> transactionsRead() throws IOException;
}
