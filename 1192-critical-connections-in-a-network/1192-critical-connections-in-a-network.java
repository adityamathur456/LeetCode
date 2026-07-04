class Solution {

    private int time = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] disc = new int[n];
        int[] low = new int[n];

        Arrays.fill(disc, -1);

        List<List<Integer>> ans = new ArrayList<>();

        dfs(0, -1, graph, disc, low, ans);

        return ans;
    }

    private void dfs(int node, int parent, List<List<Integer>> graph, int[] disc, int[] low, List<List<Integer>> ans) {

        disc[node] = low[node] = time++;

        for (int nei : graph.get(node)) {

            if (nei == parent)
                continue;

            if (disc[nei] == -1) {

                dfs(nei, node, graph, disc, low, ans);

                low[node] = Math.min(low[node], low[nei]);

                if (low[nei] > disc[node]) {
                    ans.add(Arrays.asList(node, nei));
                }

            } else {

                low[node] = Math.min(low[node], disc[nei]);
            }
        }
    }
}