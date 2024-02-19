package br.com.macedocaio.application.generators;

import br.com.macedocaio.application.exceptions.InvalidGenerationStrategyUseException;
import br.com.macedocaio.application.exceptions.StringGeneratorException;
import br.com.macedocaio.application.strategies.GenerationStrategy;
import br.com.macedocaio.application.utils.LongUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class StringGeneratorBuilderUnitTests {

    private static StringGeneratorBuilder builder;
    private static StringGenerator stringGenerator;

    @BeforeAll
    public static void setup() {
        stringGenerator = getDefaultInstance();
    }

    @AfterAll
    public static void cleanup() {
        builder = null;
        stringGenerator = null;
    }

    @BeforeEach
    public void initEach() {
        builder = StringGeneratorBuilder.getBuilder();
    }

    @Test
    public void should_Create_Default_Instance() {
        StringGenerator generator = builder.build();

        assertNotNull(generator);
        assertEquals(stringGenerator, generator);
    }

    @ParameterizedTest
    @EnumSource(value = GenerationStrategy.class, mode = EnumSource.Mode.EXCLUDE, names = {"CUSTOM"})
    public void should_Create_String_Generator_With_Different_Strategy(GenerationStrategy strategy) {
        StringGenerator generator = builder.setStrategy(strategy).build();

        assertNotNull(generator);
        assertEquals(strategy, generator.getStrategy());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void should_Set_ShuffleCharset(Boolean shuffleCharset) {
        StringGenerator generator = builder.setShuffleCharset(shuffleCharset).build();
        assertNotNull(generator);
        assertEquals(shuffleCharset, generator.getShuffleCharset());
    }

    @Test
    public void should_Set_RandomBitFlag() {
        long randomBitFlag = 0x43FF_4300_57FF_5700L;

        StringGenerator generator = builder.setRandomBitFlag(randomBitFlag).build();

        assertNotNull(generator);
        assertEquals(randomBitFlag, generator.getRandomBitFlag());
    }

    @Test
    public void should_Set_VariantBitFlag() {
        long variantBitFlag = 0x1200_2400_3600_4800L;

        StringGenerator generator = builder.setVariantBitFlag(variantBitFlag).build();

        assertNotNull(generator);
        assertEquals(variantBitFlag, generator.getVariantBitFlag());
    }

    @Test
    public void should_Create_Custom_String_Generator() {
        GenerationStrategy strategy = GenerationStrategy.CUSTOM;
        String elderFutharkCharset = "fuþarkgwhnijæpzstbemlŋod";

        StringGenerator generator = builder.setStrategy(strategy).setCharset(elderFutharkCharset).build();

        assertNotNull(generator);
        assertEquals(strategy, generator.getStrategy());
        assertEquals(elderFutharkCharset, generator.getCharset());
    }

    @Test
    public void should_Throw_InvalidGenerationStrategyUseException() {
        GenerationStrategy strategy = GenerationStrategy.CUSTOM;

        Throwable throwable = assertThrows(StringGeneratorException.class, () -> builder.setStrategy(strategy).build());

        assertEquals(InvalidGenerationStrategyUseException.class, throwable.getClass());
    }

    @Test
    public void should_Not_Set_Value_For_Strategy() {
        StringGenerator generator = builder.setStrategy(null).build();
        assertNotNull(generator);
        assertNotNull(generator.getStrategy());
    }

    @Test
    public void should_Not_Set_Value_For_ShuffleStrategy() {
        StringGenerator generator = builder.setShuffleCharset(null).build();
        assertNotNull(generator);
        assertNotNull(generator.getShuffleCharset());
    }

    @Test
    public void should_Not_Set_Value_For_RandomBitFlag() {
        StringGenerator generator = builder.setRandomBitFlag(null).build();
        assertNotNull(generator);
        assertNotNull(generator.getRandomBitFlag());
    }

    @Test
    public void should_Not_Set_Value_For_VariantBitFlag() {
        StringGenerator generator = builder.setVariantBitFlag(null).build();
        assertNotNull(generator);
        assertNotNull(generator.getVariantBitFlag());
    }

    private static StringGenerator getDefaultInstance() {
        GenerationStrategy strategy = GenerationStrategy.ALPHANUMERIC;
        Boolean shuffleCharset = false;
        Long randomBitFlag = LongUtils.RANDOM_BIT_FLAG;
        Long variantBitFlag = LongUtils.VARIANT_BIT_FLAG;

        return new StringGenerator(strategy, shuffleCharset, randomBitFlag, variantBitFlag);
    }
}
