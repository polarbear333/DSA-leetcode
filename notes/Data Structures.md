
## Interfaces
Also known as **Abstract Data Types**, typically defines the set of operations supported by a data structure and the semantics, or meaning, of those operations. It does not tell us how the data structure implements these operators, only provides a list of supported operations along with what types of arguments each operation accepts and the value returned by each operation.


### Queue interfaces 
Basic examples:`Queue`, `Stack`, `Deque`

The Queue interfaces represents a collection of elements to which we can add elements and remove the next element. They mostly support these operators:

* add(x): add the value x to the Queue
* remove(): remove the next (previously added) value, y, from the Queue and return y

Notice that the remove() operation takes no argument. The Queue’s queueing discipline decides which element should be removed. Examples of queueing discipline include `FIFO`, `LIFO`, `priority` etc.

**FIFO** queue:
A FIFO (first-in-first-out) Queue removes items in the same order they were added, much in the same way a queue (or line-up) works when checking out at a cash register in a grocery store.
The add(x) and remove() operations on a FIFO queue is often called enqueue(x) and dequeue().

`LIFO` queue:
In LIFO queue, the most recently added element is the next one removed. This is best visualized in terms of a stack of plates , where plates are placed on the top of the stack and also removed from the top. It is also called `Stack`, which computer RAM also uses the same principle. The add(x) and remove() operations on a FIFO queue is called push(x) and pop() instead.

`Priority` queue:
A priority queue always removes the smallest element from the Queue, breaking ties arbitrarily.
This is similar to the way in which patients are triaged in a hospital emergency room, and treats the patient with the most life-threatening condition. . The remove() operation on a priority Queue is usually called deleteMin() in other texts.

A Deque is a generalization of both the FIFO Queue and LIFO Queue (Stack). A Deque represents a sequence of elements, with a front and a back. Elements can be added at the front of the sequence or the back of the sequence. Names of the Deque operations include:

`addFirst(x)`, `removeFirst()`, `addLast(x)`, `removeLast()`. 

It is worth noting that a Stack can be implemented using only `addFirst(x)` and `removeFirst()` while a FIFO Queue can be implemented using `addLast(x)` and `removeFirst()`.

### List Interfaces
The `Queue`, `Stack` and `Deque` interfaces are mostly implemented by `List` interfaces, as both they can all represent a linear sequence indexed by the input (i.e 0, 1, 2, ... , n-1). The List interface includes the following operations:

1. size(): return $n$, the length of the list 
2. get(i): return the value $x_i$
3. set(i,x): set the value of $x_i$ equal to $x$ 
4. add(i,x): add $x$ at position $i$, displacing $x_i ,...,x_n−1$; Set $x_j+1 = x_j$ , for all $j \in {n − 1,...,i}$, increment $n$, and set $x_i = x$ 
5. remove(i) remove the value $x_i$ , displacing $x_i+1 ,...,x_n−1$; Set $x_j = x_j+1$, for all $j \in {i,...,n − 2}$ and decrement $n$

Notice that these operations are easily sufficient to implement the Deque interface: 
* addFirst(x) ⇒ add(0,x) 
* removeFirst() ⇒ remove(0) 
* addLast(x) ⇒ add(size(),x) 
* removeLast() ⇒ remove(size() − 1)

Hence `List` is a very efficient linear structure that can implement these interfaces. For example, the `ArrayDeque` class is an implementation of the List interface that implements all the Deque operations in constant time per operation.

### USet Interface: Unordered Sets

The `USet` interface represents an unordered set of unique elements, which mimics a mathematical set. A `USet` contains n distinct elements where no element appears more than once, and the elements are in no specific order. A USet supports the following operations:

1. size(): return the number, n, of elements in the set 

2. add(x): add the element x to the set if not already present; Add x to the set provided that there is no element y in the set such that x equals y. Return true if x was added to the set and false otherwise. 

3. remove(x): remove x from the set; Find an element y in the set such that x equals y and remove y. Return y, or null if no such element exists. 

4. find(x): find x in the set if it exists; Find an element y in the set such that y equals x. Return y, or null if no such element exists.

These definitions are used to distinguish `x` from `y` , comparing the element we want to find or remove, from the other element we want to find/remove. This is because `x` and `y` might actually be different objects but are treated as equal (equal value / strings etc). Hence `USet` is a collection of objects and unique keys which are unordered. 

### Map Interface: Dictionaries
The `Map` interface represents a dictionary, which stores key-value pairs. A `Map` contains n distinct keys, each mapping to a corresponding value. No key appears more than once; the keys are unique. A `Map` supports the following operations:

1. size(): return the number, n, of key-value pairs in the map.
    
2. put(k, v): add the key-value pair (k, v) to the map if the key k is not already present; Add (k, v) to the map provided that there is no key k' in the map such that k equals k'. Return the value v if the pair was added to the map and null otherwise.
    
3. remove(k): remove the key-value pair associated with the key k from the map; Find a pair (k', v) in the map such that k equals k' and remove it. Return the value v, or null if no such pair exists.
    
4. get(k): find the value associated with the key k in the map; Find a pair (k', v) in the map such that k equals k'. Return the value v, or null if no such pair exists.
    
In essence, a `Map` is a collection of key-value pairs where the keys are unique. The `get()` and `remove()` methods retrieve or remove values based on key equality, not necessarily object identity.

### The SSet Interface: Sorted Sets
The SSet interface represents a sorted set of elements. An SSet stores elements from some total order, so that any two elements x and y can be compared. In code examples, this will be done with a method called compare(x,y) in which:

$$
\text{compare}(x, y) = 
\begin{cases}
< 0 & \text{if } x < y \\
> 0 & \text{if } x > y \\
= 0 & \text{if } x = y
\end{cases}
$$
An SSet supports the size(), add(x), and remove(x) methods with exactly the same semantics as in the USet interface. The difference between a USet and an SSet is in the `find(x)` method: 

4. `find(x)`: locate x in the sorted set; Find the smallest element y in the set such that $y \geq x$. Return $y$ or $null$ if no such element exists. 

This version of the `find(x)` operation is sometimes referred to as a successor search. It differs in a fundamental way from USet `find(x)` since it returns a meaningful result even when there is no element equal to x in the set.

The extra functionality provided by an SSet usually comes with a price that includes both a larger running time and a higher implementation complexity. For example, most of the SSet implementations discussed in this book all have find(x) operations with running times that are logarithmic in the size of the set. 

On the other hand, the implementation of a USet as a `ChainedHashTable`has a find(x) operation that runs in constant expected time. 