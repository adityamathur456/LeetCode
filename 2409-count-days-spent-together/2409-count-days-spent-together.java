class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        return Math.max(0, Math.min(countsDays(leaveAlice), countsDays(leaveBob)) - Math.max(countsDays(arriveAlice), countsDays(arriveBob)) + 1);
    }

    private int countsDays(String person) {
        int p = (person.charAt(0) - '0') * 10 + (person.charAt(1) - '0');
        int q = (person.charAt(3) - '0') * 10 + (person.charAt(4) - '0');
        return (275 * p) / 9 - 2 * ((p + 9) / 12) + q - 30;
    }
}