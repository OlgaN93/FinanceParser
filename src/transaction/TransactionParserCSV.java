package transaction;

import constants.DatePatterns;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TransactionParserCSV implements TransactionParser {
    Path pathToCSV;

    public TransactionParserCSV(Path pathFile) {
        this.pathToCSV = pathFile;
    }

    @Override
    public List<Transaction> transactionsRead() throws IOException {
        final List<Transaction> transactions = new ArrayList<>();
        final int numberOfLineColumnsName = 1;

        try (Stream<String> linesStream = Files.lines(pathToCSV)) {
            linesStream.skip(numberOfLineColumnsName)
                    .forEach(line -> transactions.add(getTransaction(line)));
        } catch (NoSuchFileException ioe) {
            throw new IOException(String.format("Файла '%s' нет по указанному пути", pathToCSV), ioe);
        } catch (IOException ioe) {
            throw new IOException(String.format("Ошибка при считывании файла %s", pathToCSV), ioe);
        }

        return transactions;
    }

    private Transaction getTransaction(String line) {
        String[] columns = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String accountType = columns[0];
        String accountNumber = columns[1];
        String currency = columns[2];
        LocalDate date = LocalDate.parse(columns[3],
                DateTimeFormatter.ofPattern(DatePatterns.DDMMYY.getTitle()));
        String reference = columns[4];
        String description = columns[5];
        BigDecimal income = new BigDecimal(columns[6]
                .replace(",", ".")
                .replace("\"", "")
        );
        BigDecimal expense = new BigDecimal(columns[7]
                .replace(",", ".")
                .replace("\"", "")
        );

        return new Transaction(accountType, accountNumber, currency, date, reference,
                description, income, expense);
    }

}
