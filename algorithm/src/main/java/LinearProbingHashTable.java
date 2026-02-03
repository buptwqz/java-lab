import java.util.Map;

public class LinearProbingHashTable<K, V> {
    private static final int DEFAULT_TABLE_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
    private int size;

    private final double loadFactor;

    public LinearProbingHashTable(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        this.table = new Entry[initialCapacity];
        this.size = 0;
    }

    public LinearProbingHashTable() {
        this(DEFAULT_TABLE_SIZE, LOAD_FACTOR);
    }

    private static class Entry<K, V> {
        K key;
        V value;
        boolean isActive;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.isActive = true;
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        if ((double) size / table.length > loadFactor) {
            resize(2 * table.length);
        }

        int index = hash(key);

        while (table[index] != null) {
            if (table[index].key.equals(key) && table[index].isActive) {
                table[index].value = value;
                return;
            }
            index = (index + 1) % table.length;
        }

        table[index] = new Entry<>(key, value);
        size++;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        int index = hash(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].key == key && table[index].isActive) {
                return table[index].value;
            }
            index = (index + 1) % table.length;
            if (index == startIndex) {
                break;
            }
        }
        return null;
    }

    public boolean remove(K key) {
        if (key == null) {
            return false;
        }
        int index = hash(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index].key.equals(key) && table[index].isActive) {
                table[index].isActive = false;
                size--;
                return true;
            }
            index = (index + 1) % table.length;
            if (index == startIndex) {
                break;
            }
        }
        return false;
    }

    private void resize(int newCapacity) {
        Entry<K, V>[] oldTable = table;
        table = new Entry[newCapacity];
        size = 0;
        for (Entry<K, V> entry : oldTable) {
            if (entry != null && entry.isActive) {
                put(entry.key, entry.value);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
