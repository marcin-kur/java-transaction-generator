package classes.input;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class IntegerParser {
    int parse(String valueToParse) {
        try {
            return Integer.valueOf(valueToParse);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            throw new ParseException(e.getMessage());
        }
    }
}