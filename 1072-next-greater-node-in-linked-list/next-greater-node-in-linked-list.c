int* nextLargerNodes(struct ListNode* head, int* returnSize) {
    int capacity = 10000;
    int* values = (int*)malloc(capacity * sizeof(int));
    int n = 0;

    struct ListNode* curr = head;
    while (curr != NULL) {
        values[n++] = curr->val;
        curr = curr->next;
    }

    int* ans = (int*)calloc(n, sizeof(int));
    int* stack = (int*)malloc(n * sizeof(int));
    int top = -1;

    for (int i = 0; i < n; i++) {
        while (top >= 0 && values[stack[top]] < values[i]) {
            ans[stack[top--]] = values[i];
        }
        stack[++top] = i;
    }

    free(values);
    free(stack);

    *returnSize = n;
    return ans;
}