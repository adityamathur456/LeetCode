class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] charArray = s.toCharArray();
        int openCount = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '(') {
                openCount++;
            } else if (charArray[i] == ')') {
                if (openCount > 0) 
                    openCount--;
                else
                    charArray[i] = '*';
            }
        }

        for (int i = charArray.length - 1; i >= 0 && openCount > 0; i--) {
            if (charArray[i] == '(') {
                charArray[i] = '*';
                openCount--;
            }
        }

        return new String(charArray).replace("*", "");
    }
}