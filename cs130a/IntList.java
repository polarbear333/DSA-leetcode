public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    public static void main(String args[]){

        IntList L = new IntList(5, null);
        L.rest = new IntList(10, null);
        L.rest.rest = new IntList(15, null);
    
    }

    public int size(){
        if(rest == null){
            return 1;
        }

        return 1 + this.rest.size();
    }

    public int iterativeSize(){
        IntList current = this; 
        /* current variable is holding a pointer, we need that pointer
        since we cannot reassign a pointer in Java */
        int size = 0;
        while(current != null){
            size++;
            current = current.rest;
        }
        return size;
    }

    public int get(int index) throws IndexOutOfBoundsException{
        if (index == 0){
            return first;
        }
        return rest.get(index - 1);
    }

    public int iterativeGet(int index) throws IndexOutOfBoundsException{
        IntList current = this;
        while(index > 0){
            current = current.rest;
            index--;
        }
        return current.first;
    }

    public void addFirst(int element){
        IntList newRest = new IntList(first, rest);
        first = element;
        rest = newRest;
    }

}



