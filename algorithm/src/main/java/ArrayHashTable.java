import java.util.*;

/**
 * 数组加强哈希表 - 支持随机键访问
 * 在O(1)时间内返回随机键
 */
public class ArrayHashTable<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final List<Entry<K, V>> array;  // 存储键值对的紧凑数组
    private final Map<K, Integer> indexMap; // 键到数组索引的映射
    private final Random random;

    public ArrayHashTable() {
        this.array = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.random = new Random();
    }

    public ArrayHashTable(int initialCapacity) {
        this.array = new ArrayList<>(initialCapacity);
        this.indexMap = new HashMap<>(initialCapacity);
        this.random = new Random();
    }

    /**
     * 获取值
     */
    public V get(K key) {
        Integer index = indexMap.get(key);
        return index == null ? null : array.get(index).value;
    }

    /**
     * 添加键值对
     */
    public V put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        Integer index = indexMap.get(key);
        if (index != null) {
            // 键已存在，更新值
            V oldValue = array.get(index).value;
            array.get(index).value = value;
            return oldValue;
        } else {
            // 新键，添加到数组末尾
            Entry<K, V> entry = new Entry<>(key, value);
            array.add(entry);
            indexMap.put(key, array.size() - 1);
            return null;
        }
    }

    /**
     * 删除键值对
     * 使用交换技巧保持数组紧凑
     */
    public V remove(K key) {
        Integer index = indexMap.get(key);
        if (index == null) return null;

        // 获取要删除的元素
        Entry<K, V> toRemove = array.get(index);

        if (index == array.size() - 1) {
            // 如果是最后一个元素，直接删除
            array.remove(index.intValue());
        } else {
            // 将最后一个元素移动到要删除的位置
            Entry<K, V> last = array.get(array.size() - 1);
            array.set(index, last);
            array.remove(array.size() - 1);

            // 更新被移动元素的索引映射
            indexMap.put(last.key, index);
        }

        // 移除索引映射
        indexMap.remove(key);
        return toRemove.value;
    }

    /**
     * 随机返回一个键（均匀随机）
     */
    public K randomKey() {
        if (array.isEmpty()) return null;
        int randomIndex = random.nextInt(array.size());
        return array.get(randomIndex).key;
    }

    /**
     * 随机返回一个值（均匀随机）
     */
    public V randomValue() {
        if (array.isEmpty()) return null;
        int randomIndex = random.nextInt(array.size());
        return array.get(randomIndex).value;
    }

    /**
     * 随机返回一个键值对（均匀随机）
     */
    public Map.Entry<K, V> randomEntry() {
        if (array.isEmpty()) return null;
        int randomIndex = random.nextInt(array.size());
        Entry<K, V> entry = array.get(randomIndex);
        return new AbstractMap.SimpleEntry<>(entry.key, entry.value);
    }

    public int size() { return array.size(); }
    public boolean isEmpty() { return array.isEmpty(); }
    public boolean containsKey(K key) { return indexMap.containsKey(key); }

    /**
     * 获取所有键（无序）
     */
    public Set<K> keySet() {
        return indexMap.keySet();
    }

    /**
     * 获取所有值（无序）
     */
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Entry<K, V> entry : array) {
            values.add(entry.value);
        }
        return values;
    }

    @Override
    public String toString() {
        return array.toString();
    }
}