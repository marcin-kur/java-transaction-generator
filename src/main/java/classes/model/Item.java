package classes.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private final String name;
    private final int quantity;
    private final BigDecimal price;

    public Item(String name, int quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    BigDecimal getTotalPrice() {
        return getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }
}
