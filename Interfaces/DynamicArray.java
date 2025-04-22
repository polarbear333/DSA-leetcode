public class DynamicArray<T> {
    private T[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public DynamicArray(){
        //creates raw array of `Object` w/ initial size, then casts to a T[] array for data.
        this.data = (T[]) new Object[INITIAL_CAPACITY]; 
        this.size = 0;
    }

    private void ensureCapcity(){
        if(size == data.length){
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(){ //resizes array to double the capacity in a new array
        int newCapacity = data.length * 2;
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size); //faster as a native method
        /* 
        for(int i=0; i < size; i++){
            newData[i] = data[i];
        }
        */

        data = newData;
    }

    private void checkIndex(int index){
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("invalid index");
        }
    }

    public void add(T value){
        ensureCapcity();
        data[size++] = value;
    }

    public T get(int index){
        checkIndex(index);
        return data[index];
    }

    public T set(int index, T value){
        checkIndex(index);
        T oldValue = data[index];
        data[index] = value;
        return oldValue;
    }

    public T remove(int index) {
        checkIndex(index);
        T removed = data[index];
        // shift elements to the left
        for(int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null; // help with garbage collection
        
        return removed;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString(){
        if(size == 0){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<size - 1; i++){
            sb.append(data[i]).append(", ");
        }
        sb.append(data[size - 1]).append("] ");
        return sb.toString();
    }
}

