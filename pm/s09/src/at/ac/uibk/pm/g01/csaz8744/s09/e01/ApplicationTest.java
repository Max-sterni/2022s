package at.ac.uibk.pm.g01.csaz8744.s09.e01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @ParameterizedTest
    @CsvSource(value = {"1,arnie morton's of chicago,435 s. la cienega blv.,los angeles,310/246-1501,american:1,arnie morton's of chicago,435 s. la cienega blvd.,los angeles,310-246-1501,americano", "5,hotel bel-air,701 stone canyon rd.,bel air,310/472-1211,californian:6,bel-air hotel,701 stone canyon rd.,bel air,310-472-1211,californian"}, delimiter = ':')
     void similarityRestaurantsEqualTest(String a, String b) {
        assertTrue(Application.similarityRestaurants(a,b) > 0.75);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,arnie morton's of chicago,435 s. la cienega blv.,los angeles,310/246-1501,american:6,bel-air hotel,701 stone canyon rd.,bel air,310-472-1211,californian", "5,hotel bel-air,701 stone canyon rd.,bel air,310/472-1211,californian:15,fenix,8358 sunset blvd. west,hollywood,213/848-6677,american"}, delimiter = ':')
    void similarityRestaurantsUnequalTest(String a, String b) {
        assertFalse(Application.similarityRestaurants(a,b) > 0.75);
    }
}