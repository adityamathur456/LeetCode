class Solution {
    public int scoreOfParentheses(String s) {
        int parenthesesDepth = 0, parenthesesScore = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                parenthesesDepth++;
            } else {
                parenthesesDepth--;
                if (s.charAt(i - 1) == '(') {
                    parenthesesScore += 1 << parenthesesDepth;
                }
            }
        }

        return parenthesesScore;
    }
}