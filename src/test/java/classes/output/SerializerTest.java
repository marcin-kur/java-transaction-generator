package classes.output;

import classes.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

public class SerializerTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void shouldSerialize100Transactions() throws JsonProcessingException {
        // given
        Transaction transaction = mock(Transaction.class);
        List<Transaction> transactions = Collections.nCopies(100, transaction);
        Serializer serializer = new Serializer(objectMapper);

        // when
        serializer.serializeTransactions(transactions);

        // then
        verify(objectMapper, times(100)).writeValueAsString(transaction);
    }

    @Test
    public void shouldThrowSerializeException() throws JsonProcessingException {
        // given
        Transaction transaction = mock(Transaction.class);
        List<Transaction> transactions = Collections.singletonList(transaction);
        when(objectMapper.writeValueAsString(transaction)).thenThrow(new RuntimeException("Error"));
        Serializer serializer = new Serializer(objectMapper);

        // given
        Throwable throwable = catchThrowable(() -> serializer.serializeTransactions(transactions));

        //then
        assertThat(throwable).isInstanceOf(SerializeException.class);
    }
}
