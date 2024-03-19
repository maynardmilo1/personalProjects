//This solution passes 23/23 of the test cases by making use of two pointers from both ends going to the center

package Java;

public class twosum2updated {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length -1;
        while (left<right){
            int currentSum = numbers[left]+numbers[right];
            if (currentSum == target){
                return new int[]{left + 1, right + 1};
            }else if(currentSum < target){
                left++;
            }else{
                right--;
            }
        }
    return new int[]{-1,-1};         
    }
}
