package Generics;

public class DoublyNode<T> {
    T data;
    DoublyNode<T> next;
    DoublyNode<T> prev;

    public DoublyNode(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public T getData(){
        return data;
    }

    public DoublyNode<T> getNext(){
        return next;
    }

    public DoublyNode<T> getPrev(){
        return prev;
    }

    public void setData(T data){
        this.data = data;
    }

    public void setNext(DoublyNode<T> nextNode){
        this.next = nextNode;
        if(nextNode != null){
            nextNode.prev = this;
        }
    }

    public void setPrev(DoublyNode<T> prevNode){
        this.prev = prevNode;
        if(prevNode != null){
            prevNode.next = this;
        }
    }

    @Override
    public String toString(){
        return data.toString();
    }
}
