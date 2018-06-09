package classes.output;

import classes.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Serializer {

    private final ObjectMapper objectMapper;

    @Autowired
    public Serializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<String> serializeTransactions(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> {
            try {
                return objectMapper.writeValueAsString(transaction);
            } catch (Exception e) {
                SerializeException exception = new SerializeException("Error during serializing transaction");
                log.error(exception.getMessage());
                throw exception;
            }
        }).collect(Collectors.toList());
    }
}
