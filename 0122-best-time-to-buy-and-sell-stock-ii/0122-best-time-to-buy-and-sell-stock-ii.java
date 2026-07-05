class Solution {
    public int maxProfit(int[] prices) {
        int nextBuy = 0, nextSell = 0;
        int curBuy = 0, curSell = 0;

        for (int i = prices.length - 1; i >= 0; i--) {
            curBuy = Math.max(nextBuy, -prices[i] + nextSell);
            curSell = Math.max(nextSell, prices[i] + nextBuy);
            nextBuy = curBuy;
            nextSell = curSell;
        }

        return curBuy;
    }
}