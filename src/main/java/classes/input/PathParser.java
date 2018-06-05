package classes.input;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
class PathParser {
    Path parseFile(String valueToParse) {
        Path path = Paths.get(valueToParse);
        if (!validateFile(path)) {
            throw new ParseException("File " + valueToParse + " not exists or isn't readable");
        }
        return path;
    }

    Path parseDirectory(String valueToParse) {
        Path path = Paths.get(valueToParse);
        if (!validateDirectory(path)) {
            throw new ParseException("Directory " + valueToParse + " not exists");
        }
        return path;
    }

    boolean validateFile(Path path) {
        return Files.exists(path) && Files.isReadable(path);
    }

    boolean validateDirectory(Path path) {
        return Files.isDirectory(path);
    }
}