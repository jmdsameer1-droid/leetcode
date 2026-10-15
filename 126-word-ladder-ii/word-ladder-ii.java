import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return results;
        }

        // Map each word to its valid predecessors on the shortest paths
        Map<String, List<String>> predecessors = new HashMap<>();
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);
        dict.remove(beginWord);

        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {
            Set<String> nextLevel = new HashSet<>();

            for (String word : currentLevel) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char orig = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == orig) continue;
                        chars[i] = c;
                        String nextWord = new String(chars);

                        if (dict.contains(nextWord)) {
                            nextLevel.add(nextWord);
                            predecessors.computeIfAbsent(nextWord, k -> new ArrayList<>()).add(word);
                            if (nextWord.equals(endWord)) {
                                found = true;
                            }
                        }
                    }
                    chars[i] = orig;
                }
            }

            // Remove all words seen in nextLevel from dict so subsequent levels don't revisit them
            dict.removeAll(nextLevel);
            currentLevel = nextLevel;
        }

        if (found) {
            List<String> path = new LinkedList<>();
            path.add(endWord);
            backtrack(endWord, beginWord, predecessors, path, results);
        }

        return results;
    }

    private void backtrack(String word, String beginWord, Map<String, List<String>> predecessors, List<String> path, List<List<String>> results) {
        if (word.equals(beginWord)) {
            results.add(new ArrayList<>(path));
            return;
        }

        List<String> parents = predecessors.get(word);
        if (parents == null) return;

        for (String parent : parents) {
            path.add(0, parent);
            backtrack(parent, beginWord, predecessors, path, results);
            path.remove(0);
        }
    }
}