package classes.generators;

import classes.model.Product;
import classes.model.Range;
import classes.model.Transaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionGeneratorTest {

    @Test
    public void shouldReadProductsFromFile() {
        // given
        Range<Integer> customerIds = new Range<>(1, 20);
        Range<ZonedDateTime> dateRange = new Range<>(ZonedDateTime.now(), ZonedDateTime.now());
        Range<Integer> itemsCount = new Range<>(1, 20);
        Range<Integer> itemsQuantity = new Range<>(1, 20);
        int eventsCount = 100;
        ArrayList<Product> products = new ArrayList<>(Arrays.asList(
                new Product("test", BigDecimal.ONE),
                new Product("test", BigDecimal.ONE)
        ));

        IntegerGenerator integerGenerator = new IntegerGenerator();
        TimestampGenerator timestampGenerator = new TimestampGenerator();
        ItemsGenerator itemsGenerator = new ItemsGenerator(integerGenerator);
        TransactionGenerator transactionGenerator = new TransactionGenerator(integerGenerator, timestampGenerator, itemsGenerator);
        //when
        List<Transaction> transactions = transactionGenerator.generateTransactions(customerIds, dateRange, itemsCount, itemsQuantity, products, eventsCount);

        //then
        assertThat(transactions.size()).isEqualTo(eventsCount);
        transactions.forEach(
                transaction -> {
                    assertThat(transaction.getCustomerId()).isBetween(customerIds.getLowerLimit(), customerIds.getUpperLimit());
                    assertThat(transaction.getTimestamp()).isBetween(dateRange.getLowerLimit(), dateRange.getUpperLimit());
                    assertThat(transaction.getItems().size()).isBetween(itemsCount.getLowerLimit(), itemsCount.getUpperLimit());
                }
        );
    }
}

