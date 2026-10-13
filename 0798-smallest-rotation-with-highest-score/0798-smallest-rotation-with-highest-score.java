class Solution {
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            int high = (i - nums[i] + n + 1) % n;
            diff[low]++;
            diff[high]--;
            if (low >= high) {
                diff[0]++;
            }
        }

        int maxScore = 0;
        int bestK = 0;
        int currentScore = 0;

        for (int k = 0; k < n; k++) {
            currentScore += diff[k];
            if (currentScore > maxScore) {
                maxScore = currentScore;
                bestK = k;
            }
        }

        return bestK;
    }
}