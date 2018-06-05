package classes.launcher;

import classes.generators.TransactionGenerator;
import classes.input.InputParameters;
import classes.input.ProductsReader;
import classes.model.Product;
import classes.model.Transaction;
import classes.output.FileWriter;
import classes.output.JmsProducers;
import classes.output.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@Component
class TransactionGeneratorStarter {
    private final Supplier<InputParameters> inputParametersSupplier;
    private final ProductsReader productsReader;
    private final TransactionGenerator transactionGenerator;
    private final Serializer serializer;
    private final JmsProducers jmsProducers;
    private final FileWriter fileWriter;

    @Autowired
    TransactionGeneratorStarter(Supplier<InputParameters> inputParametersSupplier, ProductsReader productsReader, TransactionGenerator transactionGenerator, Serializer serializer, JmsProducers jmsProducers, FileWriter fileWriter) {
        this.inputParametersSupplier = inputParametersSupplier;
        this.productsReader = productsReader;
        this.transactionGenerator = transactionGenerator;
        this.serializer = serializer;
        this.jmsProducers = jmsProducers;
        this.fileWriter = fileWriter;
    }

    void start() {
        InputParameters inputParameters = inputParametersSupplier.get();
        List<Product> products = productsReader.readProducts(inputParameters.getItemsFile());
        List<Transaction> transactions = transactionGenerator.generateTransactions(
                inputParameters.getCustomerIds(),
                inputParameters.getDateRange(),
                inputParameters.getItemsCount(),
                inputParameters.getItemsQuantity(),
                products,
                inputParameters.getEventsCount()
        );
        List<String> transactionOutput = serializer.serializeTransactions(transactions);
        jmsProducers.publish(transactionOutput, inputParameters.getBroker());
        fileWriter.write(transactionOutput);
    }
}
