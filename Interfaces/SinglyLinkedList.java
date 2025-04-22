import Generics.SinglyNode;

public class SinglyLinkedList<T> {
    private SinglyNode<T> head;
    private int size;

    public SinglyLinkedList(){
        this.head = null;
        this.size = 0;
    }

    //add to End, O(N) for singly-linked list
    public void addEnd (T value){
        SinglyNode<T> newNode = new SinglyNode<>(value);
        if(this.head == null){
            this.head = newNode;
        }else{
            addEndHelper(newNode);
        }
        this.size++;
    }

    private void addEndHelper(SinglyNode<T> newNode){
        SinglyNode<T> current = this.head;
        while(current.getNext() != null){
            current = current.getNext();
        }
        current.setNext(newNode);
    }

    // adding to the beginning, constant time operation
    public void addFirst(T value){
        addFirstHelper(value);
        this.size++;
    }

    private void addFirstHelper(T value){
        SinglyNode<T> insert = new SinglyNode<>(value);
        insert.setNext(this.head); // 1. new node points to old head
        this.head = insert;        // 2. move head to the new node
    }

    // get value at given index
    public T get(int index){
        return getHelper(this.head, index);
    }

    private T getHelper(SinglyNode<T> current, int index){
        //Edge case: negative index or index larger than current size
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("negative index or index out of bounds");
        }
        for(int i=0; i < index; i++){
            current = current.getNext();
        }
        return current.getData();
    }

    //add value at certain index
    public void addIndex(int index, T value){
        this.head = addIndexHelper(index, value);
        this.size++;
    }

    private SinglyNode<T> addIndexHelper(int index, T value){
        if(index < 0 || index > this.size){ //Note: add indices can be out of bounds by 1 to add to end of list 
            throw new IndexOutOfBoundsException("negative index or index out of bounds");
        }
        SinglyNode<T> root = this.head;
        SinglyNode<T> newNode = new SinglyNode<>(value);
        if(index == 0){
            newNode.setNext(root);
            root = newNode;
        }else{
            SinglyNode<T> prev = root;
            for(int i = 0; i < index - 1; i++){
                prev = prev.getNext();
            }
            newNode.setNext(prev.getNext());
            prev.setNext(newNode);
        }
        return root;
    }

    //set value at certain index
    public T set(int index, T value){
        return setHelper(index, value);
    }

    private T setHelper(int index, T value){
        //Edge case: negative index or index larger than current size
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("negative index or index out of bounds");
        }
        SinglyNode<T> current = this.head;
        for(int i=0; i < index; i++){
            current = current.getNext();
        }
        current.setData(value);
        return current.getData();
    }

    public T remove(int index){
        return removeHelper(index);
    }

    private T removeHelper(int index){
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("negative index or index out of bounds");
        }
        T removedData;
        if(index == 0){
            removedData = this.head.getData();
            this.head = this.head.getNext();
        }else{
            SinglyNode<T> prev = this.head;
            for(int i = 0; i < index - 1; i++){
                prev = prev.getNext();
            }
            SinglyNode<T> toRemove = prev.getNext();
            removedData = toRemove.getData();
            prev.setNext(toRemove.getNext());
        }
        this.size--;
        return removedData;
    }
}
