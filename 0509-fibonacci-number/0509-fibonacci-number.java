class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n > 0 && n <= 2) return 1;

        int fib1 = 1, fib2 = 1;

        for (int i = 3; i <= n; i++) {
            int tempFib = fib2;
            fib2 = fib1 + fib2;
            fib1 = tempFib;
        }

        return fib2;
    }
}