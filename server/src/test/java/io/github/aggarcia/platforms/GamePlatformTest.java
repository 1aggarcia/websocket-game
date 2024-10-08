package io.github.aggarcia.platforms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.aggarcia.game.GameConstants;

@SpringBootTest
public class GamePlatformTest {
    private static final int RANDOM_TEST_ITERATIONS = 100;

    @Test
    void test_createRandomPlatform_setsYToZero() {
        for (int i = 0; i < RANDOM_TEST_ITERATIONS; i++) {
            var platform = GamePlatform.createRandomPlatform();
            assertEquals(0, platform.y());
        }
    }

    @Test
    void test_createRandomPlatform_generatesWidthWithinBounds() {
        for (int i = 0; i < RANDOM_TEST_ITERATIONS; i++) {
            var platform = GamePlatform.createRandomPlatform();
            assertTrue(platform.width() >= GamePlatform.MIN_WIDTH);
            assertTrue(platform.width() <= GamePlatform.MAX_WIDTH);
        }
    }

    @Test
    void test_createRandomPlatform_setsXWithinBounds() {
        for (int i = 0; i < RANDOM_TEST_ITERATIONS; i++) {
            var platform = GamePlatform.createRandomPlatform();
            assertTrue(platform.x() >= 0);
            assertTrue(platform.x() <= GameConstants.WIDTH - platform.width());
        }
    }

    @Test
    void test_toNextTick_zeroedValues_onlyChangesY() {
        var origin = new GamePlatform(0, 0, 0);
        var nextPlatform = origin.toNextTick();
        
        var expected = new GamePlatform(0, 0, GamePlatform.PLATFORM_GRAVITY);
        assertEquals(expected, nextPlatform);
    }
}

