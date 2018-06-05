package classes.generators;

import classes.model.Item;
import classes.model.Product;
import classes.model.Range;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ItemsGeneratorTest {

    @Test
    public void shouldGenerateItems() {
        // given
        ArrayList<Product> products = new ArrayList<>(Arrays.asList(
                mock(Product.class),
                mock(Product.class),
                mock(Product.class)
        ));
        ItemsGenerator itemsGenerator = new ItemsGenerator(new IntegerGenerator());
        Range<Integer> itemsCount = new Range<>(10, 20);
        Range<Integer> itemsQuantity = new Range<>(20, 30);

        //when
        List<Item> items = itemsGenerator.generate(itemsCount, itemsQuantity, products);

        //then
        assertThat(items.size()).isBetween(10, 20);
        items.forEach(item -> assertThat(item.getQuantity()).isBetween(20, 30));
    }
}