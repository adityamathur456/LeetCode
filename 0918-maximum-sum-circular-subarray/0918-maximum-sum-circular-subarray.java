class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int globMax = nums[0], globMin = nums[0];
        int curMin = 0, curMax = 0, total = 0;

        for (int num : nums) {
            curMax = Math.max(curMax + num, num);
            curMin = Math.min(curMin + num, num);
            total += num;
            globMax = Math.max(globMax, curMax);
            globMin = Math.min(globMin, curMin);
        }

        return globMax > 0 ? Math.max(globMax, total - globMin) : globMax;
    }
}