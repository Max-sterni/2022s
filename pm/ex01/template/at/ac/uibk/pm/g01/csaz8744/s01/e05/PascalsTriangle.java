package e05;

public class PascalsTriangle {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[][] triangle = pascalsTriangle(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                System.out.print(" ");
            }
            for (int val : triangle[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static int[][] pascalsTriangle(int n){
        int[][] triangle = new int[n][];

        for (int i = 0; i < n; i++) {

            int[] tmp = new int[i + 1]; 
            for (int j = 0; j < (i + 1); j++){
                if(j < i/2 + 1){
                    tmp[j] = j + 1;
                }
                else{
                    tmp[j] = i - j + 1;
                }
            }
            triangle[i] = tmp;
        }
        return triangle;
    }

}
