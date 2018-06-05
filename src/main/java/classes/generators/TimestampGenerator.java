package classes.generators;

import classes.model.Range;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class TimestampGenerator {
    public ZonedDateTime generate(Range<ZonedDateTime> timestampRange) {
        long until = timestampRange.getLowerLimit().until(timestampRange.getUpperLimit(), ChronoUnit.MILLIS);
        long randomLong = (long) (Math.random() * (until));
        return timestampRange.getLowerLimit().plus(randomLong, ChronoUnit.MILLIS);
    }
}
