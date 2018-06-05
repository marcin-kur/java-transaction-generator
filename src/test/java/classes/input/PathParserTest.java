package classes.input;

import org.junit.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PathParserTest {
    @Test
    public void shouldParseFile() {
        String pathString = "someDummyValue";
        PathParser mock = mock(PathParser.class);
        when(mock.validateFile(any(Path.class))).thenReturn(true);
        when(mock.parseFile(any(String.class))).thenCallRealMethod();

        assertThatCode(() -> mock.parseFile(pathString)).doesNotThrowAnyException();
    }

    @Test
    public void shouldParseDirectory() {
        String pathString = "someDummyValue";
        PathParser mock = mock(PathParser.class);
        when(mock.validateDirectory(any(Path.class))).thenReturn(true);
        when(mock.parseDirectory(any(String.class))).thenCallRealMethod();

        assertThatCode(() -> mock.parseDirectory(pathString)).doesNotThrowAnyException();
    }

    @Test
    public void shouldThrowExceptionWhileParsingFile() {
        String pathString = "someDummyValue";
        PathParser mock = mock(PathParser.class);
        when(mock.validateFile(any(Path.class))).thenReturn(false);
        when(mock.parseFile(any(String.class))).thenCallRealMethod();

        assertThatThrownBy(() -> mock.parseFile(pathString)).isInstanceOf(ParseException.class);
    }

    @Test
    public void shouldThrowExceptionWhileParsingDirectory() {
        String pathString = "someDummyValue";
        PathParser mock = mock(PathParser.class);
        when(mock.validateFile(any(Path.class))).thenReturn(false);
        when(mock.parseDirectory(any(String.class))).thenCallRealMethod();

        assertThatThrownBy(() -> mock.parseDirectory(pathString)).isInstanceOf(ParseException.class);
    }
}
