class Solution {
    private static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int closedIsland(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int countClosed = 0;

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 0 && dfs(grid, r, c)) {
                    countClosed++;
                }
            }
        }

        return countClosed;
    }

    private boolean dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) return false;
        
        if (grid[r][c] == 1) return true;

        grid[r][c] = 1;

        boolean isClosed = true;

        for (int[] d : directions) {
            isClosed &= dfs(grid, r + d[0], c + d[1]);
        }

        return isClosed;
    }
}