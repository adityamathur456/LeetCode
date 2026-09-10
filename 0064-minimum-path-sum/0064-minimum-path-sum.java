class Solution {
    public int minPathSum(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        
        int[] dp = new int[COLS + 1];
        for (int c = 0; c <= COLS; c++) {
            dp[c] = Integer.MAX_VALUE;
        }

        dp[COLS - 1] = 0;

        for (int r = ROWS - 1; r >= 0; r--) {
            for (int c = COLS - 1; c >= 0; c--) {
                dp[c] = grid[r][c] + Math.min(dp[c], dp[c + 1]);
            }
        }

        return dp[0];
    }
}