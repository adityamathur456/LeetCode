class Solution {
    public int tribonacci(int n) {
        if (n <= 2) {
            return n == 0 ? 0 : 1;
        }

        int tribA = 0, tribB = 1, tribC = 1;

        for (int i = 3; i <= n; i++) {
            int tribTemp = tribA + tribB + tribC;
            tribA = tribB;
            tribB = tribC;
            tribC = tribTemp;
        }

        return tribC;
    }
}