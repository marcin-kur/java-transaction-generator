package classes.input;

import classes.model.Product;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductReaderTest {

    @Mock
    private Path path;

    @Test
    public void shouldReadThreeProductsFromFile() {
        // given
        FileReader mock = mock(FileReader.class);
        assertThatCode(() ->
                when(mock.getFileLines(path)).then(i -> Stream.of(
                        "name,price",
                        "\"mleko 3% 1l\",2.30",
                        "\"bułeczka\",1.20",
                        "\"chleb biały\",2.20")
                )).doesNotThrowAnyException();

        ProductsReader productsReader = new ProductsReader(mock);

        //when
        List<Product> products = productsReader.readProducts(path);

        //then
        assertThat(products.size()).isEqualTo(3);
    }

    @Test
    public void shouldThrowParseExceptionWhenPriceIsInvalid() {
        FileReader mock = mock(FileReader.class);
        assertThatCode(() ->
                when(mock.getFileLines(path)).then(i -> Stream.of(
                        "name,price",
                        "\"mleko 3% 1l\",mleko")
                )).doesNotThrowAnyException();

        ProductsReader productsReader = new ProductsReader(mock);

        assertThatThrownBy(() ->
                productsReader.readProducts(path)
        ).isInstanceOf(ParseException.class);
    }

    @Test
    public void shouldThrowParseExceptionWhenIOExceptionIsThrown() {
        FileReader mock = mock(FileReader.class);
        assertThatCode(() ->
                when(mock.getFileLines(path)).then(i -> {
                    throw new IOException();
                })).doesNotThrowAnyException();

        ProductsReader productsReader = new ProductsReader(mock);

        assertThatThrownBy(() ->
                productsReader.readProducts(path)
        ).isInstanceOf(ParseException.class);
    }

    @Test
    public void shouldThrowParseExceptionWhenFileIsEmpty() {
        FileReader mock = mock(FileReader.class);
        assertThatCode(() ->
                when(mock.getFileLines(path)).then(i -> "")).doesNotThrowAnyException();


        ProductsReader productsReader = new ProductsReader(mock);

        assertThatThrownBy(() ->
                productsReader.readProducts(path)
        ).isInstanceOf(ParseException.class);
    }

    @Test
    public void shouldThrowParseExceptionFileHasOnlyHeader() {
        FileReader mock = mock(FileReader.class);
        assertThatCode(() ->
                when(mock.getFileLines(path)).then(i -> Stream.of(
                        "name,price")
                )).doesNotThrowAnyException();

        ProductsReader productsReader = new ProductsReader(mock);

        assertThatThrownBy(() ->
                productsReader.readProducts(path)
        ).isInstanceOf(ParseException.class);
    }

    @Test
    public void shouldThrowParseExceptionWhenPriceIsEmpty() {
        FileReader mock = mock(FileReader.class);
        assertThatCode(() ->
                when(mock.getFileLines(path)).then(i -> Stream.of(
                        "name,price",
                        "\"mleko 3% 1l\"")
                )).doesNotThrowAnyException();

        ProductsReader productsReader = new ProductsReader(mock);

        assertThatThrownBy(() ->
                productsReader.readProducts(path)
        ).isInstanceOf(ParseException.class);
    }
}
