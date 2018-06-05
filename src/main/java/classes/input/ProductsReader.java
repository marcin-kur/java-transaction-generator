package classes.input;

import classes.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class ProductsReader {

    private final FileReader fileReader;

    @Autowired
    public ProductsReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public List<Product> readProducts(Path itemsFile) {
        log.info("Product parsing started. Filename: " + itemsFile);

        try (Stream<String> lines = fileReader.getFileLines(itemsFile)) {
            return getProducts(lines);
        } catch (IOException | ClassCastException e) {
            log.error("Error during parsing Products. " + e.getMessage());
            throw new ParseException(e.getMessage());
        }
    }

    private List<Product> getProducts(Stream<String> lines) {
        return lines.skip(1)
                .map(this::parseProduct)
                .filter(p -> Optional.ofNullable(p).isPresent())
                .collect(Collectors.toList());
    }

    private Product parseProduct(String line) {
        try {
            String[] elements = line.split(",", 2);
            return new Product(elements[0], new BigDecimal(elements[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            log.error("Error during parsing Product: " + line + ". Error: " + e.getMessage());
            return null;
        }
    }
}