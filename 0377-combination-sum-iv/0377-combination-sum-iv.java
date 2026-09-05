class Solution {
    public int combinationSum4(int[] nums, int target) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);

        for (int total = 1; total <= target; total++) {
            dp.put(total, 0);
            for (int num : nums) {
                dp.put(total, dp.get(total) + dp.getOrDefault(total - num, 0));
            }
        }

        return dp.get(target);
    }
}