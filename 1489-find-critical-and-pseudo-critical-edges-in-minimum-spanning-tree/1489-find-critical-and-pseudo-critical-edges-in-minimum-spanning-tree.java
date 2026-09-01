class UnionFind {
    private int n;
    private int[] Parent, Size;

    public UnionFind(int n) {
        this.n = n;
        Parent = new int[n + 1];
        Size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            Parent[i] = i;
            Size[i] = 1;
        }
    }

    public int find(int node) {
        if (Parent[node] != node) {
            Parent[node] = find(Parent[node]);
        }
        return Parent[node];
    }

    public boolean union(int u, int v) {
        int pu = find(u), pv = find(v);
        if (pu == pv) {
            return false;
        }
        n--;
        if (Size[pu] < Size[pv]) {
            int temp = pu;
            pu = pv;
            pv = temp;
        }
        Size[pu] += Size[pv];
        Parent[pv] = pu;
        return true;
    }

    public boolean isConnected() {
        return n == 1;
    }
}

public class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            edges[i] = Arrays.copyOf(edges[i], edges[i].length + 1);
            edges[i][3] = i;
        }

        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));


        int mst_wgt = findMST(n, edges, -1, false);
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            if (mst_wgt < findMST(n, edges, i, false)) {
                critical.add(edges[i][3]);
            } else if (mst_wgt == findMST(n, edges, i, true)) {
                pseudo.add(edges[i][3]);
            }
        }

        return Arrays.asList(critical, pseudo);
    }

    public int findMST(int n, int[][] edges, int index, boolean include) {
        UnionFind uf = new UnionFind(n);
        int wgt = 0;
        if (include) {
            wgt += edges[index][2];
            uf.union(edges[index][0], edges[index][1]);
        }
        for (int i = 0; i < edges.length; i++) {
            if (i == index) {
                continue;
            }
            if (uf.union(edges[i][0], edges[i][1])) {
                wgt += edges[i][2];
            }
        }
        return uf.isConnected() ? wgt : Integer.MAX_VALUE;
    }
}