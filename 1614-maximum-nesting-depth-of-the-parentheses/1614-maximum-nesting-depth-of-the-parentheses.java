class Solution {
    public int maxDepth(String s) {
        int res = 0, depth = 0;
        for (char ch : s.toCharArray()) {
            depth += ch == '(' ? 1 : ch == ')' ? -1 : 0;
            res = Math.max(res, depth);
        }
        return res;
    }
}