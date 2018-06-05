package classes.input;

public enum FileFormat {
    JSON, XML, YAML;

    public String getFileExtension() {
        return "." + this.toString().toLowerCase();
    }
}