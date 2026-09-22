class Solution {
    public int countVowelPermutation(int n) {
        final int MOD = 1000000007;

        long a = 1, e = 1, i = 1, o = 1, u = 1;

        for (int k = 1; k < n; k++) {
            long an = e;
            long en = (a + i) % MOD;
            long in = (a + e + o + u) % MOD;
            long on = (i + u) % MOD;
            long un = a;

            a = an;
            e = en;
            i = in;
            o = on;
            u = un;
        }

        return (int) ((a + e + i + o + u) % MOD);
    }
}