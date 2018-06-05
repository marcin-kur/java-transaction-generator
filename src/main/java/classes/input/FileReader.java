package classes.input;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
class FileReader {
    Stream<String> getFileLines(Path path) throws IOException {
        return Files.lines(path);
    }
}
