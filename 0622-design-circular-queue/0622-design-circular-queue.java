class ListNode {
    int val;
    ListNode next, prev;

    ListNode(int val, ListNode next, ListNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}

class MyCircularQueue {
    private int space;
    private ListNode left, right;

    public MyCircularQueue(int k) {
        space = k;
        left = new ListNode(0, null, null);
        right = new ListNode(0, null, left);
        left.next = right;
    }
    
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }

        ListNode cur = new ListNode(value, right, right.prev);
        right.prev.next = cur;
        right.prev = cur;
        space--;
        return true;
    }
    
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        left.next = left.next.next;
        left.next.prev = left;
        space++;
        return true;
    }
    
    public int Front() {
        return isEmpty() ? -1 : left.next.val;
    }
    
    public int Rear() {
        return isEmpty() ? -1 : right.prev.val;
    }
    
    public boolean isEmpty() {
        return left.next == right;
    }
    
    public boolean isFull() {
        return space == 0;
    }
}