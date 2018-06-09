package classes.output;

import classes.input.InputParameters;
import classes.input.InputParametersSupplier;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileWriterTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private InputParametersSupplier inputParametersSupplier;

    @Mock
    private InputParameters inputParameters;

    @Mock
    private FileWriterFactory fileWriterFactory;

    @Mock
    private Writer writer;

    @Test
    public void shouldSuccessfullyWrite1Transaction() throws IOException {
        // given
        List<String> transactions = Collections.singletonList("Test");
        when(inputParameters.getOutDir()).thenReturn(Paths.get("Test"));
        when(inputParametersSupplier.get()).thenReturn(inputParameters);
        when(fileWriterFactory.getFileWriter(any())).thenReturn(writer);
        FileWriter fileWriter = new FileWriter(inputParametersSupplier, fileWriterFactory);

        // when
        fileWriter.write(transactions);

        //then
        verify(writer, times(1)).write(anyString());
    }

    @Test
    public void shouldSuccessfullyWrite100Transaction() throws IOException {
        // given
        List<String> transactions = Collections.nCopies(100, "Test");
        when(inputParameters.getOutDir()).thenReturn(Paths.get("Test"));
        when(inputParametersSupplier.get()).thenReturn(inputParameters);
        when(fileWriterFactory.getFileWriter(any())).thenReturn(writer);
        FileWriter fileWriter = new FileWriter(inputParametersSupplier, fileWriterFactory);

        // when
        fileWriter.write(transactions);

        //then
        verify(writer, times(100)).write(anyString());
    }

    @Test
    public void shouldWriteExceptionOnWrite() throws IOException {
        List<String> transactions = Collections.singletonList("Test");
        when(inputParameters.getOutDir()).thenReturn(Paths.get("Test"));
        when(inputParametersSupplier.get()).thenReturn(inputParameters);
        when(fileWriterFactory.getFileWriter(any())).thenThrow(new IOException("Error"));
        FileWriter fileWriter = new FileWriter(inputParametersSupplier, fileWriterFactory);

        // given
        Throwable throwable = catchThrowable(() -> fileWriter.write(transactions));

        //then
        assertThat(throwable).isInstanceOf(FileWriterException.class);
    }
}
