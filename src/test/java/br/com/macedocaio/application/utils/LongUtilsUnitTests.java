package br.com.macedocaio.application.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class LongUtilsUnitTests {

    private static SecureRandom secureRandom;

    @BeforeAll
    public static void before_All() {
        secureRandom = RandomUtils.createSecureRandom();
    }

    @Test
    public void should_Generate_Random_Bits() {
        try (MockedStatic<RandomUtils> uuidMock = mockStatic(RandomUtils.class)) {
            uuidMock.when(RandomUtils::createSecureRandom).thenReturn(secureRandom);

            Long generated = LongUtils.generateRandomBits();

            assertNotNull(generated);
        }
    }

    @Test
    public void should_Generate_Random_Bits_With_Custom_Flags() {
        try (MockedStatic<RandomUtils> uuidMock = mockStatic(RandomUtils.class)) {
            uuidMock.when(RandomUtils::createSecureRandom).thenReturn(secureRandom);

            var randomBitFlag = 0x43FF_4300_57FF_5700L;
            var variantBitFlag = 0x1200_2400_3600_4800L;

            Long generated = LongUtils.generateRandomBits(randomBitFlag, variantBitFlag);

            assertNotNull(generated);
        }
    }
}
