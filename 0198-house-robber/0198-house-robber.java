class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int rob1 = nums[0];
        int rob2 = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int current = Math.max(rob2, nums[i] + rob1);
            rob1 = rob2;
            rob2 = current;
        }

        return rob2;
    }
}