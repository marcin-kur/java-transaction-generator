package classes.input;

import classes.model.Range;
import org.junit.Test;
import org.mockito.Mock;

import java.nio.file.Path;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class InputParametersTest {

    @Mock
    private Range<Integer> integerRange;

    @Mock
    private Range<ZonedDateTime> zonedDateTimeRange;

    @Test
    public void shouldCreateInputParameters() {
        assertThatCode(() -> new InputParameters(
                integerRange,
                zonedDateTimeRange,
                mock(Path.class),
                integerRange,
                integerRange,
                1,
                mock(Path.class),
                "test",
                "test",
                "test"
        )).doesNotThrowAnyException();
    }

    @Test
    public void shouldThrowInputExceptionWhenOutDirAndBrokerAreEmpty() {
        assertThatThrownBy(() -> new InputParameters(
                integerRange,
                zonedDateTimeRange,
                mock(Path.class),
                integerRange,
                integerRange,
                1,
                null,
                null,
                "test",
                "test"
        )).isInstanceOf(InputException.class);
    }

    @Test
    public void shouldThrowInputExceptionWhenTopicAndQueueAreEmptyAndBrokerIsPopulated() {
        assertThatThrownBy(() -> new InputParameters(
                integerRange,
                zonedDateTimeRange,
                mock(Path.class),
                integerRange,
                integerRange,
                1,
                mock(Path.class),
                "test",
                null,
                null
        )).isInstanceOf(InputException.class);
    }
}
