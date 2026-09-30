class Solution {
    public String orderlyQueue(String s, int k) {
        if (k > 1) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }

        String smallest = s;

        for (int i = 1; i < s.length(); i++) {
            String rotation = s.substring(i) + s.substring(0, i);

            if (rotation.compareTo(smallest) < 0) 
                smallest = rotation;
        }

        return smallest;
    }
}