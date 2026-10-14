class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        long val = 1;

        for (int j = 0; j <= rowIndex; j++) {
            row.add((int) val);
            val = val * (rowIndex - j) / (j + 1);
        }

        return row;
    }
}