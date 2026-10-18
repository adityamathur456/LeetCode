class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] C;

    private long ways(List<Integer> list) {
        int n = list.size();
        if (n <= 2) return 1;
        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        int root = list.get(0);
        for (int i = 1; i < n; i++) {
            if (list.get(i) < root) 
                left.add(list.get(i));
            else
                right.add(list.get(i));
        }
        return C[n - 1][left.size()] * ways(left) % MOD * ways(right) % MOD;
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;
        C = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
            }
        }
        List<Integer> list = Arrays.stream(nums).boxed().toList();
        return (int) ((ways(list) - 1 + MOD) % MOD);
    }
}