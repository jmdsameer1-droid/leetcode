import java.util.*;

class Solution {
    private TreeMap<Long, Integer> low = new TreeMap<>();
    private TreeMap<Long, Integer> high = new TreeMap<>();
    private int sizeLow = 0, sizeHigh = 0;
    private long sumLow = 0, sumHigh = 0;
    private int kLow, kHigh;

    private void addMap(TreeMap<Long, Integer> map, long val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private void removeMap(TreeMap<Long, Integer> map, long val) {
        int count = map.get(val);
        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }

    private void balance() {
        while (sizeLow < kLow && sizeHigh > 0) {
            long val = high.firstKey();
            removeMap(high, val);
            sizeHigh--;
            sumHigh -= val;

            addMap(low, val);
            sizeLow++;
            sumLow += val;
        }
        while (sizeLow > kLow) {
            long val = low.lastKey();
            removeMap(low, val);
            sizeLow--;
            sumLow -= val;

            addMap(high, val);
            sizeHigh++;
            sumHigh += val;
        }
    }

    private void add(long val) {
        if (low.isEmpty() || val <= low.lastKey()) {
            addMap(low, val);
            sizeLow++;
            sumLow += val;
        } else {
            addMap(high, val);
            sizeHigh++;
            sumHigh += val;
        }
        balance();
    }

    private void remove(long val) {
        if (low.containsKey(val)) {
            removeMap(low, val);
            sizeLow--;
            sumLow -= val;
        } else {
            removeMap(high, val);
            sizeHigh--;
            sumHigh -= val;
        }
        balance();
    }

    private long getCost() {
        long median = low.lastKey();
        return (median * sizeLow - sumLow) + (sumHigh - median * sizeHigh);
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        kLow = (x + 1) / 2;
        kHigh = x - kLow;

        long[] cost = new long[n];
        for (int i = 0; i < n; i++) {
            add(nums[i]);
            if (i >= x) {
                remove(nums[i - x]);
            }
            if (i >= x - 1) {
                cost[i] = getCost();
            }
        }

        long INF = (long) 1e18;
        long[] dp = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] nextDp = new long[n + 1];
            Arrays.fill(nextDp, INF);
            for (int i = j * x; i <= n; i++) {
                nextDp[i] = Math.min(nextDp[i - 1], dp[i - x] + cost[i - 1]);
            }
            dp = nextDp;
        }

        return dp[n];
    }
}