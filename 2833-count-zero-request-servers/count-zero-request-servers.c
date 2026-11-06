#include <stdlib.h>

typedef struct {
    int time;
    int index;
} Query;

static int compareLogs(const void* a, const void* b) {
    int* logA = *(int**)a;
    int* logB = *(int**)b;
    return logA[1] - logB[1];
}

static int compareQueries(const void* a, const void* b) {
    Query* qA = (Query*)a;
    Query* qB = (Query*)b;
    return qA->time - qB->time;
}

int* countServers(int n, int** logs, int logsSize, int* logsColSize, int x, int* queries, int queriesSize, int* returnSize) {
    *returnSize = queriesSize;
    int* ans = (int*)malloc(sizeof(int) * queriesSize);

    Query* sortedQueries = (Query*)malloc(sizeof(Query) * queriesSize);
    for (int i = 0; i < queriesSize; i++) {
        sortedQueries[i].time = queries[i];
        sortedQueries[i].index = i;
    }

    qsort(logs, logsSize, sizeof(int*), compareLogs);
    qsort(sortedQueries, queriesSize, sizeof(Query), compareQueries);

    int* count = (int*)calloc(n + 1, sizeof(int));
    int activeServers = 0;
    int left = 0;
    int right = 0;

    for (int i = 0; i < queriesSize; i++) {
        int rTime = sortedQueries[i].time;
        int lTime = rTime - x;

        while (right < logsSize && logs[right][1] <= rTime) {
            int serverId = logs[right][0];
            if (count[serverId] == 0) {
                activeServers++;
            }
            count[serverId]++;
            right++;
        }

        while (left < logsSize && logs[left][1] < lTime) {
            int serverId = logs[left][0];
            count[serverId]--;
            if (count[serverId] == 0) {
                activeServers--;
            }
            left++;
        }

        ans[sortedQueries[i].index] = n - activeServers;
    }

    free(sortedQueries);
    free(count);

    return ans;
}