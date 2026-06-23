class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        if (n == 1)
            return m;
        
        long[] up = new long[m];
        long[] down = new long[m];

        for (int v = 0; v < m; v++) {
            up[v] = v;
            down[v] = m - 1 - v;
        }

        for (int len = 3; len <= n; len++) {
            long[] prefixDown = new long[m + 1];
            long[] prefixUp = new long[m + 1];

            for (int i = 0; i < m; i++) {
                prefixDown[i + 1] = (prefixDown[i] + down[i]) % MOD;
                prefixUp[i + 1] = (prefixUp[i] + up[i]) % MOD;
            }

            long totalUp = prefixUp[m];

            long[] newUp = new long[m];
            long[] newDown = new long[m];

            for (int v = 0; v < m; v++) {
                newUp[v] = prefixDown[v];
                newDown[v] = (totalUp - prefixUp[v + 1] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        for (int v = 0; v < m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}