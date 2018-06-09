package classes.output;

import classes.input.InputParametersSupplier;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
@Component
public class FileWriter {

    private static final String FILE_PREFIX = "transaction_";

    private final Path outDir;
    private final FileWriterFactory fileWriterFactory;

    @Autowired
    public FileWriter(@NotNull InputParametersSupplier inputParametersSupplier, FileWriterFactory fileWriterFactory) {
        this.outDir = inputParametersSupplier.get().getOutDir();
        this.fileWriterFactory = fileWriterFactory;
    }

    public void write(List<String> transactions) {
        IntStream.range(1, transactions.size() + 1).forEach(id -> writeTransaction(id, transactions.get(id - 1)));
    }

    void writeTransaction(int id, String transaction) {
        Path transactionPath = getTransactionPath(id);
        try (Writer writer = fileWriterFactory.getFileWriter(transactionPath)) {
            writer.write(transaction);
        } catch (IOException e) {
            log.error("Exception thrown during writing transaction to file. ", e);
            throw new FileWriterException("Exception thrown during writing transaction to file. ", e);
        }
    }

    Path getTransactionPath(int index) {
        return outDir.resolve(FILE_PREFIX + index);
    }
}