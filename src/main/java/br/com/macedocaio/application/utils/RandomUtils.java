package br.com.macedocaio.application.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Utility class utilized to create a {@link SecureRandom} instance with random seed
 */
public final class RandomUtils {

    /**
     * Default {@link SecureRandom} algorithm
     */
    public static final String SHA1PRNG_ALGORITHM = "SHA1PRNG";

    /**
     * Try to generate a {@link SecureRandom} instance using SHA1PRNG
     * otherwise generate a random with default algorithm
     *
     * @return a generate {@link SecureRandom} instance
     */
    public static SecureRandom createSecureRandom() {
        byte[] uuidBytes = UUID.randomUUID().toString().getBytes();

        SecureRandom random;
        try {
            random = SecureRandom.getInstance(SHA1PRNG_ALGORITHM);
            random.setSeed(uuidBytes);
        } catch (NoSuchAlgorithmException e) {
            random = new SecureRandom(uuidBytes);
        }
        return random;
    }
}