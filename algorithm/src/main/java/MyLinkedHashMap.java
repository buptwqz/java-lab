public class MyLinkedHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;          // 哈希桶链表指针
        Entry<K, V> before, after; // 双向链表指针（记录顺序）

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] table;
    private int capacity = 16;
    private Entry<K, V> head, tail; // 记录插入顺序的双向链表

    @SuppressWarnings("unchecked")
    public MyLinkedHashMap() {
        table = new Entry[capacity];
    }

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode() & 0x7FFFFFFF) % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> existing = table[index];

        // 1. 更新逻辑
        while (existing != null) {
            if (existing.key.equals(key)) {
                existing.value = value;
                return;
            }
            existing = existing.next;
        }

        // 2. 插入逻辑
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = table[index]; // 处理哈希冲突（头插）
        table[index] = newEntry;

        linkLast(newEntry); // 维护双向链表顺序
    }

    // 将节点挂在双向链表末尾
    private void linkLast(Entry<K, V> entry) {
        Entry<K, V> last = tail;
        tail = entry;
        if (last == null) {
            head = entry;
        } else {
            entry.before = last;
            last.after = entry;
        }
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> curr = table[index];
        while (curr != null) {
            if (curr.key.equals(key)) return curr.value;
            curr = curr.next;
        }
        return null;
    }

    // 按照插入顺序遍历
    public void forEach() {
        Entry<K, V> curr = head;
        while (curr != null) {
            System.out.print("[" + curr.key + ": " + curr.value + "] -> ");
            curr = curr.after;
        }
        System.out.println("null");
    }
}