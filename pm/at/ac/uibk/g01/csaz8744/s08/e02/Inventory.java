package at.ac.uibk.pm.g01.csaz8744.s08.e02;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inventory {

    private List<Item> items;

    public Inventory(List<Item> items) {
        this.items = new ArrayList<>(items);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    public List<Item> getItems(FoodType foodType, BigDecimal priceUpperBound) {
        Predicate<Item> predicate = (item) -> (item.getFoodType() == foodType) && (item.getPrice().compareTo(priceUpperBound) < 0);
        List<Item> itemsPerType = items.stream()
                .filter(predicate).collect(Collectors.toList());
        return itemsPerType;
    }

    public List<Item> getItems(FoodType foodType) {
        Predicate<Item> predicate = (item) -> (item.getFoodType() == foodType);
        List<Item> itemsPerType = items.stream()
                .filter(predicate).collect(Collectors.toList());
        return itemsPerType;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal sum = items.stream()
                .map(item -> item.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }

    public BigDecimal getTotalPrice(FoodType foodType) {
        BigDecimal sum = items.stream()
                .filter(item -> item.getFoodType() == foodType)
                .map(item -> item.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }

    public BigDecimal getAveragePrice(FoodType foodType) {
        BigDecimal sum = items.stream()
                .filter(item -> item.getFoodType() == foodType)
                .map(item -> item.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(BigDecimal.valueOf(items.size()), 2, RoundingMode.HALF_EVEN);
    }

    public void sortItemsByPrice() {
        items.sort(Comparator.comparing(Item::getPrice));
    }

    public void sortItemsByFoodType() {
        items.sort(Comparator.comparing(Item::getFoodType));
    }

    public Stream<Item> getAllItemsAlphabetically(){
        return items.stream().sorted(Comparator.comparing(Item::getName));
    }

    public void increasePrices(){
        for (Item item : items) {
            switch (item.getFoodType()){
                case MEAT: item.increasePrice(0.2); break;
                case FRUIT: item.increasePrice(0.10); break;
                default: item.increasePrice(0.125); break;
            }
        }
    }

    public List<Item> removeExpiredItems(){
        List<Item> removed = items.stream()
                .filter(item -> item.getExpirationDate().compareTo(LocalDate.now()) < 0)
                .collect(Collectors.toList());
        items.remove(removed);
        return removed;
    }


    @Override
    public String toString() {
        return items.toString();
    }


}
