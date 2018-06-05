package classes.input;

import classes.model.Range;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TimestampRangeParserTest {
    @Test
    public void shouldParse() {
        // given
        TimestampRangeParser parser = new TimestampRangeParser();
        String value = "2018-03-08T00:00:00.000-0100:2018-03-09T00:00:00.000-0100";

        // when
        Range<ZonedDateTime> integer = parser.parse(value);

        // then
        assertThat(integer.getLowerLimit()).isEqualTo(ZonedDateTime.of(2018,3,8,0,0,0,0, ZoneId.of("Etc/GMT+1")));
        assertThat(integer.getUpperLimit()).isEqualTo(ZonedDateTime.of(2018,3,9,0,0,0,0, ZoneId.of("Etc/GMT+1")));
    }

    @Test
    public void shouldFail() {
        assertThatThrownBy(() -> new TimestampRangeParser().parse("x")).isInstanceOf(ParseException.class);
    }
}
