package classes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Item {
    private final String name;
    private final int quantity;
    private final BigDecimal price;

    BigDecimal getTotalPrice() {
        return getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }
}
