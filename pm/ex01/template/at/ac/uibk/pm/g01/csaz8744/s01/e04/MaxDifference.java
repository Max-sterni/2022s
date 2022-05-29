package e04;

public class MaxDifference {
    public static void main(String[] args) {
        int[] test = {};
        MaxDifference max = new MaxDifference();
        System.out.println(max.maxDifference(test));
    }

    public int maxDifference(int[] array){
        int max_difference = 0;
        int tmp;

        for (int val1 : array) {
            for (int val2 : array) {
                tmp = val1 - val2;
                if(tmp > max_difference){
                    max_difference = tmp;
                }
            }
        }

        return max_difference;
    }
}
