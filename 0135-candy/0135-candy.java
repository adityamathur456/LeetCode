class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int res = n;

        int i = 1;
        while (i < n) {
            if (ratings[i] == ratings[i - 1]) {
                i++;
                continue;
            }

            int inc = 0;
            while (i < n && ratings[i] > ratings[i - 1]) {
                inc++;
                res += inc;
                i++;
            }

            int dec = 0;
            while (i < n && ratings[i] < ratings[i - 1]) {
                dec++;
                res += dec;
                i++;
            }

            res -= Math.min(inc, dec);
        }

        return res;
    }
}