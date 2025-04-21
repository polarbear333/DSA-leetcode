package Generics;

public class Node<T extends Comparable<T>>{
    T data;
    Node<T> left;
    Node<T> right; 

    public Node(T data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // getters
    public T getData() {
        return data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    // setters
    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public String toString(){
        return data.toString();
    }
}

/* Non-generic version

 * class Node {
    Object data;
    Node left;
    Node right;

    public Node(Object data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public String toString() {
        return data.toString(); // Might need to be careful with nulls and types
    }
}

 * 
 * 
 */