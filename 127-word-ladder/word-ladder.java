class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                char[] wordsChars = currentWord.toCharArray();

                for (int j = 0; j < wordsChars.length; j++) {
                    char originalChar = wordsChars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (wordsChars[j] == c) continue;
                        wordsChars[j] = c;
                        String newWord = String.valueOf(wordsChars);

                        if (newWord.equals(endWord)) {
                            return level + 1;
                        }

                        if (set.contains(newWord)) {
                            queue.offer(newWord);
                            set.remove(newWord);
                        }
                    }
                    wordsChars[j] = originalChar;
                }
            }
            level++;
        }

        return 0;
    }
}