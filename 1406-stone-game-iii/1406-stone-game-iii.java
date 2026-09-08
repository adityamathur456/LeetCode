class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[4];

        for (int i = n - 1; i >= 0; i--) {
            int total = 0;
            dp[i % 4] = Integer.MIN_VALUE;
            for (int j = i; j < Math.min(i + 3, n); j++) {
                total += stoneValue[j];
                dp[i % 4] = Math.max(dp[i % 4], total - dp[(j + 1) % 4]);
            }
        }

        if (dp[0] == 0) return "Tie";
        return dp[0] > 0 ? "Alice" : "Bob";
    }
}