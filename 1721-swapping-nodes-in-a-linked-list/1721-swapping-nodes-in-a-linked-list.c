struct ListNode* swapNodes(struct ListNode* head, int k) {
    struct ListNode *first = head;
    for (int i = 1; i < k; i++) {
        first = first->next;
    }

    struct ListNode *curr = first;
    struct ListNode *second = head;

    while (curr->next != NULL) {
        curr = curr->next;
        second = second->next;
    }

    int temp = first->val;
    first->val = second->val;
    second->val = temp;

    return head;
}