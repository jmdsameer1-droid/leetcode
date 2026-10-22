class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int pos0 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos0 = i;
                break;
            }
        }

        boolean isInc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 + i) % n] != i) {
                isInc = false;
                break;
            }
        }

        boolean isDec = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 - i + n) % n] != i) {
                isDec = false;
                break;
            }
        }

        if (!isInc && !isDec) {
            return -1;
        }

        int ans = Integer.MAX_VALUE;

        if (isInc) {
            ans = Math.min(ans, pos0);
            ans = Math.min(ans, 2 + (n - pos0) % n);
        }

        if (isDec) {
            int shift1 = (n - 1 - pos0);
            ans = Math.min(ans, 1 + shift1);

            int shift2 = (pos0 + 1) % n;
            ans = Math.min(ans, shift2 + 1);
        }

        return ans;
    }
}