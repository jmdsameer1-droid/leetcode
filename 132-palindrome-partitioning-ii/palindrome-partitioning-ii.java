class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] isPal = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            int minCuts = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || isPal[j + 1][i - 1])) {
                    isPal[j][i] = true;
                    minCuts = (j == 0) ? 0 : Math.min(minCuts, dp[j - 1] + 1);
                }
            }
            dp[i] = minCuts;
        }

        return dp[n - 1];
    }
}