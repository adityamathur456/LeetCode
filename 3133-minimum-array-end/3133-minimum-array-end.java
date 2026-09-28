class Solution {
    public long minEnd(int n, int x) {
        long res = x;
        long i_x = 1;
        long i_n = 1;

        while (i_n <= n - 1) {
            if ((i_x & x) == 0) {
                if ((i_n & (n - 1)) != 0) {
                    res = res | i_x;
                }
                i_n = i_n << 1;
            }
            i_x = i_x << 1;
        }

        return res;
    }
}