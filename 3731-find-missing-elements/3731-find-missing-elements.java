class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> missings = new ArrayList<>();
        boolean[] hashArray = new boolean[101];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int ele : nums) {
            min = Math.min(min, ele);
            max = Math.max(max, ele);
            hashArray[ele] = true;
        } 

        for (int i = min; i <= max; i++) {
            if (!hashArray[i]) 
                missings.add(i);
        }

        return missings;
    }
}