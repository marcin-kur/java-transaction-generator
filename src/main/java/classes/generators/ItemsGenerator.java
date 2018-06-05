package classes.generators;

import classes.model.Product;
import classes.model.Item;
import classes.model.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ItemsGenerator {
    private final IntegerGenerator integerGenerator;

    @Autowired
    public ItemsGenerator(IntegerGenerator integerGenerator) {
        this.integerGenerator = integerGenerator;
    }

    List<Item> generate(Range<Integer> itemsCount, Range<Integer> itemQuantity, List<Product> products) {
        return IntStream.range(0, integerGenerator.generate(itemsCount))
                .mapToObj(i -> getItem(itemQuantity, products, i))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Item getItem(Range<Integer> itemQuantity, List<Product> products, int i) {
        return new Item(
                products.get(i % products.size()).getName(),
                integerGenerator.generate(itemQuantity),
                products.get(i % products.size()).getPrice()
        );
    }
}
