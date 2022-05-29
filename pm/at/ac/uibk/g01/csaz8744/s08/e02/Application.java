package at.ac.uibk.pm.g01.csaz8744.s08.e02;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;

public class Application {

    public static void main(String[] args){
        if (args.length < 1) {
            System.err.println("usage: Application <path to CSV file>");
            return;
        }
        Path path = Path.of(args[0]);
        try {
            Inventory shoppingCart = new Inventory(CSVToArray.readCsv(path));
            System.out.println(shoppingCart);
            shoppingCart.sortItemsByPrice();
            System.out.println(shoppingCart);
            System.out.println(shoppingCart.getItems(FoodType.MEAT, BigDecimal.valueOf(5)));
            System.out.println(shoppingCart);
            System.out.println(shoppingCart.getTotalPrice());
            System.out.println(shoppingCart.removeExpiredItems());
        } catch (IOException e) {
            System.err.printf("Error: Unable to successfully read from file %s!".formatted(path));
        }

    }
}
