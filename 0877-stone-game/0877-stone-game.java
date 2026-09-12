public class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[] dp = new int[n];

        for (int l = n - 1; l >= 0; l--) {
            for (int r = l; r < n; r++) {
                boolean even = (r - l) % 2 == 0;
                int left = even ? piles[l] : 0;
                int right = even ? piles[r] : 0;

                if (l == r) {
                    dp[r] = left;
                } else {
                    dp[r] = Math.max(dp[r] + left, dp[r - 1] + right);
                }
            }
        }

        int total = 0;
        for (int pile : piles) {
            total += pile;
        }

        int aliceScore = dp[n - 1];
        return aliceScore > (total - aliceScore);
    }
}