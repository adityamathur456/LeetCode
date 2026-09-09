public class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        if (grid[0][0] == 1 || grid[M - 1][N - 1] == 1) {
            return 0;
        }

        grid[M - 1][N - 1] = 1;

        for (int r = M - 1; r >= 0; r--) {
            for (int c = N - 1; c >= 0; c--) {
                if (r == M - 1 && c == N - 1) {
                    continue;
                }

                if (grid[r][c] == 1) {
                    grid[r][c] = 0;
                } else {
                    int down = (r + 1 < M) ? grid[r + 1][c] : 0;
                    int right = (c + 1 < N) ? grid[r][c + 1] : 0;
                    grid[r][c] = down + right;
                }
            }
        }

        return grid[0][0];
    }
}