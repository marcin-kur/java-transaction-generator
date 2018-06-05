package classes.input;

import classes.model.Range;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class IntegerRangeParser {
    private static final String SEPARATOR = ":";

    Range<Integer> parse(String valueToParse) {
        try {
            String[] parts = valueToParse.split(SEPARATOR);
            return new Range<>(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ParseException(e.getMessage());
        }
    }
}