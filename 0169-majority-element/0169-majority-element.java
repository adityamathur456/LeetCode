class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int majorElement = 0;
        
        for (int num : nums) {
            if (count == 0) {
                majorElement = num;
            }
            count += (num == majorElement) ? 1 : -1;
        }

        return majorElement;
    }
}