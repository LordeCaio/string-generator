package br.com.macedocaio.application.generators;

import br.com.macedocaio.application.exceptions.InvalidStringLengthException;
import br.com.macedocaio.application.exceptions.StringGeneratorException;
import br.com.macedocaio.application.strategies.GenerationStrategy;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class StringGeneratorUnitTests {

    private static StringGenerator stringGenerator;
    private static final StringGeneratorBuilder builder = StringGeneratorBuilder.getBuilder();

    private final int maxLength = 10;

    @Test
    public void should_Generate_Random_String() {
        stringGenerator = builder.build();

        String actual = stringGenerator.generate(maxLength);
        assertNotNull(actual);
    }

    @Test
    public void should_Generate_Random_Numeric_String() {
        stringGenerator = builder.setStrategy(GenerationStrategy.NUMERIC).build();

        Pattern pattern = Pattern.compile("\\d+");
        String actual = stringGenerator.generate(maxLength);

        assertNotNull(actual);
        assertTrue(pattern.matcher(actual).matches());
    }

    @Test
    public void should_Generate_Random_Alphabetical_String() {
        stringGenerator = builder.setStrategy(GenerationStrategy.ALPHA).build();

        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        String actual = stringGenerator.generate(maxLength);

        assertNotNull(actual);
        assertTrue(pattern.matcher(actual).matches());
    }

    @Test
    public void should_Generate_Random_Custom_String() {
        String elderFutharkCharset = "fuþarkgwhnijæpzstbemlŋod";

        stringGenerator = builder.setStrategy(GenerationStrategy.CUSTOM).setCharset(elderFutharkCharset).build();

        Pattern pattern = Pattern.compile("[" + elderFutharkCharset + "]+");
        String actual = stringGenerator.generate(maxLength);

        assertNotNull(actual);
        assertTrue(pattern.matcher(actual).matches());
    }

    @Test
    public void should_Throws_When_Length_Less_Than_One() {
        Throwable throwable = assertThrows(StringGeneratorException.class, () -> stringGenerator.generate(0));

        assertEquals(InvalidStringLengthException.class, throwable.getClass());
    }

    @AfterAll
    public static void after_All() {
        stringGenerator = null;
    }
}
