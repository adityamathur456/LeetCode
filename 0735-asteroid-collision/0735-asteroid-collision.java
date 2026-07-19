class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> collisionStack = new Stack<>();

        for (int asteroid : asteroids) {
            while (!collisionStack.isEmpty() && asteroid < 0 && collisionStack.peek() > 0) {
                int diff = asteroid + collisionStack.peek();

                if (diff < 0) {
                    collisionStack.pop();
                } else if (diff > 0) {
                    asteroid = 0;
                } else{
                    asteroid = 0;
                    collisionStack.pop();
                }
            }

            if (asteroid != 0) 
                collisionStack.push(asteroid);
        }

        return collisionStack.stream().mapToInt(i -> i).toArray();
    }
}