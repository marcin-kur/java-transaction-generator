package classes.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {

    @Test
    public void shouldCalculateSum() {
        // given
        List<Item> items = Arrays.asList(
                new Item("test", 2, BigDecimal.valueOf(10)),
                new Item("test", 3, BigDecimal.valueOf(5))
        );
        Transaction transaction = new Transaction(1, 1, ZonedDateTime.now(), items);

        // when
        BigDecimal result = transaction.getSum();

        // then
        assertThat(result).isEqualTo(BigDecimal.valueOf(35));
    }
}
