package br.com.macedocaio.application.utils;

import java.security.SecureRandom;

/**
 * Utility class to manipulate and return {@link Long} values
 */
public final class LongUtils {

    public static final long RANDOM_BIT_FLAG = 0x5FFF_5000_4FFF_3FFFL;
    public static final long VARIANT_BIT_FLAG = 0x8000_7000_4000_3000L;

    /**
     * Generates a pseudo randomized long value using bit flags
     *
     * @return generated long value
     */
    public static Long generateRandomBits() {
        return generateRandomBits(RANDOM_BIT_FLAG, VARIANT_BIT_FLAG);
    }

    /**
     * Generates a pseudo randomized long value using bit flags
     *
     * @param randomBitFlag  Bitwise AND flag applied to random generated long
     * @param variantBitFlag Bitwise OR flag applied to the result of {@param randomBitFlag} operation
     * @return generated long value
     */
    public static Long generateRandomBits(Long randomBitFlag, Long variantBitFlag) {
        SecureRandom random = RandomUtils.createSecureRandom();
        long unmaskLong = random.nextLong() & randomBitFlag;
        return unmaskLong | variantBitFlag;
    }
}