class Solution {
    public int[][] transpose(int[][] matrix) {
        int ROWS = matrix.length, COLS = matrix[0].length;
        int[][] res = new int[COLS][ROWS];

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                res[c][r] = matrix[r][c];
            }
        }

        return res;
    }
}