class Solution {
    public int minMoves(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int x : nums)
            max = Math.max(max, x);
        long res = 0;
        for (int x : nums)
            res += (max - x);
        return (int) res;
    }
}