package classes.output;

class FileWriterException extends RuntimeException {
    FileWriterException(String message, Exception e) {
        super(message, e);
    }
}
