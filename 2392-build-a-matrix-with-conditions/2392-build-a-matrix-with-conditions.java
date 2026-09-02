public class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] rowOrder = topoSort(k, rowConditions);
        if (rowOrder.length != k) return new int[0][0];

        int[] colOrder = topoSort(k, colConditions);
        if (colOrder.length != k) return new int[0][0];

        int[][] res = new int[k][k];
        int[] colIndex = new int[k + 1];
        for (int i = 0; i < k; i++) {
            colIndex[colOrder[i]] = i;
        }

        for (int i = 0; i < k; i++) {
            res[i][colIndex[rowOrder[i]]] = rowOrder[i];
        }

        return res;
    }

    private int[] topoSort(int k, int[][] edges) {
        int[] indegree = new int[k + 1];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] order = new int[k];
        int idx = 0;
        for (int i = 1; i <= k; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[idx++] = node;
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    queue.offer(nei);
                }
            }
        }

        if (idx != k) return new int[0];
        return order;
    }
}