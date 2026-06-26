class Solution {

    private static final int MAX_POS = 50000;
    private int[] segmentTree;

    private void update(int node, int left, int right, int index, int value) {
        if (left == right) {
            segmentTree[node] = value;
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        segmentTree[node] = Math.max(
            segmentTree[node * 2],
            segmentTree[node * 2 + 1]
        );
    }

    private int query(int node, int left, int right, int ql, int qr) {
        if (qr < left || ql > right) {
            return 0;
        }

        if (ql <= left && right <= qr) {
            return segmentTree[node];
        }

        int mid = left + (right - left) / 2;

        return Math.max(
            query(node * 2, left, mid, ql, qr),
            query(node * 2 + 1, mid + 1, right, ql, qr)
        );
    }

    public List<Boolean> getResults(int[][] queries) {

        segmentTree = new int[4 * (MAX_POS + 1)];

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        // Collect all obstacle positions
        for (int[] query : queries) {
            if (query[0] == 1) {
                obstacles.add(query[1]);
            }
        }

        List<Integer> positions = new ArrayList<>(obstacles);

        // Build initial gap information
        for (int i = 1; i < positions.size(); i++) {
            int current = positions.get(i);
            int previous = positions.get(i - 1);

            update(
                1,
                0,
                MAX_POS,
                current,
                current - previous
            );
        }

        List<Boolean> answer = new ArrayList<>();

        // Process queries in reverse
        for (int i = queries.length - 1; i >= 0; i--) {

            int[] query = queries[i];

            if (query[0] == 2) {

                int x = query[1];
                int size = query[2];

                int previousObstacle = obstacles.floor(x);

                int maxGap = query(
                    1,
                    0,
                    MAX_POS,
                    0,
                    previousObstacle
                );

                maxGap = Math.max(
                    maxGap,
                    x - previousObstacle
                );

                answer.add(maxGap >= size);

            } else {

                int x = query[1];

                Integer leftObstacle = obstacles.lower(x);
                Integer rightObstacle = obstacles.higher(x);

                // Remove gap ending at x
                update(1, 0, MAX_POS, x, 0);

                // Merge adjacent gaps
                if (rightObstacle != null) {
                    update(
                        1,
                        0,
                        MAX_POS,
                        rightObstacle,
                        rightObstacle - leftObstacle
                    );
                }

                obstacles.remove(x);
            }
        }

        Collections.reverse(answer);
        return answer;
    }
}