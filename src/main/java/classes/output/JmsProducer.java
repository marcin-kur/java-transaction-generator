package classes.output;

public interface JmsProducer {
    void publish(String message, String brokerName);
}
