package br.com.macedocaio.application.generators;

import br.com.macedocaio.application.exceptions.InvalidGenerationStrategyUseException;
import br.com.macedocaio.application.strategies.GenerationStrategy;
import br.com.macedocaio.application.utils.LongUtils;

/**
 * Class used to build a {@link StringGenerator} instance.
 */
public final class StringGeneratorBuilder {

    private GenerationStrategy strategy;
    private Boolean shuffleCharset;
    private Long randomBitFlag;
    private Long variantBitFlag;
    private String charset;

    private StringGeneratorBuilder() {
        strategy = GenerationStrategy.ALPHANUMERIC;
        shuffleCharset = false;
        randomBitFlag = LongUtils.RANDOM_BIT_FLAG;
        variantBitFlag = LongUtils.VARIANT_BIT_FLAG;
    }

    /**
     * Used to crate a new builder instance
     *
     * @return new builder instance
     */
    public static StringGeneratorBuilder getBuilder() {
        return new StringGeneratorBuilder();
    }

    /**
     * Set {@link StringGeneratorBuilder#strategy} variable that be applied in the new instance of
     * {@link StringGenerator}
     *
     * @param strategy that will be used to generate random strings
     *
     * @return self class instance
     */
    public StringGeneratorBuilder setStrategy(GenerationStrategy strategy) {
        if (strategy == null) {
            return this;
        }
        this.strategy = strategy;
        return this;
    }

    /**
     * Set {@link StringGeneratorBuilder#shuffleCharset} variable that be applied in the new instance of
     * {@link StringGenerator}
     *
     * @param shuffleCharset used to tell if the charset need a shuffle before string generation
     *
     * @return self class instance
     */
    public StringGeneratorBuilder setShuffleCharset(Boolean shuffleCharset) {
        if (shuffleCharset == null) {
            return this;
        }
        this.shuffleCharset = shuffleCharset;
        return this;
    }

    /**
     * Set {@link StringGeneratorBuilder#randomBitFlag} variable that be applied in the new instance of
     * {@link StringGenerator}
     *
     * @param randomBitFlag used to change bitwise mask when generating a new long value
     *
     * @return self class instance
     */
    public StringGeneratorBuilder setRandomBitFlag(Long randomBitFlag) {
        if (randomBitFlag == null) {
            return this;
        }
        this.randomBitFlag = randomBitFlag;
        return this;
    }

    /**
     * Set {@link StringGeneratorBuilder#variantBitFlag} variable that be applied in the new instance of
     * {@link StringGenerator}
     *
     * @param variantBitFlag used to change bitwise mask when generating a new long value
     *
     * @return self class instance
     */
    public StringGeneratorBuilder setVariantBitFlag(Long variantBitFlag) {
        if (variantBitFlag == null) {
            return this;
        }
        this.variantBitFlag = variantBitFlag;
        return this;
    }

    /**
     * Set {@link StringGeneratorBuilder#charset} variable that be applied in the new instance of
     * {@link StringGenerator}
     * <p>
     * Note: This method must be called if {@link StringGeneratorBuilder#strategy} is set to
     * {@link GenerationStrategy#CUSTOM}
     *
     * @param charset that will be used with custom string generation
     *
     * @return self class instance
     */
    public StringGeneratorBuilder setCharset(String charset) {
        if (charset == null) {
            return this;
        }
        this.charset = charset;
        return this;
    }

    /**
     * Creates a new instance of {@link StringGenerator}
     *
     * @return new {@link StringGenerator instance}
     */
    public StringGenerator build() {
        if (GenerationStrategy.CUSTOM == strategy) {
            if (charset == null) {
                throw new InvalidGenerationStrategyUseException("Provide a custom charset to use CUSTOM strategy.");
            }
            return new StringGenerator(strategy, shuffleCharset, randomBitFlag, variantBitFlag, charset);
        }
        return new StringGenerator(strategy, shuffleCharset, randomBitFlag, variantBitFlag);
    }
}
