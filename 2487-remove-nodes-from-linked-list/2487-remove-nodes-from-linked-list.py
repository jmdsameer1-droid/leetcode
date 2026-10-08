# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next


class Solution(object):

  def removeNodes(self, head):
    """
    :type head: Optional[ListNode]
    :rtype: Optional[ListNode]
    """
    stack = []
    curr = head

    while curr:
      while stack and stack[-1].val < curr.val:
        stack.pop()
      stack.append(curr)
      curr = curr.next

    dummy = ListNode(0)
    curr = dummy
    for node in stack:
      curr.next = node
      curr = curr.next
    curr.next = None

    return dummy.next