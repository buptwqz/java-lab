public class SeparateChainingHashTable<K, V> {
    private static final int DEFAULT_TABLE_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Node<K, V>[] table;
    private int size;
    private int capacity;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }
    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.table = new Node[capacity];
    }


    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = hash(key);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        table[index] = new Node<>(key, value, table[index]);
        size++;

        if ((double) size / capacity > LOAD_FACTOR) {
            resize(2 * capacity);
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = hash(key);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean remove(K key) {
        if (key == null) {
            return false;
        }
        int index = hash(key);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Node<K, V>[] oldTable = table;
        this.capacity = newCapacity;
        this.size = 0;
        this.table = new Node[newCapacity];

        for (Node<K, V> head : oldTable) {
            Node<K, V> current = head;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        SeparateChainingHashTable<String, Integer> hashMap = new SeparateChainingHashTable<>();

        // 测试插入
        hashMap.put("Alice", 25);
        hashMap.put("Bob", 30);
        hashMap.put("Charlie", 35);

        // 测试查找
        System.out.println("Alice's age: " + hashMap.get("Alice")); // 25
        System.out.println("Bob's age: " + hashMap.get("Bob")); // 30

        // 测试更新
        hashMap.put("Alice", 26);
        System.out.println("Alice's updated age: " + hashMap.get("Alice")); // 26

        // 测试删除
        hashMap.remove("Bob");
        System.out.println("Bob's age after removal: " + hashMap.get("Bob")); // null

        // 测试哈希冲突
        hashMap.put("Aa", 100);
        hashMap.put("BB", 200); // "Aa"和"BB"的hashCode可能相同，测试拉链法
    }

}
