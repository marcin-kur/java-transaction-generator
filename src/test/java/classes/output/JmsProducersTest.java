package classes.output;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JmsProducersTest {

    @Test
    public void shouldPublishMessages() {
        // given
        JmsProducer jmsProducer = mock(JmsProducer.class);
        List<JmsProducer> jmsProducerList = Collections.singletonList(jmsProducer);
        Optional<List<JmsProducer>> jmsProducerListOptional = Optional.of(jmsProducerList);
        JmsProducers jmsProducers = new JmsProducers(jmsProducerListOptional);
        List<String> transactions = Collections.nCopies(100, "Test");

        // when
        jmsProducers.publish(transactions, "Test");

        // then
        verify(jmsProducer, times(100)).publish(anyString(), anyString());
    }

    @Test
    public void shouldThrowJmsProducerException() throws Exception {
        // given
        JmsProducer jmsProducer = mock(JmsProducer.class);
        doCallRealMethod().when(jmsProducer).publish(anyString(), anyString());
        doThrow(new RuntimeException("Error")).when(jmsProducer).createDestination(any());
        List<JmsProducer> jmsProducerList = Collections.singletonList(jmsProducer);
        Optional<List<JmsProducer>> jmsProducerListOptional = Optional.of(jmsProducerList);
        JmsProducers jmsProducers = new JmsProducers(jmsProducerListOptional);
        List<String> transactions = Collections.nCopies(100, "Test");

        // when
        Throwable throwable = catchThrowable(() ->  jmsProducers.publish(transactions, "Test"));

        //then
        assertThat(throwable).isInstanceOf(JmsProducerException.class);
    }
}
