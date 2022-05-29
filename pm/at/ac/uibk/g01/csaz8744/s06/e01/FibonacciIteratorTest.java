package at.ac.uibk.pm.g01.csaz8744.s06.e01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciIteratorTest {

    @Test
    void fiveIterationsTest(){
        FibonacciIterator iterator = new FibonacciIterator(5);

        assertEquals(BigInteger.valueOf(1), iterator.next());
        assertEquals(BigInteger.valueOf(1), iterator.next());
        assertEquals(BigInteger.valueOf(2), iterator.next());
        assertEquals(BigInteger.valueOf(3), iterator.next());
        assertEquals(BigInteger.valueOf(5), iterator.next());
    }

    @ParameterizedTest
    @ValueSource (longs = {1, 2, 3})
    void maxIterationsTest(long n){
        FibonacciIterator iterator = new FibonacciIterator(n);

        for (int i = 0; i < n; i++) {
            try {
                iterator.next();
            } catch (Exception e) {
                fail("No exception expected");
            }
        }
    }

    @ParameterizedTest
    @ValueSource (longs = {1, 2, 3})
    void moreThenMaxIterationsTest(long n){
        FibonacciIterator iterator = new FibonacciIterator(n);

        for (int i = 0; i < n; i++) {
            try {
                iterator.next();
            } catch (Exception e) {
                fail("No exception expected");
            }
        }

        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

}