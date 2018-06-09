package classes.output;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@Slf4j
public abstract class JmsProducer {

    abstract Destination createDestination(Session session) throws Exception;

    void publish(String message, String brokerName) {
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerName);
        try {
            Connection conn = factory.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = createDestination(session);
            MessageProducer messageProducer = session.createProducer(destination);
            sendMessage(message, session, messageProducer);
            session.close();
            conn.close();
        } catch (Exception e) {
            log.error("Publish message failed:" + e.getMessage());
            throw new JmsProducerException("Publish message failed:" + e.getMessage());
        }
    }

    private void sendMessage(String s, Session session, MessageProducer producer) throws Exception {
        Message message = session.createTextMessage(s);
        producer.send(message);
    }
}
