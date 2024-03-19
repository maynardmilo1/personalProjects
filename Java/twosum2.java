package Java;

import java.util.Arrays;

public class twosum2 {
    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        System.out.println(Arrays.toString(twoSum(numbers,9)));
    }
    public static int[] twoSum(int[] numbers, int target) {
        int[] index = {-1, -1};
        for (int i = 0; i<numbers.length; i++){
            for(int j = i + 1; j<numbers.length; i++){
                if (numbers[i]+numbers[j] == target){
                    index[1]=i +1;
                    index[0]=j +1;
                    return index;
                }
            }           
        }
        return index;
    }
}
