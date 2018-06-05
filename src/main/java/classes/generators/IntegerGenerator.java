package classes.generators;

import classes.model.Range;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IntegerGenerator {
    private final Random random;

    public IntegerGenerator() {
        this.random = new Random();
    }

    int generate(Range<Integer> integerRange) {
        return integerRange.getLowerLimit() + random.nextInt(1 + integerRange.getUpperLimit() - integerRange.getLowerLimit());
    }
}