package lru_cache;

import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentCache<K,V> implements Cache<K,V> {
    private final Cache<K, V> cache;
    private final ReadWriteLock lock;

    public ConcurrentCache(Cache <K,V> cache){
        this.cache = cache;
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    //write lock
    public Optional<V> get(K key){
        lock.writeLock().lock(); //LRUCache mutates state in get()
        try{
            return cache.get(key);
        }finally{
            lock.writeLock().unlock();
        }

    }

    @Override
    //write lock, mutates states with inserts
    public void put(K key, V value){
        lock.writeLock().lock();
        try{
            cache.put(key, value);
        }finally{
            lock.writeLock().unlock();
        }

    }

    @Override
    // read lock
    public boolean containsKey(K key) {
        lock.readLock().lock();
        try {
            return cache.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    //read lock
    public int size() {
        lock.readLock().lock();
        try {
            return cache.size();
        } finally {
            lock.readLock().unlock();
        }
    }

}
