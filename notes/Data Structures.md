
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

## Array Lists
---
An Array is an **homogenous** data structure: all elements are of the same type (int, string etc), and are also stored in adjacent memory locations. Below is an example of an **array**, where the number inside each cell is the index of that cell, using 0-based indexing (i.e., the first element is at index 0, the second element is at index 1, etc.).
```
arr = [0, 1, 2, 3, 4, 5, 6, 7]
```
Because each cell has the same type (and thus the same size), and because the cells are adjacent in memory, it is possible to quickly calculate the address of any array cell, given the address of the first cell.

Say we allocate memory for an **array** of _n_ elements (the total number of cells of the array must be defined beforehand), where the elements of the **array** are of a type that has a size of `_b_ bytes` (e.g. a C++ int has a size of `4 bytes`), and the resulting **array** is allocated starting at memory address `_x_`. Using 0-based indexing, the element at index `i = 0` is at memory address `_x_`, the element at index `i = 1` is at memory address `x + b`, and the element at index _i_ is at memory address `x + bi`.

```
arr = [0, 10, 20, 30, 40, 50, 60]
	// ↑   ↑   ↑
//    |x| |x+b| |x+2b| |x+3b| .....

```
Because of this phenomenon of being able to find the memory address of any _i_-th element in constant time (and thus being able to access any _i_-th element in constant time), we say that **arrays** have **random access**. In other words, we can access any specific element we want very quickly: in `O(1)` time.

 For an `ArrayList` , we'll assume that there are no empty cells between elements in the array (even though an array in general has no such restriction). As a result, we will assume that a user can only add elements to indices between 0 and _n_ (inclusive), where _n_ is the number of total elements that exist in the list prior to the new insertion.

We programmers generally don't know how many elements we want to insert into `ArrayList` beforehand, hence many languages implement this data structure as "dynamic", where we allocate some "large" amount of memory initially, insert elements into this initial array, and create a larger array, copy all elements from the old array into the new array, and then replace any references to the old array with references to the new array. In C++, the "dynamic" array is the vector data structure.

Example of adding the letters a-f to an ﻿**Array List** backed by an array initialized with 5 cells:
```cpp
Insert a: [a] [] [] [] []
Insert b: [a] [b] [] [] []
Insert c: [a] [b] [c] [] []
Insert d: [a] [b] [c] [d] []
Insert e: [a] [b] [c] [d] [e]
		   ↓   ↓   ↓   ↓   ↓
Insert f: [a] [b] [c] [d] [e] [] [] [] [] [] 
//create a new array 2x long, and copy old elements
 [a] [b] [c] [d] [e] [f] [] [] [] [] 
// then insert f
```

**STOP and Think:** Array structures (e.g. the array, or the Java ArrayList, or the C++ array and vector, etc.) require that all elements be the same size. However, array structures can contain strings, which can be different lengths (and thus different sizes in memory). How is this possible?

**Answer:** Every string is stored as a pointer to the location of the memory where the string is actually stored. Therefore each element of the string has the same amount of memory, and can be accessed directly (random access). For example:

```cpp
#include <stdio.h>

int main(){

	char *arr[] = {"Geek", "Geeks", "Geekfor"};

	 for(int i=0; i<3; i++){
		 printf("%s\n", arr[i]);
	 }
	 return 0;
}
```
### Inserting at the end of Array List
---
From the previous example, it is clear that inserting at the end of `Array List`, for which the backing array is not full is constant time (`O(1)`) , but how about inserting at the front? 

Unfortunately because of the rigid structure of an array (vital for random access), we need to move a potentially large amount of elements out of the way to make room for the element i want to insert:

```
[a] [b] [c] → [ ] [ ]
[a] [b] → [ ] [c] [ ]
[a] → [ ] [b] [c] [ ]
[ ] [a] [b] [c] [ ]
[d] [a] [b] [c] [ ]
```
Hence the worst time-complexity for insertion is O(N). 

```cpp
## Psuedocode of insertion into an Array List:

insert(element, index): //inserts element into array and returns boolean
//perform safety checks before insertion
	if index <0 or index > n:
		return False

	if n == array.length: //if array is full
		newArray = empty array of length 2*array.length
		for i from 0 to n-1: //copy all elements of old arr to new arr
			newArray[i] = array[i]
		array = newArray //replaces old arr with new
		
	//perform the insertion algo
	if index == n: //insert at the end
		array[index] = element
	else:
		for i from n-1 to index: //make space for new element (if not inserting at the end)
			array[i+1] = array[i]
		array[index] = element
	n = n+1 //increment number of elements
	return True
```
```cpp
find(element): //returns True if element exists in array, o.w returns False
	for i from 0 to n-1:
		if array[i] == element:
			return True
	return False
```
# Binary Search 
---
Because the worst-case time complexity to find an element in an `Array List` is `O(n)`, we have to individually check each of the `n` elements. 

However what if our `Array List` is sorted? Could we exploit that using the features of random access to speed things up? We will introduce an algorithm called **Binary Search**, to exploit random access for a worse-case time complexity of `O(log n)` in a sorted Array List.

The basic idea as follows: Since we have random access, compare the element we are searching for against the middle element of the array. If our element is less than the middle element, then it must exist on the left half, so we continue to search on the left half. Otherwise, it would exist on the right half. If our element is equal to the middle, we successfully found our element.

```cpp
## Psuedocode for Binary Serach

BinarySearch(array, element): //perform binary search to find element:
	L=0 and R=n-1 //initialize "left" and "right"

	loop infinitely:
		if L>R: //left index larger than right, so we failed:
			return False
		M = the floor of (L+R)/2 //compute middle index

		if element == array[M]: //if element equal to middle element
			return True
		if element > array[M]: // if element is larger than middle element, "recurse" right
			L = M + 1
		if element < array[M]: // if element is smaller than middle element, "recurse" left
			R = M - 1
```

Just like with insertion, removal at the end of our list of elements is very fast: simply remove the element at the n-th index of our backing array, which is a constant time operation:
```cpp
[a] [b] [c] (remove [d])
[a] [b] [c]
```
However also just like insertion, removal at the beginning of our list is very slow: we remove the element at index 0 of our backing array, which is a constant-time operation, but then we need to move all of the elements left one slow, which is an `O(N)` operation.
```cpp
[a] [remove [b]] [c] [d] [e]
[a] [ ] ← [c] [d] [e]
[a] [c] [] ← [d] [e]
[a] [c] [d] [] ← [e]
[a] [c] [d] [e] []

```
---
**STOP and Think:** When we remove from the very beginning of the backing array of an Array List, even before we move the remaining elements to the left, the remaining elements are all still contiguous, so our restriction is satisfied. Can we do something clever with our implementation to avoid having to perform this move operation when we remove from the very beginning of our list?

1. Incrementing an internal pointer or index to skip over the first element. (However physical memory still contains it)
2. Circular Array (use modular arithmetic to wrap around it):
	*  index = (start + i) % capacity

```cpp
[ _, _, _, _, _ ]   ← actual array
  0  1  2  3  4     ← indices

#Step 1: start empty
start = 0
end = 0
count = 0

#Step 2: add items:
Insert at end = 0: A → end = 1
Insert at end = 1: B → end = 2
Insert at end = 2: C → end = 3

[ A, B, C, _, _ ]
  0  1  2  3  4
  ↑
start
       ↑
      end

#Step 3: remove one item
Remove at start = 0 → A
Move start to 1

[ A, B, C, _, _ ]  ← A still there but "logically removed"
     ↑
   start
       ↑
      end

#Step 4: add more items, wrapping around
Add `D` at end = 3  
Add `E` at end = 4  
Now `end = 0` (wrap around!)

[ A, B, C, D, E ]
           ↑
         end (wrapped around to 0)
     ↑
   start
```

```cpp 
# Removal psuedocode

remove(index): //removes element at position "index" in the array:
	// check for valid index
	if index<0 or index >= n: //invalid index
		return False

	//perform removal algorithm
	clear array[index]
	if index < n-1: //if we didn't remove from the very end of the list
		for i from index to n-1: //shift all elements left by 1
			array[i] = array[i+1];
		clear array[n-1] // not necessary since it will be overwritten by next insert
	n = n-1
	return True
```

In summation, **Array Lists** are great if we know exactly how many elements we want and if the data is already sorted, as finding an element in a sorted **Array List** is O(log _n_) in the worst case and accessing a specific element is O(1). However, inserting into an **Array List** is O(_n_) in the worst case and finding an element in a non-sorted **Array List** is O(_n_). Also, if we don't know exactly how many elements we want to store, we would need to allocate extra space in order to avoid having to rebuild the array over and over again, which would waste some space.

# Linked List 
---
Recall from the previous section that **Array Lists** are excellent if we know exactly how many elements we want to store, but if we don't, they can be problematic either in terms of time complexity (rebuilding the backing array as we need to grow it) or in terms of space complexity (allocating more space than we need just in case).

The **Linked List** is a dynamically-allocated data structures, meaning it grows dynamically in memory on its own very time-efficiently (as opposed to an **Array List**, for which we needed to explicitly reallocate memory as the backing array fills, which is a very time-costly operation). 

### Nodes, Singly-Linked List and Doubly-Linked List
---
A **Linked List** is a data structure composed of **nodes**: containers that each hold a single element, and each nodes are "linked" to one another via pointers.
* Typically, we maintain one global **_head_ pointer** (a pointer to the first node) and one global **_tail_ pointer** (a pointer to the last node). 
* We only have direct access to the head and tail pointers, other nodes can only be accessed by traversing pointers starting either at the head or tail node.

In a **Singly-Linked List**, each node only has a pointer pointing to the node directly after it (for internal nodes) or a null pointer (for the tail node). As a result, we can only traverse a Singly-Linked List in one direction.
```cpp
[HEAD] -> [1] -> [2] -> [3] <- [TAIL]
```
In a **Doubly-Linked List**, each node has two pointers: one pointing to the node directly after it and one pointing to the node directly before it (of course, the head and the tail nodes only have a single pointer each). As a result, we can traverse a Doubly-Linked List in both directions.
```cpp
[HEAD] -> [1] -> [2] -> [3] <- [TAIL]
	   <-     <-     <-
```
Iterating through all `n` elements in worst case, as we don't have access to internal nodes:
```cpp
[HEAD] -> #0 [1]  -> [2] -> [3] <- [TAIL]
	   <-     <-     <-

[HEAD] -> [1] #1 -> [2] -> [3] <- [TAIL]
	   <-     <-     <-

[HEAD] -> [1] -> [2] #2 -> [3] <- [TAIL]
	   <-     <-     <-
```

**STOP and Think:** Notice that when we look for an element in the middle of the **Linked List**, we start at the _head_ and step forward. This works fine if the index is towards the beginning of the list, but what about when the index of interest is large (i.e., closer to _n_)? Can we speed things up?

**Answer:** Use a **doubly-linked list** instead so we could start from the tail. We can first check if the index is closer to the head (index 0) or tail (index n-1):
* distance between `index` and `head` is `index -0`, which is just `index`
* distance between `index` and `tail` is `(n-1) - index`
* calculate both distances and compare which one is smaller (the one `index` is closer to)

### Find operation in Linked List
---
As mentioned previously, to find an element at a given index in a **Linked List**, we simply start at the _head_ node and follow forward pointers until we have reached the desired node.
If we are finding the i-th element of a Linked List, it would be a O(i) operation, whereas in an array, it is a O(1) operation due to random access. This is one of the main drawbacks of a Linked List: even if we know exactly what index we want to access, because the data is not stored contiguously in memory, we need to slowly iterate through the elements one-by-one until we reach the node we want.

```cpp
## Psuedocode of find operation for Linked List
find(element): //returns True if element exist, False otherwise
	current = head //start at the head node
	while current is not NULL:
		if current.data ==element:
			return True
		current = current.next // follow the next pointer
	return False //iterated all but does not find the element
```
Finding what element is at a given index of the Linked List:
```cpp
find(index): //returns element at position "index" of Linked List (or NULL)
	if index < 0 or index >= n: //check invalid indicies
		return NULL // invalid node

	curr = head //looking for an element in the middle of the linked list
	repeat index times: // move forward index times
		curr = curr.next
	return curr 
```
---
### Insert method for Linked List

The insert algorithm is almost identical to the find algorithm: 
* First execute the `find` algorithm to find the insertion site.
* Rearrange the pointers to fit the new node in its rightful spot
```cpp

		  #0	   #1
[HEAD] -> [1]  -> [2] -> [3] <- [TAIL]
	   <-     <-     <-
				↖ [5] ↗

		  #0	   #1
[HEAD] -> [1]  -> [2] -> [3] <- [TAIL]
	   <-     <-     <-
				↘↖ [5] ↗↙

		  #0	 #1     #2
[HEAD] -> [1] -> [5] -> [2] -> [3] <- [TAIL]
	   <-     <-     <-     <-     
```
Notice how we do a `find` operation to index directly before index i, then point the new node's "next" pointer to the node that was previously at index i (and the new node's "prev" pointer to the node that is directly before index i in the case of a Doubly-Linked List), then point the "next" pointer of the node before the insertion site to point to the new node.

```cpp
insert(newnode, index) //inserts newnode at position "index" of Linked List
	if index == 0: //special case for insertion at the beginning of list
		newnode.next = head
		head.prev = newnode
		head = newnode
	else if index == size: //special case for insertion at end of list
		newnode.prev = tail
		tail.next = newnode
		tail = newnode
	else: //general case
		curr = head
		repeat index -1 times: //move curr to directly before insertion site
			curr = curr.next
		newnode.next = curr.next //update pointers
		newnode.prev = curr
		curr.next = newnode
		newnode.next.prev = newnode
	size = size + 1 // increment size
```
### Remove method for LinkedList

The "remove" algorithm is also almost identical to the "find" algorithm: you first execute the "find" algorithm just like before, but once you find the insertion site, you rearrange pointers to remove the node of interest.

```cpp
remove(index): //removes the element at position "index" of Linked List
	if index == 0:
		head = head.next
		head.prev = NULL
	else if index == n-1:
		tail = tail.prev
		tail.next = NULL
	else:
		curr = head
		repeat index-1 times:
			curr = curr.bext
		curr.bext = curr.next.next
		currn.next.prev = curr
	n = n-1
```
Graph for visualization:
```cpp
		  #0	   #1
[HEAD] -> [0]  -> [1] -> [2] -> [3] <- [TAIL]
	   <-      <-      <-      <-

				↗  →  ↘
		↗  #0	   #1        ↘
[HEAD]  [0]   [1]  [2] -> [3] <- [TAIL]
	   <-        <-            
		↖		             ↙
				↖  ←  ↙      

```

**STOP and Think:** Notice that the node we removed still exists in this diagram. Not considering memory management (i.e., only thinking in terms of data structure functionality), is this an issue?

In terms of data structure functionality, this won't be an issue since we cannot access the internal nodes of `2`, as we can only traverse through `head` or `prev` by incrementing through the pointers forwards or backwards.

In summation, **Linked Lists** are great (constant-time) when we add or remove elements from the beginning or the end of the list, but finding elements in a **Linked List** (even one in which elements are sorted) cannot be optimized like it can in an **Array List**, so we are stuck with O(_n_) "find" operations. Also, recall that, with **Array Lists**, we needed to allocate extra space to avoid having to recreate the backing array repeatedly, but because of the dynamic allocation of memory for new nodes in a **Linked List**, we have no wasted memory here.

---
## Skip List

So far we've discussed `Array List` and `Linked List`, where we could have different time complexity in operations.
**Array List:**
* Worst-case of $O(log n)$  for `find` operations with an **Array List** if we kept it sorted and used _binary search_
* Insert and remove operations would always be $O(n)$
* Also have to allocate extra space for rebuilding back array
**Linked List:**
* Worst-case of $O(n)$ for `find` operations, as we cannot use the random access property.
* Worst-case of $O(1)$ for `insert` and `remove` to the front and back of the structure.
* No need to allocate extra space, as each node is created on-the-fly.

Now here comes the question, is there any way to reap benefits of both data structures? We introduce **Skip List**, a data structure that expands on the **Linked List** and uses extra forward pointers with some random number generation to simulate the binary search algorithm achievable in **Array Lists**.

It is effectively the same as a **Linked List**, except every node in the list has multiple layers, where each _layer_ of a node is a forward pointer. For our purposes, we will denote the number of layers a node reaches as its _height_. The very bottom layer is exactly a regular **Linked List**, and each higher layer acts as an "express lane" for the layers below. Also, the elements in a **Skip List** must be **sorted**. The sorted property of the elements in a **Skip List** will let us perform a find algorithm that is functionally similar to binary search. Example:

```cpp
┌────────────────-───────────────-─────┐
│ [] ──▶ [] ──▶    ──▶    ──▶    ──▶ []|
│ [] ──▶ [] ──▶    ──▶ [] ──▶    ──▶ []|
│ [] ──▶ [] ──▶ [] ──▶ [] ──▶ [] ──▶ []|
Head	 1      2      3      4      5 
└──────────────────────────────────────┘
```
Just like with a **Linked List**, we have a _head_. However, because **Skip Lists** have _multiple_ layers, the _head_ also has multiple layers. Specifically, for each layer _i_, the _i_-th pointer in _head_ points to the first node that has a height of at least _i_. In the example above, the first node (1) happens to reach every layer, so each pointer in _head_ points to 1, but this does not have to be the case.
```cpp
┌────────────────-───────────────-─────┐
│ [] ──▶    ──▶    ──▶    ──▶    ──▶ []|
│ [] ──▶    ──▶    ──▶ [] ──▶    ──▶ []|
│ [] ──▶ [] ──▶ [] ──▶ [] ──▶ [] ──▶ []|
Head	 1      2      3      4      5 
└──────────────────────────────────────┘
```
Now, the three pointers in _head_ point to three different nodes. For our purposes, we will number the bottom layer as layer 0, the next layer as layer 1, etc. In this example, _head_'s pointer in layer 0 points to node 1, its pointer in layer 1 points to node 3, and its pointer in layer 2 points to node 5.

In this example, nodes 1, 2, and 4 have heights of 0, node 3 has a height of 1, and node 5 has a height of 2.

---
### Finding an element in Skip List

To **find** an element _e_ in a **Skip List**, we start our list traversal at _head_, and we start at the highest layer. When we are on a given layer _i_, we traverse the forward pointers on layer _i_ until _just before_ we reach a node that is larger than _e_ **or** until there are no more forward pointers on level _i_. Once we reach this point, we move down one layer and continue the search. If _e_ exists in our **Skip List**, we will eventually find it (because the bottom layer is a regular **Linked List**, so we would step through the elements one-by-one until we find _e_). Otherwise, if we reach a point where we want to move down one layer but we're already on layer 0, _e_ does not exist in the **Skip List**.

Below is an example in which we attempt to find the element 7. **#1** arrows denote pointers that we _could_ have traversed, but that would have taken us to a node that was too big (so we instead chose to go down 1 level), and **#2** arrows denote pointers we actually took. We define both pointers that we _could_ have traversed and pointers that we _actually took_ as "followed."

```cpp
┌────────────────-───────────────-─────┐
│ []#2 ──▶ [] #1 ──▶    ──▶  ──▶ ──▶ []|
│ [] ──▶ []#2 ──▶  ──▶ []#1──▶    ──▶ []|
│ [] ──▶ [] ──▶ [] ──▶ []#2──▶ [] ──▶ []|
Head	 1      3      5      7      9 
└──────────────────────────────────────┘
```
Below is formal pseudocode to describe the `find` algorithm. In the pseudocode, `head` is _head_ and `head.height` is the highest layer in _head_ (which is the highest layer in the **Skip List**, by definition). Also, for a given node current, current.next is a list of forward-pointers, where current.next[i] is the forward-pointer at layer _i_.

```cpp
find(element): //returns True if element exists in skip list; false if not
	current = head;
	layer = head.height;
	while layer>= 0: // can't go lower than layer 0
		if current.key == element: //true if found
			return true;
		if current.next[layer] is NULL or current.next[layer].key > element:
			layer = layer - 1; //drop 1 layer if not found
		else:
			current = current.next[layer]
	return false // failed on every layer, does not exist;
```

To **remove** an element from a **Skip List**, we simply perform the "find" algorithm to find the node we wish to remove, which we will call _node_. Then, for each layer _i_ that _node_ reaches, whatever is _pointing to node_ on layer _i_ should instead point to whatever _node_ _points to_ on layer _i_.

```cpp
remove(element):
	current = head; 
	layer = head.height;
	while layer>= 0:
		if current.key == element:
			break; // if we found element, break so we can fix pointers
		if current.next[layer] is NULL or current.next[layer].key > element:
			layer = layer - 1;
		else:
			current = current.next[layer];
	for i from 0 to layer: // if we found element, fix pointers
		current.prev[i].next[i] = current.next[i];
		if current.next[i] is not NULL:
            current.next[i].prev[i] = current.prev[i]
```