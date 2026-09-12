import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by ending position
        Arrays.sort(arr, (a, b) -> {
            if (a.r != b.r)
                return Integer.compare(a.r, b.r);

            return Integer.compare(a.l, b.l);
        });

        // prev[i] = last interval that ends before arr[i] starts
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = 0;
            int hi = i - 1;
            int ans = -1;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (arr[mid].r < arr[i].l) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            prev[i] = ans;
        }

        /*
         * dp[i][k] = best result using intervals [0 ... i]
         * with at most k intervals.
         */
        State[][] dp = new State[n][5];

        for (int i = 0; i < n; i++) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: don't take current interval
                State best = (i > 0) ? dp[i - 1][k] : null;

                // Option 2: take current interval
                long newScore = arr[i].w;
                List<Integer> newList = new ArrayList<>();

                if (k > 1 && prev[i] >= 0) {
                    State previous = dp[prev[i]][k - 1];

                    if (previous != null) {
                        newScore += previous.score;
                        newList.addAll(previous.indices);
                    }
                }

                newList.add(arr[i].idx);

                State take = new State(newScore, newList);

                if (best == null || better(take, best)) {
                    best = take;
                }

                dp[i][k] = best;
            }
        }

        State answer = dp[n - 1][4];

        int[] result = new int[answer.indices.size()];

        for (int i = 0; i < answer.indices.size(); i++) {
            result[i] = answer.indices.get(i);
        }

        // Lexicographic comparison requires indices in sorted order.
        Arrays.sort(result);

        return result;
    }

    /*
     * Returns true if a is better than b.
     *
     * Higher score is better.
     * If scores are equal, lexicographically smaller indices are better.
     */
    private boolean better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score;
        }

        List<Integer> x = new ArrayList<>(a.indices);
        List<Integer> y = new ArrayList<>(b.indices);

        Collections.sort(x);
        Collections.sort(y);

        int len = Math.min(x.size(), y.size());

        for (int i = 0; i < len; i++) {
            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i);
            }
        }

        // If one is a prefix of the other,
        // shorter array is lexicographically smaller.
        return x.size() < y.size();
    }
}