public class Solution {
    public int lastStoneWeightII(int[] stones) {
        int stoneSum = 0;
        for (int stone : stones) {
            stoneSum += stone;
        }
        int target = stoneSum / 2;

        Set<Integer> dp = new HashSet<>();
        dp.add(0);

        for (int stone : stones) {
            Set<Integer> newDp = new HashSet<>(dp);
            for (int val : dp) {
                if (val + stone == target) {
                    return stoneSum - 2 * target;
                }
                if (val + stone < target) {
                    newDp.add(val + stone);
                }
            }
            dp = newDp;
        }

        int maxVal = 0;
        for (int val : dp) {
            maxVal = Math.max(maxVal, val);
        }

        return stoneSum - 2 * maxVal;
    }
}