package classes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Range<T> {
    private final T lowerLimit;
    private final T upperLimit;
}