package Generics;

public class SkipNode<T> {
    T data;
    SkipNode<T>[] forward; //array of forward pointers


    @SuppressWarnings("unchecked")
    public SkipNode(int level, T data){
        this.data = data;
        this.forward = new SkipNode[level + 1]; // level 0 to N
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public SkipNode<T> getNext(int level){
        return forward[level];
    }

    public void setNext(int level, SkipNode<T> node){
        forward[level] = node;
    }

    public int getLevel(){
        return forward.length - 1;
    }

    @Override
    public String toString(){
        return data == null ? "HEAD" : data.toString();
    }
}
