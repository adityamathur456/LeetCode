class Solution {

    private static final int MAX_PATTERN = 1000;
    private static final int WAVE_COUNT = 570;
    private static final int[] WAVE_PATTERNS = new int[WAVE_COUNT];

    static {
        int index = 0;

        for (int number = 0; number < MAX_PATTERN; number++) {
            int rightDigit = number % 10;
            int middleDigit = (number / 10) % 10;
            int leftDigit = (number / 100) % 10;

            boolean isPeak =
                    middleDigit > Math.max(leftDigit, rightDigit);

            boolean isValley =
                    middleDigit < Math.min(leftDigit, rightDigit);

            if (isPeak || isValley) {
                WAVE_PATTERNS[index++] = number;
            }
        }
    }

    public long totalWaviness(long start, long end) {
        return countWaveNumbers(end) - countWaveNumbers(start - 1);
    }

    private long countWaveNumbers(long limit) {
        if (limit < 100) {
            return 0;
        }

        long totalCount = 0;

        for (int wavePattern : WAVE_PATTERNS) {
            totalCount += countOccurrences(limit, wavePattern);
        }

        return totalCount;
    }

    private long countOccurrences(long limit, int pattern) {
        long leadingZeroAdjustment = pattern < 100 ? 1 : 0;

        long count = 0;
        long placeValue = 1;

        while (placeValue * 100 <= limit) {

            long higherPart = limit / (placeValue * 1000);
            long currentBlock = (limit / placeValue) % 1000;
            long lowerPart = limit % placeValue;

            long validBlocks;

            if (currentBlock > pattern) {
                validBlocks = higherPart - leadingZeroAdjustment + 1;
            } else if (currentBlock == pattern) {
                validBlocks = Math.max(0L, higherPart - leadingZeroAdjustment);
                count += lowerPart + 1;
            } else {
                validBlocks = Math.max(0L, higherPart - leadingZeroAdjustment);
            }

            count += validBlocks * placeValue;
            placeValue *= 10;
        }

        return count;
    }
}