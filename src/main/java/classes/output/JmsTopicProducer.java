package classes.output;

import lombok.extern.slf4j.Slf4j;

import javax.jms.*;

@Slf4j
public class JmsTopicProducer extends JmsProducer {

    private final String topicName;

    public JmsTopicProducer(String topicName) {
        this.topicName = topicName;
    }

    @Override
    Destination createDestination(Session session) throws JMSException {
        return session.createTopic(topicName);
    }
}
