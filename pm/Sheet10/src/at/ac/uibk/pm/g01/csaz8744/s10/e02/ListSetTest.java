package at.ac.uibk.pm.g01.csaz8744.s10.e02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListSetTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, Integer.MAX_VALUE, Integer.MIN_VALUE})
    void addIntTest(int input) {
        ListSet<Integer> integerListSet = new ListSet<>();
        integerListSet.add(input);
        assertTrue(integerListSet.contains(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "", "TEST", " "})
    void addStringTest(String input) {
        ListSet<String> stringListSet = new ListSet<>();
        stringListSet.add(input);
        assertTrue(stringListSet.contains(input));
    }
    @org.junit.jupiter.api.Test
    void addAll() {
        ListSet<Integer> listSet = new ListSet<>();
        List<Integer> inputList = new ArrayList<>();
        inputList.add(0);
        inputList.add(1);
        inputList.add(3);
        inputList.add(4);
        inputList.add(Integer.MAX_VALUE);
        inputList.add(Integer.MIN_VALUE);

        listSet.addAll(inputList);

        for (Integer integer : inputList) {
            assertTrue(listSet.contains(integer));
        }
    }

    @Test
    void isEmptyTest() {
        ListSet<Integer> listSet = new ListSet<>();
        assertTrue(listSet.isEmpty());
    }

    @Test
    void isEmptyRemoveTest(){
        ListSet<Integer> listSet = new ListSet<>();
        List<Integer> inputList = new ArrayList<>();
        inputList.add(0);
        inputList.add(1);
        inputList.add(3);
        inputList.add(4);
        inputList.add(Integer.MAX_VALUE);
        inputList.add(Integer.MIN_VALUE);
        listSet.removeAll(inputList);
        assertTrue(listSet.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, Integer.MAX_VALUE, Integer.MIN_VALUE})
    void contains(int input) {
        ListSet<Integer> integerListSet = new ListSet<>();
        integerListSet.add(input);
        assertTrue(integerListSet.contains(input));
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
        ListSet<Integer> listSet = new ListSet<>();
        List<Integer> inputList = new ArrayList<>();
        inputList.add(0);
        inputList.add(1);
        inputList.add(3);
        inputList.add(4);
        inputList.add(Integer.MAX_VALUE);
        inputList.add(Integer.MIN_VALUE);
        listSet.addAll(inputList);
        listSet.removeAll(inputList);

        for (Integer integer : inputList) {
            assertFalse(listSet.contains(integer));
        }
    }

    @org.junit.jupiter.api.Test
    void size() {
        ListSet<Integer> listSet = new ListSet<>();
        List<Integer> inputList = new ArrayList<>();
        inputList.add(0);
        inputList.add(1);
        inputList.add(3);
        inputList.add(4);
        inputList.add(Integer.MAX_VALUE);
        inputList.add(Integer.MIN_VALUE);
        listSet.addAll(inputList);
        assertEquals(6, listSet.size());
    }
}