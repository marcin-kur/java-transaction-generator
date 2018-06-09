package classes.output;

import lombok.extern.slf4j.Slf4j;

import javax.jms.*;

@Slf4j
public class JmsQueueProducer extends JmsProducer {

    private final String queueName;

    public JmsQueueProducer(String queueName) {
        this.queueName = queueName;
    }

    @Override
    Destination createDestination(Session session) throws JMSException {
        return session.createQueue(queueName);
    }
}
