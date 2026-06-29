class Solution {
    public int numOfStrings(String[] substrs, String word) {
        int count = 0;

        for (String substr : substrs) {
            if (word.contains(substr)) {
                count++;
            }
        }

        return count;
    }
}