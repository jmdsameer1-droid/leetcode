class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // Step 1: Create a copy of each node and insert it next to the original node
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Assign random pointers for the copied nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate the original and copied lists
        curr = head;
        Node dummy = new Node(0);
        Node copyCurr = dummy;

        while (curr != null) {
            Node nextOriginal = curr.next.next;
            Node copy = curr.next;
            
            copyCurr.next = copy;
            copyCurr = copy;

            curr.next = nextOriginal;
            curr = nextOriginal;
        }

        return dummy.next;
    }
}