package classes.model;

import lombok.Data;

@Data
public class Range<T> {
    private final T lowerLimit;
    private final T upperLimit;

    public Range(T lowerLimit, T upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }
}