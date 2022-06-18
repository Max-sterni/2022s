package at.ac.uibk.pm.g01.csaz8744.s09.e01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final int infoFields = 6;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("src/at/ac/uibk/pm/g01/csaz8744/s09/e01/restaurant.csv"));
        List<String> restaurants = new ArrayList<>();
        scanner.nextLine();
        while(scanner.hasNext()){
            restaurants.add(scanner.nextLine());
        }

        FileWriter outputfile = new FileWriter(new File("src/at/ac/uibk/pm/g01/csaz8744/s09/e01/Result.csv"));

        outputfile.write("id1, id2, similarityScore%n".formatted());

        // closing writer connection

        for (int i = 0; i < restaurants.size(); i++) {
            for (int j = i + 1; j < restaurants.size(); j++) {
                Double similarity = similarityRestaurants(restaurants.get(i), restaurants.get(j));
                if(similarity > 0.75){
                    outputfile.write(
                            "%s, %s, %f%n"
                                    .formatted(restaurants.get(i).split(",")[0], restaurants.get(j).split(",")[0], similarity));
                }
            }
        }

        outputfile.close();
    }

    public static Double similarityRestaurants(String restaurant1, String restaurant2){
        String[] components1 = restaurant1.split(",", -1);
        String[] components2 = restaurant2.split(",", -1);

        double result = 0.0;

        for (int i = 1; i < infoFields; i++) {
            result += 1 - levenshteinDistance(components1[i], components2[i]).doubleValue()
                    / Integer.max(components1[i].length(), components2[i].length());
        }
        return result / (infoFields - 1);
    }
    public static Integer levenshteinDistance(String a, String b){
        int[][] matrix = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length() + 1; i++) {
            matrix[i][0] = i;
        }
        for (int i = 0; i < b.length() + 1; i++) {
            matrix[0][i] = i;
        }
        int substitutionCost;
        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1)) {
                    substitutionCost = 0;
                }
                else {
                    substitutionCost = 1;
                }

                matrix[i][j] = Integer.min(matrix[i - 1][j] + 1,
                    Integer.min(matrix[i][j - 1] + 1,
                            matrix[i - 1][j - 1] +substitutionCost
                            )
                );
            }
        }

        return matrix[a.length()][b.length()];

    }

    public static Integer levenshteinDistance(String a, String b, int iterations){
        System.out.println("Comparing %s %s".formatted(a, b));
        if(a.length() == 0 || b.length() == 0){
            return Integer.max(a.length(), b.length());
        }

        if(a.charAt(0) == b.charAt(0)){
            return levenshteinDistance(a.substring(1), b.substring(1), iterations - 1);
        }

        return 1 + Integer.min(Integer.min(
                        levenshteinDistance(a.substring(1), b, iterations - 1),
                        levenshteinDistance(a, b.substring(1), iterations - 1)
                ),
                levenshteinDistance(a.substring(1), b.substring(1), iterations - 1));
    }
}
