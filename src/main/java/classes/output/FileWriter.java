package classes.output;

import classes.input.InputParametersSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
public class FileWriter {

    private static final String FILE_PREFIX = "transaction_";

    private final Path outDir;
    private final FileWriterFactory fileWriterFactory;

    @Autowired
    public FileWriter(InputParametersSupplier inputParametersSupplier, FileWriterFactory fileWriterFactory) {
        this.outDir = inputParametersSupplier.get().getOutDir();
        this.fileWriterFactory = fileWriterFactory;
    }

    public void write(List<String> transactions) {
        IntStream.of(transactions.size()).forEach(index -> {
            Path transactionPath = getTransactionPath(index);
            String transaction = transactions.get(index - 1);
            try (Writer writer = fileWriterFactory.getFileWriter(transactionPath)) {
                writer.write(transaction);
            } catch (IOException e) {
                log.error("Exception thrown during writing transaction to file. ", e);
                throw new FileWriterException("Exception thrown during writing transaction to file. ", e);
            }
        });
    }

    private Path getTransactionPath(int index) {
        return outDir.resolve(FILE_PREFIX + index);
    }
}