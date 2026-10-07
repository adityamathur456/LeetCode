class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            if (isValid(curr)) {
                result.add(curr);
                found = true;
            }

            if (found) continue;

            for (int i = 0; i < curr.length(); i++) {
                char ch = curr.charAt(i);

                if (ch != '(' && ch != ')') continue;

                String next = curr.substring(0, i) + curr.substring(i + 1);

                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        
        return result;
    }

    private boolean isValid(String curr) {
        int count = 0;

        for (int i = 0; i < curr.length(); i++) {
            char ch = curr.charAt(i);

            if (ch == '(') count++;
            else if (ch == ')') count--;

            if (count < 0) return false;
        }

        return count == 0;
    }
}