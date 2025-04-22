package Generics;

public class SinglyNode<T>{
    T data;
    SinglyNode<T> next;

    public SinglyNode(T data){
        this.data = data;
        this.next = null;
    }

    public T getData(){
        return data;
    }

    public SinglyNode<T> getNext(){
        return next;
    }

    public void setData(T data){
        this.data = data;
    }

    public void setNext(SinglyNode<T> next){
        this.next = next;
    }

    @Override
    public String toString(){
        return data.toString();
    }
}
