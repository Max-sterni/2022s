package e03;

public class TimesTwo {
    public static void main(String[] args) {
        
    }
    
    public static void timesTwo(int value){
        System.out.println(value * 2);
    }

    public static void timesTwo(int[] values){
        int out = 1;
        for (int value : values) {
            out *= value;
        }
        System.out.println(out);
    }
}

