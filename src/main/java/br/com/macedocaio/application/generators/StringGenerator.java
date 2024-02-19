package br.com.macedocaio.application.generators;

import br.com.macedocaio.application.exceptions.InvalidStringLengthException;
import br.com.macedocaio.application.strategies.GenerationStrategy;
import br.com.macedocaio.application.utils.LongUtils;
import br.com.macedocaio.application.utils.RandomUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class used to generate random strings
 */
public class StringGenerator {

    private final GenerationStrategy strategy;
    private final Boolean shuffleCharset;
    private final Long randomBitFlag;
    private final Long variantBitFlag;

    private String charset;

    StringGenerator(GenerationStrategy strategy, Boolean shuffleCharset, Long randomBitFlag, Long variantBitFlag) {
        this.strategy = strategy;
        this.shuffleCharset = shuffleCharset;
        this.randomBitFlag = randomBitFlag;
        this.variantBitFlag = variantBitFlag;
        generateCharset();
    }

    StringGenerator(GenerationStrategy strategy, Boolean shuffleCharset, Long randomBitFlag, Long variantBitFlag,
            String charset) {
        this.strategy = strategy;
        this.shuffleCharset = shuffleCharset;
        this.randomBitFlag = randomBitFlag;
        this.variantBitFlag = variantBitFlag;
        this.charset = charset;
    }

    /**
     * Generate a random string with a custom length
     *
     * @param length of the string
     *
     * @return randomized string
     */
    public String generate(int length) {
        checkLength(length);

        String splitRegex = "(?<=\\G.{%d})";
        int charsetLength = charset.length();

        String[] numbers = generateNumericString().split(splitRegex.formatted(charsetLength));
        SecureRandom random = RandomUtils.createSecureRandom();
        StringBuilder sb = new StringBuilder();

        int numbersLength = numbers.length;
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(numbersLength) * random.nextInt(numbersLength);
            int calculatedIndex = randomIndex % charset.length();
            char character = charset.charAt(calculatedIndex);
            sb.append(character);
        }

        return sb.toString();
    }

    /**
     * Verify if string length is greater or equal one
     *
     * @param length to be verified
     */
    private void checkLength(int length) {
        if (length < 1) {
            throw new InvalidStringLengthException("Length must be greater than 1 but was [%s]", length);
        }
    }

    /**
     * Gets the charset from {@link StringGenerator#strategy} and apply a shuffle if
     * {@link StringGenerator#shuffleCharset} is true
     */
    private void generateCharset() {
        charset = strategy.getCharacteres();

        if (!shuffleCharset) {return;}

        List<String> characteres = new ArrayList<>(List.of(strategy.getCharacteres().split("")));
        Collections.shuffle(characteres, RandomUtils.createSecureRandom());
        charset = String.join("", characteres);
    }

    /**
     * Generate a randomized long string used to calculate char index from charset
     *
     * @return a randomized long string
     */
    private String generateNumericString() {
        Long start = Math.abs(LongUtils.generateRandomBits(randomBitFlag, variantBitFlag) >> 2);
        Long middle = Math.abs(LongUtils.generateRandomBits(randomBitFlag, variantBitFlag) << 3);
        Long end = Math.abs(LongUtils.generateRandomBits(randomBitFlag, variantBitFlag) >> 5);

        return String.valueOf(start).concat(String.valueOf(middle)).concat(String.valueOf(end));
    }

    public GenerationStrategy getStrategy() {
        return strategy;
    }

    public Boolean getShuffleCharset() {
        return shuffleCharset;
    }

    public Long getRandomBitFlag() {
        return randomBitFlag;
    }

    public Long getVariantBitFlag() {
        return variantBitFlag;
    }

    public String getCharset() {
        return charset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof StringGenerator generator)) {return false;}

        if (strategy != generator.strategy) {return false;}
        if (!shuffleCharset.equals(generator.shuffleCharset)) {return false;}
        if (!randomBitFlag.equals(generator.randomBitFlag)) {return false;}
        return variantBitFlag.equals(generator.variantBitFlag);
    }

    @Override
    public int hashCode() {
        int result = strategy.hashCode();
        result = 31 * result + shuffleCharset.hashCode();
        result = 31 * result + randomBitFlag.hashCode();
        result = 31 * result + variantBitFlag.hashCode();
        return result;
    }
}
