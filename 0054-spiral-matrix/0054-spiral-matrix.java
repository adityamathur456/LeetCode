class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] steps = {matrix[0].length, matrix.length - 1};

        int r = 0, c = -1, d = 0;
        while (steps[d % 2] > 0) {
            for (int i = 0; i < steps[d % 2]; i++) {
                r += dir[d][0];
                c += dir[d][1];
                res.add(matrix[r][c]);
            }

            steps[d % 2]--;
            d = (d + 1) % 4;
        }

        return res;
    }
}