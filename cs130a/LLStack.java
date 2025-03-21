import java.util.EmptyStackException;

public class LLStack {
    
    private class Node{
        private int value;
        private Node next;

        private Node(int value){
            this.value = value;
            this.next = null;
        }

        private int getValue(){
            return this.value;
        }
        private Node getNext(){
            return this.next;
        }
        private void setNext(Node data){
            this.next = data;
        }

    }

    private Node head;
    private int size;

    public LLStack(){
        head = null;
        size = 0;
    }

    public void push(int value){
        Node temp = new Node(value);
        if(head == null){
            head = temp;
        }else{
            temp.setNext(head);
            head = temp;
        }
        size++;
    }

    public int pop(){
        if(head == null){
            throw new NullPointerException("Head of the stack is null");
        }
        int value = head.getValue();
        head = head.getNext();
        size--;

        return value;
    }

    public int size(){
        return this.size;
    }
    
    public int peek(){
        if(head == null){
            throw new EmptyStackException();
        }
        return this.head.getValue();
    }

}

