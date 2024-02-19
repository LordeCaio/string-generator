package br.com.macedocaio.application.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class RandomUtilsUnitTests {

    private static UUID uuid;

    @BeforeAll
    public static void before_All() {
        uuid = UUID.randomUUID();
    }

    @Test
    public void should_Create_Secure_Random() {
        try (MockedStatic<UUID> uuidMock = mockStatic(UUID.class)) {

            uuidMock.when(UUID::randomUUID).thenReturn(uuid);

            SecureRandom actual = RandomUtils.createSecureRandom();

            assertNotNull(actual);
            assertEquals(RandomUtils.SHA1PRNG_ALGORITHM, actual.getAlgorithm());
        }
    }

    @Test
    public void should_Create_Secure_Random_With_Default_Algorithm() {
        try (MockedStatic<SecureRandom> secureRandomMock = mockStatic(SecureRandom.class);
             MockedStatic<UUID> uuidMock = mockStatic(UUID.class)) {

            uuidMock.when(UUID::randomUUID).thenReturn(uuid);
            secureRandomMock.when(() -> SecureRandom.getInstance(anyString()))
                    .thenThrow(NoSuchAlgorithmException.class);

            SecureRandom actual = RandomUtils.createSecureRandom();

            assertNotNull(actual);
            assertNotEquals(RandomUtils.SHA1PRNG_ALGORITHM, actual.getAlgorithm());
        }
    }
}
