package classes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Transaction {
    public int id;

    @JsonProperty("customer_id")
    private int customerId;

    private ZonedDateTime timestamp;

    private List<Item> items;

    private BigDecimal sum;

    public Transaction(int id, int customerId, ZonedDateTime timestamp, List<Item> items) {
        this.id = id;
        this.customerId = customerId;
        this.timestamp = timestamp;
        this.items = items;
        this.sum = calculateSum();
    }

    private BigDecimal calculateSum() {
        return items
                .stream()
                .map(Item::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
