class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        return Math.max(solve(nums, k, true), solve(nums, k, false));
    }

    private long solve(int[] nums, int k, boolean mult) {
        long NEG = Long.MIN_VALUE / 4;
        long dp0 = NEG;
        long dp1 = NEG;
        long dp2 = NEG;
        long ans = NEG;

        for (int x : nums) {
            long val = mult ? 1L * x * k : divide(x, k);
            long ndp0 = Math.max(dp0 + x, (long) x);
            long ndp1 = Math.max(Math.max(dp0 + val, val), dp1 + val);
            long ndp2 = Math.max((long) x, dp2 + x);
            ndp2 = Math.max(ndp2, dp1 + x);
            dp0 = ndp0;
            dp1 = ndp1;
            dp2 = ndp2;

            ans = Math.max(ans, Math.max(dp0, Math.max(dp1, dp2)));
        }

        return ans;
    }

    private long divide(int x, int k) {
        if (x >= 0) 
            return x / k;
        else
            return -((-x) / k);
    }
}