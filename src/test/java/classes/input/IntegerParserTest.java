package classes.input;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IntegerParserTest {

    @Test
    public void shouldParse() {
        // given
        IntegerParser parser = new IntegerParser();
        String value = "5";

        // when
        int integer = parser.parse(value);

        // then
        assertThat(integer).isEqualTo(5);
    }

    @Test
    public void shouldFail() {
        assertThatThrownBy(() -> new IntegerParser().parse("x")).isInstanceOf(ParseException.class);
    }
}
