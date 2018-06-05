package classes.output;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@Slf4j
public class JmsQueueProducer implements JmsProducer {

    private final String queueName;

    public JmsQueueProducer(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public void publish(String message, String brokerName) {
        log.info("Publish Queue:"+ queueName + brokerName);
        log.info(message);
//        ConnectionFactory f = new ActiveMQConnectionFactory(brokerName);
//        try {
//            Connection conn = f.createConnection();
//            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            Queue queue = session.createQueue(queueName);
//            MessageProducer messageProducer = session.createProducer(queue);
//            sendMessage(message, session, messageProducer);
//            session.close();
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private static void sendMessage(String s, Session session, MessageProducer producer) throws Exception {
        Message message = session.createTextMessage(s);
        producer.send(message);
    }
}
