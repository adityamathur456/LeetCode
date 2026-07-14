class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        int pos = 0, neg = 1;
        int idx = 0;

        while (idx < n) {
            if (nums[idx] >= 0) {
                ans[pos] = nums[idx];
                pos += 2;
            } else {
                ans[neg] = nums[idx];
                neg += 2;
            }
            idx++;
        }

        return ans;
    }
}