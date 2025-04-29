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
        for(int i = level; i >=0; i--){ //iterate by levels 
            while(current.getNext(i) != null &&
                current.getNext(i).getData().compareTo(target)< 0){ // when current node is lower than target, go to next node
                current = current.getNext(i);
            }
        }

        current = current.getNext(0); //goes to the next level if getData() > target, until we find the result
        return current != null && current.getData().compareTo(target) == 0;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(T target){
        SkipNode<T>[] update = new SkipNode[MAX_LEVEL + 1];
        SkipNode<T> current = head;

        // traverse top-down to save update links
        for(int i = level; i >= 0; i--){
            while(current.getNext(i) != null && 
                current.getNext(i).getData().compareTo(target) < 0){
                    current = current.getNext(i);
            }
            update[i] = current; 
        }
    
        current = current.getNext(0);
        if(current != null && current.getData().compareTo(target) == 0){
            for(int i = 0; i <= level; i++){
                if(update[i].getNext(i) != current) break;
                update[i].setNext(i, current.getNext(i));
            }

            while(level > 0 && head.getNext(level) == null){
                level--;
            }

            return true;
        }

        return false;
        
    }

    @SuppressWarnings("unchecked")
    public void insert(T value){
        SkipNode<T>[] update = new SkipNode[MAX_LEVEL + 1];
        SkipNode<T> current = head;

        // traverse top-down to save update links
        for(int i = level; i >= 0; i--){
            while(current.getNext(i) != null && 
                current.getNext(i).getData().compareTo(value) < 0){
                    current = current.getNext(i);
            }
            update[i] = current; 
        }

        current = current.getNext(0);
        if(current == null || !current.getData().equals(value)){
            int newLevel = randomLevel();
            if(newLevel > level){
                for(int i = level + 1; i < newLevel; i++){
                    update[i] = head;
                }
                level = newLevel;
            }

            SkipNode<T> newNode = new SkipNode<>(newLevel, value);
            for (int i = 0; i <= newLevel; i++) {
                newNode.setNext(i, update[i].getNext(i));
                update[i].setNext(i, newNode);
            }
        }
    }

    private int randomLevel(){
        int lvl = 0;
        while(rand.nextFloat() < p && lvl < MAX_LEVEL){
            lvl++;
        }
        return lvl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = level; i >= 0; i--) {
            SkipNode<T> node = head.getNext(i);
            sb.append("Level ").append(i).append(": ");
            while (node != null) {
                sb.append(node.getData()).append(" -> ");
                node = node.getNext(i);
            }
            sb.append("null\n");
        }
        return sb.toString();
    }
}
