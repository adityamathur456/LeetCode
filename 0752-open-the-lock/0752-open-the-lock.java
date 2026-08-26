public class Solution {
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;

        Set<String> visit = new HashSet<>(Arrays.asList(deadends));
        if (visit.contains("0000")) return -1;

        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        visit.add("0000");
        int steps = 0;

        while (!q.isEmpty()) {
            steps++;
            for (int i = q.size(); i > 0; i--) {
                String lock = q.poll();
                for (int j = 0; j < 4; j++) {
                    for (int move : new int[]{1, -1}) {
                        char[] arr = lock.toCharArray();
                        arr[j] = (char)((arr[j] - '0' + move + 10) % 10 + '0');
                        String nextLock = new String(arr);
                        if (visit.contains(nextLock)) continue;
                        if (nextLock.equals(target)) return steps;
                        q.offer(nextLock);
                        visit.add(nextLock);
                    }
                }
            }
        }
        return -1;
    }
}