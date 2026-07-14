class Solution {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {

        Map<Long, Long> bucket = new HashMap<>();
        long bucketSize = (long) valueDiff + 1;

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            long num = nums[i];
            long bucketId = getBucketId(num, bucketSize);

            if (bucket.containsKey(bucketId)
                    || (bucket.containsKey(bucketId - 1)
                        && Math.abs(num - bucket.get(bucketId - 1)) <= valueDiff)
                    || (bucket.containsKey(bucketId + 1)
                        && Math.abs(num - bucket.get(bucketId + 1)) <= valueDiff)) {
                return true;
            }

            bucket.put(bucketId, num);

            if (i >= indexDiff) {
                long oldBucketId = getBucketId(nums[i - indexDiff], bucketSize);
                bucket.remove(oldBucketId);
            }
        }

        return false;
    }

    private long getBucketId(long num, long bucketSize) {
        if (num >= 0) {
            return num / bucketSize;
        }

        return ((num + 1) / bucketSize) - 1;
    }
}