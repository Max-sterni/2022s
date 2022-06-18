package at.ac.uibk.pm.g01.csaz8744.s09.e02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Invalid input");
            System.exit(1);
        }

        Scanner ayScanner = null;
        Scanner quScanner = null;

        try {
            ayScanner = new Scanner(new File("src/at/ac/uibk/pm/g01/csaz8744/s09/e02/ay.txt"));
            quScanner = new Scanner(new File("src/at/ac/uibk/pm/g01/csaz8744/s09/e02/qu.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("language file not found");
            System.exit(1);
        }

        List<String> ayLanguage = new ArrayList<>();
        List<String> quLanguage = new ArrayList<>();

        while (ayScanner.hasNext()){
            ayLanguage.add(ayScanner.nextLine());
        }

        while (quScanner.hasNext()){
            quLanguage.add(quScanner.nextLine());
        }

        int ayhits = 0;
        int quhits = 0;

        String[] words = args[0].split(" ");

        for (String word: words
             ) {
            if(ayLanguage.contains(word)){
                ayhits++;
            }
            if(quLanguage.contains(word)){
                quhits++;
            }
        }
        System.out.printf("QU: %f%nAY: %f%n", (double)quhits/words.length, (double)ayhits/words.length);

    }
}
