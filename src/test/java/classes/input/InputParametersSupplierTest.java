package classes.input;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.core.env.Environment;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InputParametersSupplierTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Environment env;

    @Mock
    private PathParser pathParser;

    @Mock
    private IntegerParser integerParser;

    @Mock
    private IntegerRangeParser integerRangeParser;

    @Mock
    private TimestampRangeParser timestampRangeParser;

    @Test
    public void shouldCallWhenSupplyInputParameters() {
        when(env.getProperty("customerIds", "1:20")).thenReturn("1:20");
        when(env.containsProperty("dateRange")).thenReturn(true);
        when(env.getProperty("dateRange")).thenReturn("2018-03-08T00:00:00.000-0100\":\"2018-03-08T23:59:59.999-0100");
        when(env.getProperty("itemsFile", "items.csv")).thenReturn("items.csv");
        when(env.getProperty("itemsCount", "1:20")).thenReturn("1:20");
        when(env.getProperty("itemsQuantity", "1:20")).thenReturn("1:20");
        when(env.getProperty("eventsCount", "100")).thenReturn("100");
        when(env.getProperty("outDir")).thenReturn("outDir");
        when(env.getProperty("broker")).thenReturn("broker");
        when(env.getProperty("topic")).thenReturn("topic");
        when(env.getProperty("queue")).thenReturn("queue");
        InputParametersSupplier inputParametersSupplier =
                new InputParametersSupplier(env, pathParser, integerParser, integerRangeParser, timestampRangeParser);

        inputParametersSupplier.get();

        verify(env, times(5)).getProperty(anyString(), anyString());
        verify(env, times(5)).getProperty(anyString());
        verify(pathParser, times(1)).parseDirectory(anyString());
        verify(pathParser, times(1)).parseFile(anyString());
        verify(integerParser, times(1)).parse(anyString());
        verify(integerRangeParser, times(3)).parse(anyString());
        verify(timestampRangeParser, times(1)).parse(anyString());
    }
}
