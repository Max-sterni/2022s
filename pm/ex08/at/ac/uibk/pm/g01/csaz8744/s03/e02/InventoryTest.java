package at.ac.uibk.pm.g01.csaz8744.s08.e02;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void sortTest(){
        List<Item> testItems = new ArrayList<>();
        testItems.add(new Item("item1", FoodType.MEAT, BigDecimal.valueOf(1000), LocalDate.of(2022, 6, 21)));
        testItems.add(new Item("item2", FoodType.FRUIT, BigDecimal.valueOf(100), LocalDate.of(2022, 6, 20)));
        testItems.add(new Item("item3", FoodType.SWEETS, BigDecimal.valueOf(10), LocalDate.of(2022, 6, 22)));

        Inventory testInventory = new Inventory(testItems);

        testInventory.sortItemsByFoodType();
    }
}