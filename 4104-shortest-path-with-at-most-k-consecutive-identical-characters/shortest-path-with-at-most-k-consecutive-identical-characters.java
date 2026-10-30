import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (n == 1) return 0;

        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        int[] dist = new int[n * (k + 1)];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        dist[0 * (k + 1) + 1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0];
            int u = top[1];
            int count = top[2];

            if (d > dist[u * (k + 1) + count]) continue;
            if (u == n - 1) return d;

            char cu = labels.charAt(u);

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];
                char cv = labels.charAt(v);

                int nextCount = (cu == cv) ? count + 1 : 1;
                if (nextCount > k) continue;

                int nextIndex = v * (k + 1) + nextCount;
                if (d + w < dist[nextIndex]) {
                    dist[nextIndex] = d + w;
                    pq.offer(new int[]{d + w, v, nextCount});
                }
            }
        }

        return -1;
    }
}