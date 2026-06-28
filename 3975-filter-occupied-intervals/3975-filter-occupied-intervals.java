class Solution {
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        List<List<Integer>> ans = new ArrayList<>();

        if (occupiedIntervals.length == 0) return ans;

        Arrays.sort(occupiedIntervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : occupiedIntervals) {
            if (merged.isEmpty() || interval[0] > merged.get(merged.size() - 1)[1] + 1) {
                merged.add(new int[] {interval[0], interval[1]});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        for (int[] in : merged) {
            int l = in[0];
            int r = in[1];

            if (r < freeStart || l > freeEnd) {
                ans.add(Arrays.asList(l, r));
                continue;
            }

            if (l <= freeStart - 1) {
                ans.add(Arrays.asList(l, freeStart - 1));
            }

            if (r >= freeEnd + 1) {
                ans.add(Arrays.asList(freeEnd + 1, r));
            }
        }

        return ans;
    }
}