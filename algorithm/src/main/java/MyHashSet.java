public class MyHashSet<E> {
    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 16;
    // 默认加载因子
    private static final double LOAD_FACTOR = 0.75;

    private Node<E>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    // 节点类
    private static class Node<E> {
        E key;
        Node<E> next;
        Node(E key) { this.key = key; }
    }

    // 哈希函数
    private int getIndex(Object key) {
        if (key == null) return 0;
        return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    // 添加元素
    public boolean add(E key) {
        if (contains(key)) return false;

        // 检查扩容
        if (size >= buckets.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key);
        Node<E> newNode = new Node<>(key);
        // 头插法
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        return true;
    }

    // 是否包含
    public boolean contains(Object key) {
        int index = getIndex(key);
        Node<E> curr = buckets[index];
        while (curr != null) {
            if (curr.key != null && curr.key.equals(key)) return true;
            if (curr.key == null && key == null) return true;
            curr = curr.next;
        }
        return false;
    }

    // 删除元素
    public boolean remove(Object key) {
        int index = getIndex(key);
        Node<E> curr = buckets[index];
        Node<E> prev = null;

        while (curr != null) {
            if ((curr.key == null && key == null) || (curr.key != null && curr.key.equals(key))) {
                if (prev == null) buckets[index] = curr.next;
                else prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    // 扩容机制
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<E>[] oldBuckets = buckets;
        buckets = new Node[oldBuckets.length * 2];
        size = 0; // 重新计数，因为 add 会增加 size

        for (Node<E> head : oldBuckets) {
            while (head != null) {
                add(head.key); // 重新插入到新桶中
                head = head.next;
            }
        }
    }

    public int size() { return size; }
}