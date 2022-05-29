

public class Test {
	
	public static void main(String[] args){
		int[] arr = {0, 1, 2};
		System.out.println(Test.test(arr));
		for(int i : arr){
			System.out.println(i);
		}
		
}
        public static int test(int arr){
		return arr;
	}
	public static String test(int[] arr){
		for(int i : arr){
			i *= 2;	
		}
		return "Hallo";
	}
}
