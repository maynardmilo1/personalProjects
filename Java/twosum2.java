//This code only passed 19/23 of the test cases due to the time limit being exceeded. 
//Searching through the array should be faster
package Java;
import java.util.Arrays;

public class twosum2 {
    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        System.out.println(Arrays.toString(twoSum(numbers,9)));
    }
    public static int[] twoSum(int[] numbers, int target) {
        int[] index = {-99,-99};
        for (int i = 0; i<numbers.length; i++){
            for(int j = 0; j<numbers.length; j++){
                if (numbers[i] == numbers[j] && numbers[i] + numbers[j] == target){
                    index[0] = i + 1;
                    index[1] = j + 2;
                    return index;
                }else if (numbers[i] + numbers[j] == target){
                    index[0] = i + 1;
                    index[1] = j + 1;
                    return index;
                
                }
            }           
        }
    return index;         
    }
}
