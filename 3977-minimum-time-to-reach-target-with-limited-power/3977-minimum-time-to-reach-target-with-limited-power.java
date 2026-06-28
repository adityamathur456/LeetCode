import java.util.*;

class Solution {

    static class State {
        long time;
        int power;
        int node;

        State(long time, int power, int node) {
            this.time = time;
            this.power = power;
            this.node = node;
        }
    }

    public long[] minTimeMaxPower(int n, int[][] edges, int power, int[] cost,
                                  int source, int target) {

        final long INF = (long) 1e18;

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        long[][] dist = new long[n][power + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>(
                Comparator.comparingLong(a -> a.time));

        dist[source][power] = 0;
        pq.offer(new State(0, power, source));

        while (!pq.isEmpty()) {

            State cur = pq.poll();

            long time = cur.time;
            int powerRem = cur.power;
            int node = cur.node;

            if (time != dist[node][powerRem]) {
                continue;
            }

            if (cost[node] > powerRem) {
                continue;
            }

            int newPower = powerRem - cost[node];

            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                long nextTime = time + next[1];

                if (dist[nextNode][newPower] > nextTime) {
                    dist[nextNode][newPower] = nextTime;
                    pq.offer(new State(nextTime, newPower, nextNode));
                }
            }
        }

        long minTime = INF;
        for (int i = 0; i <= power; i++) {
            minTime = Math.min(minTime, dist[target][i]);
        }

        if (minTime == INF) {
            return new long[]{-1, -1};
        }

        long maxPower = 0;
        for (int i = 0; i <= power; i++) {
            if (dist[target][i] == minTime) {
                maxPower = Math.max(maxPower, i);
            }
        }

        return new long[]{minTime, maxPower};
    }
}