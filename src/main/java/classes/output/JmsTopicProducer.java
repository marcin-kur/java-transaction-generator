package classes.output;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@Slf4j
public class JmsTopicProducer implements JmsProducer {

    private final String topicName;

    public JmsTopicProducer(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public void publish(String message, String brokerName) {
        log.info("Publish Topic:"+ topicName + brokerName);
//        ConnectionFactory f = new ActiveMQConnectionFactory(brokerName);
//        try {
//            Connection conn = f.createConnection();
//            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            Topic topic = session.createTopic(topicName);
//            MessageProducer messageProducer = session.createProducer(topic);
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
