class Solution {

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        // pre[i] stores the frequency of a prefix balance.
        // Balance ranges from -n to n, so shift by n.
        int[] pre = new int[2 * n + 1];

        // Initial prefix balance = 0
        pre[n] = 1;

        // Current balance index (shifted by n)
        int cnt = n;

        long ans = 0;
        long preSum = 0;

        for (int i = 0; i < n; i++) {

            if (nums[i] == target) {

                // Add number of previous prefixes with current balance
                preSum += pre[cnt];

                cnt++;
                pre[cnt]++;

            } else {

                cnt--;

                // Remove prefixes that are no longer valid
                preSum -= pre[cnt];

                pre[cnt]++;
            }

            ans += preSum;
        }

        return ans;
    }
}