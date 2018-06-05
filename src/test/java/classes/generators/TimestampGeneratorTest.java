package classes.generators;

import classes.model.Range;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TimestampGeneratorTest {

    @Test
    public void shouldGenerateTimestamp() {
        // given
        Range<ZonedDateTime> range = new Range<>(ZonedDateTime.now(), ZonedDateTime.now().plusDays(1));
        TimestampGenerator generator = new TimestampGenerator();

        // when
        List<ZonedDateTime> timestampList = IntStream.of(1000)
                .mapToObj(i -> generator.generate(range))
                .collect(Collectors.toList());

        // then
        timestampList.forEach(
                i -> assertThat(i).isBetween(ZonedDateTime.now(), ZonedDateTime.now().plusDays(1)));
    }
}