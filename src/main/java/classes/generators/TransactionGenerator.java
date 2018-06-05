package classes.generators;

import classes.model.Product;
import classes.model.Range;
import classes.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TransactionGenerator {
    private final IntegerGenerator integerGenerator;
    private final TimestampGenerator timestampGenerator;
    private final ItemsGenerator itemsGenerator;

    @Autowired
    public TransactionGenerator(IntegerGenerator integerGenerator, TimestampGenerator timestampGenerator, ItemsGenerator itemsGenerator) {
        this.integerGenerator = integerGenerator;
        this.timestampGenerator = timestampGenerator;
        this.itemsGenerator = itemsGenerator;
    }

    public List<Transaction> generateTransactions(Range<Integer> customerIds, Range<ZonedDateTime> dateRange, Range<Integer> itemsCount, Range<Integer> itemsQuantity, List<Product> products, int eventsCount) {
        return IntStream.range(1, eventsCount + 1)
                .mapToObj(i -> generateSingleTransaction(i, customerIds, dateRange, itemsCount, itemsQuantity, products))
                .collect(Collectors.toList());
    }

    private Transaction generateSingleTransaction(int id, Range<Integer> customerIds, Range<ZonedDateTime> dateRange, Range<Integer> itemsCount, Range<Integer> itemQuantity, List<Product> products) {
        return new Transaction(
                id,
                integerGenerator.generate(customerIds),
                timestampGenerator.generate(dateRange),
                itemsGenerator.generate(itemsCount, itemQuantity, products)
        );
    }
}
