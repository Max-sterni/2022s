package at.ac.uibk.pm.g01.csaz8744.midterm1.e02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    @ParameterizedTest
    @ValueSource(ints = {100, 90, 95})
    void sehrGutTest(int testValue){
        Grade testGradeObject = Grade.from(testValue);
        assertEquals(Grade.SGT1,testGradeObject);
    }

    @ParameterizedTest
    @ValueSource(ints = {89, 80, 85})
    void gutTest(int testValue){
        Grade testGradeObject = Grade.from(testValue);
        assertEquals(Grade.GUT2,testGradeObject);
    }

    @ParameterizedTest
    @ValueSource(ints = {79, 70, 75})
    void befriedigendTest(int testValue){
        Grade testGradeObject = Grade.from(testValue);
        assertEquals(Grade.BEF3,testGradeObject);
    }

    @ParameterizedTest
    @ValueSource(ints = {69, 60, 65})
    void genuegendTest(int testValue){
        Grade testGradeObject = Grade.from(testValue);
        assertEquals(Grade.GEN4,testGradeObject);
    }

    @ParameterizedTest
    @ValueSource(ints = {59, 0, 30})
    void ungenuegendTest(int testValue){
        Grade testGradeObject = Grade.from(testValue);
        assertEquals(Grade.NGD5,testGradeObject);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101, Integer.MAX_VALUE, Integer.MIN_VALUE})
    void exceptionTest(int testValue){
        Executable when = () -> Grade.from(testValue);
        assertThrows(IllegalArgumentException.class, when);
    }

}