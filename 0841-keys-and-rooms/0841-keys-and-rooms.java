class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            set.add(cur);

            for (int neighbour : rooms.get(cur)) {
                if (!set.contains(neighbour)) {
                    queue.offer(neighbour);
                    set.add(neighbour);
                }
            }
        }

        return set.size() == rooms.size();
    }
}