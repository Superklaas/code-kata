import java.util.Arrays;

public class MovingZeros {

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        moveZeros(nums);
        System.out.println(Arrays.toString(nums));
    }

    static void moveZeros(int[] nums) {

        int lengthArray = nums.length;
        int insertIndex = 0;

        // move non-zero elements to first positions
        for (int i = 0; i < lengthArray; i++) {
            if (nums[i] != 0) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }

        // fill the last positions with zeros
        for (int i = insertIndex; i < lengthArray; i++) {
            nums[i] = 0;
        }

    }

}
