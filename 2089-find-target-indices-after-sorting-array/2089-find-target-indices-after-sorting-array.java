class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessThanTarget = 0, targetFreq = 0;

        for (int num : nums) {
            if (num < target)
                lessThanTarget++;
            else if (num == target)
                targetFreq++;
        }

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < targetFreq; i++) 
            indices.add(lessThanTarget + i);
        
        return indices;
    }
}