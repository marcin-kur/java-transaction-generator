package classes.generators;

import classes.model.Range;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerGeneratorTest {

    @Test
    public void shouldGenerateInteger() {
        // given
        Range<Integer> range = new Range<>(1, 10);
        IntegerGenerator generator = new IntegerGenerator();

        // when
        List<Integer> integerList = IntStream.of(1000)
                .mapToObj(i -> generator.generate(range))
                .collect(Collectors.toList());

        // then
        integerList.forEach(i -> assertThat(i).isBetween(1, 10));
    }
}
