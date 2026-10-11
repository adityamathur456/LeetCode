class Solution {
    public int sumOfSquares(int[] nums) {
        int sum = 0, n = nums.length;

        for (int i = 1; i <= nums.length; i++) {
            sum += n % i == 0 ? nums[i - 1] * nums[i - 1] : 0;
        }

        return sum;
    }
}