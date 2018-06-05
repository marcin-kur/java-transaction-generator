package classes.input;

import classes.model.Range;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.Optional;

@Data
@Slf4j
public class InputParameters {
    private final Range<Integer> customerIds;
    private final Range<ZonedDateTime> dateRange;
    private final Path itemsFile;
    private final Range<Integer> itemsCount;
    private final Range<Integer> itemsQuantity;
    private final int eventsCount;
    private final Path outDir;
    private final String broker;
    private final String topic;
    private final String queue;

    InputParameters(
            Range<Integer> customerIds,
            Range<ZonedDateTime> dateRange,
            Path itemsFile,
            Range<Integer> itemsCount,
            Range<Integer> itemsQuantity,
            int eventsCount,
            Path outDir,
            String broker,
            String topic,
            String queue) {
        validateOutDir(outDir, broker);
        validateBroker(broker, topic, queue);

        this.customerIds = customerIds;
        this.dateRange = dateRange;
        this.itemsFile = itemsFile;
        this.itemsCount = itemsCount;
        this.itemsQuantity = itemsQuantity;
        this.eventsCount = eventsCount;
        this.outDir = outDir;
        this.broker = broker;
        this.topic = topic;
        this.queue = queue;
    }

    private void validateOutDir(Path outDir, String broker) {
        if (!Optional.ofNullable(outDir).isPresent() && !Optional.ofNullable(broker).isPresent()) {
            InputException inputException = new InputException("Both out directory and broker can't be null");
            log.error(inputException.getMessage());
            throw inputException;
        }
    }

    private void validateBroker(String broker, String topic, String queue) {
        if (Optional.ofNullable(broker).isPresent() && (!Optional.ofNullable(topic).isPresent() || !Optional.ofNullable(queue).isPresent())) {
            InputException inputException = new InputException("Both topic and queue can't be empty when broker is populated");
            log.error(inputException.getMessage());
            throw inputException;
        }
    }
}
