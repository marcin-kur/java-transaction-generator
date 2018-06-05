package classes.input;

import classes.model.Range;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IntegerRangeParserTest {

    @Test
    public void shouldParse() {
        // given
        IntegerRangeParser parser = new IntegerRangeParser();
        String value = "1:5";

        // when
        Range<Integer> integer = parser.parse(value);

        // then
        assertThat(integer.getLowerLimit()).isEqualTo(1);
        assertThat(integer.getUpperLimit()).isEqualTo(5);
    }

    @Test
    public void shouldFail() {
        assertThatThrownBy(() -> new IntegerRangeParser().parse("x")).isInstanceOf(ParseException.class);
    }
}