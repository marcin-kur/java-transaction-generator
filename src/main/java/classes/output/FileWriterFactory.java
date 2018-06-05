package classes.output;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;

@Component
class FileWriterFactory {
    Writer getFileWriter(Path filePath) throws IOException {
        return new FileWriter(filePath.toString());
    }
}
