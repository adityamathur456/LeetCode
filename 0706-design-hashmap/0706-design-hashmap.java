class MyHashMap {
    private static class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int CAPACITY = 1009; // Prime number
    private Node[] buckets;
    private int size;

    public MyHashMap() {
        buckets = new Node[CAPACITY];
        size = 0;
    }

    private int hash(int key) {
        return Math.abs(key) % CAPACITY;
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        Node curr = buckets[index];

        while (curr != null) {
            if (curr.key == key) {
                curr.value = value;
                return;
            }
            curr = curr.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }
    
    public int get(int key) {
        int index = hash(key);
        Node curr = buckets[index];
        
        while (curr != null) {
            if (curr.key == key) 
                return curr.value;
            curr = curr.next;
        }

        return -1;
    }
    
    public void remove(int key) {
        int index = hash(key);

        Node curr = buckets[index];
        Node prev = null;

        while (curr != null) {
            if (curr.key == key) {
                if (prev == null) {
                    buckets[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */