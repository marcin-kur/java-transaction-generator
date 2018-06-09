package classes.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JmsProducers {
    private final Optional<List<JmsProducer>> jmsProducers;

    @Autowired
    public JmsProducers(Optional<List<JmsProducer>> jmsProducers) {
        this.jmsProducers = jmsProducers;
    }

    public void publish(List<String> transactions, String brokerName) {
        jmsProducers.ifPresent(jmsProducers -> jmsProducers
                .forEach(jmsProducer -> transactions.forEach(transaction -> jmsProducer.publish(transaction, brokerName))));
    }
}