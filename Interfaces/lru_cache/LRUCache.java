package lru_cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> cacheMap;
    private final DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    @Override
    public Optional<V> get(K key) {
        if (!cacheMap.containsKey(key)) {
            return Optional.empty();
        }

        Node<K, V> node = cacheMap.get(key);
        dll.moveToFront(node);
        return Optional.of(node.value);
    }

    @Override
    public void put(K key, V value) {
        if (cacheMap.containsKey(key)) {
            Node<K, V> node = cacheMap.get(key);
            node.value = value;
            dll.moveToFront(node);
        } else {
            if (cacheMap.size() >= capacity) {
                Node<K, V> lru = dll.removeLast();
                if (lru != null) {
                    cacheMap.remove(lru.key);
                }
            }
            Node<K, V> newNode = new Node<>(key, value);
            dll.addFirst(newNode);
            cacheMap.put(key, newNode);
        }
    }

    @Override
    public boolean containsKey(K key) {
        return cacheMap.containsKey(key);
    }

    @Override
    public int size() {
        return cacheMap.size();
    }

    Optional<K> getLastRecentKey() {
        return dll.head != null ? Optional.of(dll.head.key) : Optional.empty();
    }

    // Doubly Linked List Node
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Doubly Linked List to manage LRU order
    private static class DoublyLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        void addFirst(Node<K, V> node) {
            node.next = head;
            node.prev = null;
            if (head != null) {
                head.prev = node;
            }
            head = node;
            if (tail == null) {
                tail = node;
            }
        }

        void moveToFront(Node<K, V> node) {
            if (node == head) return;
            remove(node);
            addFirst(node);
        }

        void remove(Node<K, V> node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }

            node.prev = null;
            node.next = null;
        }

        Node<K, V> removeLast() {
            if (tail == null) return null;
            Node<K, V> removed = tail;
            remove(tail);
            return removed;
        }
    }
}
