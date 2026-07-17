class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        // Frequency of each number
        int[] freq = new int[max + 1];
        for (int x : nums) {
            freq[x]++;
        }

        // cnt[d] = how many numbers are divisible by d
        int[] cnt = new int[max + 1];
        for (int d = 1; d <= max; d++) {
            for (int multiple = d; multiple <= max; multiple += d) {
                cnt[d] += freq[multiple];
            }
        }

        // exactPairs[d] = number of pairs whose gcd is exactly d
        long[] exactPairs = new long[max + 1];

        for (int d = max; d >= 1; d--) {
            long total = (long) cnt[d] * (cnt[d] - 1) / 2;

            for (int multiple = d * 2; multiple <= max; multiple += d) {
                total -= exactPairs[multiple];
            }

            exactPairs[d] = total;
        }

        // Prefix counts over gcd values
        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exactPairs[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // convert 0-based index to 1-based order statistic

            int lo = 1, hi = max;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (prefix[mid] >= target) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            ans[i] = lo;
        }

        return ans;
    }
}