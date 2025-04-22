import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList(int INITIAL_CAPACITY){
        if(INITIAL_CAPACITY < 0){
            throw new IllegalArgumentException("Capcaity must be postive");
        }
        data = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    /*Append to end */
    public void add(T element){
        ensureCapcity(size + 1);
        data[size++] = element;
    }

    public void add(int index, T element){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        ensureCapcity(size + 1);
        //shift [index.. size -1] right by 1
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    public T get(int index){
        rangeCheck(index);
        return data[index];
    }

    public T set(int index, T element){
        rangeCheck(index);
        T old = data[index];
        data[index] = element;
        return old;
    }

    //remove element at index, shift left by 1
    public T remove(int index){
        rangeCheck(index);
        T removed = data[index];
        int numMoved = size - index - 1;
        if(numMoved > 0){
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null; //help GC
        return removed;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /** Ensure internal array can hold at least minCapacity elements. */
    @SuppressWarnings("unchecked")
    private void ensureCapcity(int minCapacity){
        if(minCapacity > data.length){
            int newCapacity = Math.max(data.length *2, minCapacity);
            T[] newData = (T[]) new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    // check valid index range
    private void rangeCheck(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /*Iterator support for for-each loops */
    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            private int cursor = 0;

            @Override
            public boolean hasNext(){
                return cursor < size;
            }
            
            @Override
            public T next(){
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                return data[cursor++];
            }
            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }


}
