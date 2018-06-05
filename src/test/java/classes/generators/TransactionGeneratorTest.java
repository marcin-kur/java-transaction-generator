package classes.generators;

import classes.model.Product;
import classes.model.Range;
import classes.model.Transaction;
import org.junit.Test;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TransactionGeneratorTest {

    @Mock
    private Range<Integer> customerIds;

    @Mock
    private Range<ZonedDateTime> dateRange;

    @Mock
    private Range<Integer> itemsCount;

    @Mock
    private Range<Integer> itemsQuantity;

    @Mock
    private int eventsCount;

    @Test
    public void shouldReadProductsFromFile() {
        // given
        ArrayList<Product> products = new ArrayList<>(Arrays.asList(
                mock(Product.class),
                mock(Product.class),
                mock(Product.class)
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

