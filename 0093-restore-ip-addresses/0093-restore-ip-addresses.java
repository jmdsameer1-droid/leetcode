class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int startIndex, List<String> currentSegments, List<String> result) {
        if (currentSegments.size() == 4) {
            if (startIndex == s.length()) {
                result.add(String.join(".", currentSegments));
            }
            return;
        }

        for (int len = 1; len <= 3 && startIndex + len <= s.length(); len++) {
            String segment = s.substring(startIndex, startIndex + len);

            if ((segment.startsWith("0") && segment.length() > 1) || Integer.parseInt(segment) > 255) {
                continue;
            }

            currentSegments.add(segment);
            backtrack(s, startIndex + len, currentSegments, result);
            currentSegments.remove(currentSegments.size() - 1);
        }
    }
}