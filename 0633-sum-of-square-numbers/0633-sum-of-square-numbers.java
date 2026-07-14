class Solution {
    public boolean judgeSquareSum(int c) {
        int l = 0;
        int r = (int) Math.sqrt(c);

        while (l <= r) {
            long square = (long) l * l + (long) r * r;

            if (square == c) {
                return true;
            } else if (square < c) {
                l++;
            } else {
                r--;
            }
        }

        return false;
    }
}