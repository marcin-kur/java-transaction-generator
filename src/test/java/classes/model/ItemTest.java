package classes.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {

    @Test
    public void shouldCalculateTotalSum() {
        // given
        Item item = new Item("item", 2, BigDecimal.valueOf(5));

        // when
        BigDecimal result = item.getTotalPrice();

        // then
        assertThat(result).isEqualTo(BigDecimal.valueOf(10));
    }
}
