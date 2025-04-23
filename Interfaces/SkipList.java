import java.util.Random;

import Generics.SkipNode;

public class SkipList<T extends Comparable<T>> {
    private static final float DEFAULT_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private final SkipNode<T> head;
    private int level;
    private final float p;
    private final Random rand;

    public SkipList(){
        this.head = new SkipNode<>(MAX_LEVEL, null);
        this.level = 0;
        this.p = DEFAULT_P;
        this.rand = new Random();
    }

    //search
    public boolean search(T target){
        SkipNode<T> current = head;
        for(int i = level; i >=0; i--){
            while(current.getNext(i) != null &&
                current.getNext(i).getData().compareTo(target)< 0){ // when current node is lower than target, go to next node
                current = current.getNext(i);
            }
        }

        current = current.getNext(0);
        return current != null && current.getData().compareTo(target) == 0;
    }

}
