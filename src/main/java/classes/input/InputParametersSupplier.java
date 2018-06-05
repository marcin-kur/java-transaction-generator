package classes.input;

import classes.model.Range;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

@Slf4j
@Component
public class InputParametersSupplier implements Supplier<InputParameters> {
    private final Environment env;
    private final PathParser pathParser;
    private final IntegerParser integerParser;
    private final IntegerRangeParser integerRangeParser;
    private final TimestampRangeParser timestampRangeParser;

    @Autowired
    public InputParametersSupplier(Environment env, PathParser pathParser, IntegerParser integerParser, IntegerRangeParser integerRangeParser, TimestampRangeParser timestampRangeParser) {
        this.env = env;
        this.pathParser = pathParser;
        this.integerParser = integerParser;
        this.integerRangeParser = integerRangeParser;
        this.timestampRangeParser = timestampRangeParser;
    }

    @Override
    public InputParameters get() {
        return new InputParameters(
                getCustomerIds(),
                getDateRange(),
                getItemsFile(),
                getItemsCount(),
                getItemsQuantity(),
                getEventsCount(),
                getOutDir(),
                getBroker(),
                getTopic(),
                getQueue()
        );
    }

    private Range<Integer> getCustomerIds() {
        return integerRangeParser.parse(env.getProperty("customerIds", "1:20"));
    }

    private Range<ZonedDateTime> getDateRange() {
        return env.containsProperty("dateRange")
                ? timestampRangeParser.parse(env.getProperty("dateRange"))
                : defaultDateRange();
    }

    private Path getItemsFile() {
        return pathParser.parseFile(env.getProperty("itemsFile", "items.csv"));
    }

    private Range<Integer> getItemsCount() {
        return integerRangeParser.parse(env.getProperty("itemsCount", "1:20"));
    }

    private Range<Integer> getItemsQuantity() {
        return integerRangeParser.parse(env.getProperty("itemsQuantity", "1:20"));
    }

    private int getEventsCount() {
        return integerParser.parse(env.getProperty("eventsCount", "1:20"));
    }

    private Path getOutDir() {
        return pathParser.parseDirectory(env.getProperty("outDir"));
    }

    private String getBroker() {
        return env.getProperty("broker");
    }

    private String getTopic() {
        return env.getProperty("topic");
    }

    private String getQueue() {
        return env.getProperty("queue");
    }

    private Range<ZonedDateTime> defaultDateRange() {
        return new Range<>(
                ZonedDateTime.now().with(LocalTime.MIN),
                ZonedDateTime.now().with(LocalTime.MAX)
        );
    }
}
