package br.com.macedocaio.codegenerator.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public final class RandomUtils {

    public static final String DEFAULT_ALGORITHM = "SHA1PRNG";

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
            random = SecureRandom.getInstance(DEFAULT_ALGORITHM);
            random.setSeed(uuidBytes);
        } catch (NoSuchAlgorithmException e) {
            random = new SecureRandom(uuidBytes);
        }
        return random;
    }
}