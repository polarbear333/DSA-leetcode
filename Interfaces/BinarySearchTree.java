import java.util.ArrayList;
import java.util.List;
import Generics.Node;



public class BinarySearchTree<T extends Comparable<T>>{
    private Node<T> root;
    public BinarySearchTree(){
        this.root = null;
    }

    // ---Insertion---
    public void insert(T data){
        this.root = insertHelper(this.root, data);
    }

    private Node<T> insertHelper(Node<T> current, T data){
        //Base case: if current node is null, insert the spot
        if(current == null){
            return new Node<>(data);
        }
        //compare with current node's data
        int compare = data.compareTo(current.getData());
        if(compare < 0){
            //move left
            current.setLeft(insertHelper(current.getLeft(), data));
        }else if(compare > 0){
            //move right
            current.setRight(insertHelper(current.getRight(), data));
        }  //else data already exists, do nothing;
        return current;
    }

    // ---Search---
    public boolean search (T data){
        return searchHelper(this.root, data);
    }

    private boolean searchHelper(Node<T> current, T data){
        //Base case: return false if current node is null;
        if(current == null){
            return false;
        }

        int compare = data.compareTo(current.getData());
        if(compare == 0){
            return true;
        }else if (compare < 0){
            return searchHelper(current.getLeft(), data);
        }else{
            return searchHelper(current.getRight(), data);            
        }
    }

    //---Deletion---

    public void delete (T data){
        this.root = deleteHelper(this.root, data);
    }

    private Node<T> deleteHelper(Node<T> current, T data){
        //Base case: if current node is null, return node
        if(current == null){
            return current;
        }
        //compare with current node's data
        int compare = data.compareTo(current.getData());
        // If data is in the subtree
        if (compare > 0){
            current.setRight(deleteHelper(current.getRight(), data));
        }else if(compare < 0){  
            current.setLeft(deleteHelper(current.getLeft(), data));
        }else{
            // Cases when root has 0 children or
            // only right child

            if(current.getLeft() == null){
                return current.getRight();
            }

            // When root has only left child            
            if (current.getRight() == null) {
                return current.getLeft();
            }

            //when both childs are present 
            Node<T> succ = getSuccessor(current);
            current.setData(succ.getData());
            current.setRight(deleteHelper(current.getRight(), succ.getData()));
        }
        return current;
    }

    private Node<T> getSuccessor(Node<T> current){
        current = current.getRight();
        while(current != null && current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    // In-order traversal
    public List<T> inOrderTraversal(){
        List<T> result = new ArrayList<>();
        inOrderHelper(this.root, result);
        return result;
    }

    private void inOrderHelper(Node<T> node, List<T> result){
        if(node != null){
            // Traverse left subtree
            inOrderHelper(node.getLeft(), result);
            // Visit root node
            result.add(node.getData());
            // Traverse right subtree
            inOrderHelper(node.getRight(), result);
        }
    }

    public List<T> preOrderTraversal(){
        List<T> result = new ArrayList<>();
        preOrderHelper(this.root, result);
        return result;
    }

    private void preOrderHelper(Node<T> node, List<T> result){
        if(node != null){
            result.add(node.getData());
            preOrderHelper(node.getLeft(), result);
            preOrderHelper(node.getRight(), result);
        }
    }

    public List<T> postOrderTraversal(){
        List<T> result = new ArrayList<>();
        postOrderHelper(this.root, result);
        return result;
    }

    private void postOrderHelper(Node<T> node, List<T> result){
        if(node != null){
            preOrderHelper(node.getLeft(), result);
            preOrderHelper(node.getRight(), result);
            result.add(node.getData());
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        System.out.println("Inserting elements...");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.insert(30); // Duplicate ignored
        System.out.println("\nIn-Order Traversal (Sorted):");
        System.out.println(bst.inOrderTraversal()); // Output: [20, 30, 40, 50, 60, 70, 80]
        System.out.println("\nSearching for elements:");
        System.out.println("Search 40: " + bst.search(40)); // Output: true
        System.out.println("Search 90: " + bst.search(90)); // Output: false
        System.out.println("Search 20: " + bst.search(20)); // Output: true
        BinarySearchTree<String> stringBst = new BinarySearchTree<>();
        stringBst.insert("Banana");
        stringBst.insert("Apple");
        stringBst.insert("Orange");
        stringBst.insert("Mango");
        System.out.println("\nString BST In-Order:");
        System.out.println(stringBst.inOrderTraversal()); // Output: [Apple, Banana, Mango, Orange]
    }
}