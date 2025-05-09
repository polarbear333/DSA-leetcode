
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

To organize the distribution of heights in a `SkipList`, it involves the `insert` operation. We do so by first using the `find` operation to find where to insert our new element. Then we determine the new node's height. By definition, the new node must have a height of at least 0 (i.e., the 0-th layer). So how many layers higher should we build the new node? To answer this question, we will play a simple coin-flip game: starting at our base height of 0, we flip a coin, where the coin's probability of heads is _p_.  If we flip heads, we increase our height by 1. If we flip tails, we stop playing the game and keep our current height.

```cpp
insert(element): // inserts element if it doesn't exist in the list 
current = head layer = head.height 
toFix = empty list of nodes of length head.height 

// one slot for each layer 
while layer >= 0: // can't go lower than layer 0 
	if current.next[layer] is NULL or current.next[layer].key > element: 
		toFix[layer] = current // we might have to fix a pointer here 
		layer = layer - 1 // drop one layer if we can't go further 
	else: 
		current = current.next[layer] 
	if current.key == element: // if we found element, return (no duplicates)    
		return 
	
	// if we reached here, we can perform the insertion 
	newNode = new node containing element, starting with height = 0 
	while newNode.height < head.height: // can't go higher than head node's height 
		result = result of coin-flip with probability p of heads 
		if result is heads: 
			newNode.height = newNode.height + 1 
		else: // we have flipped a tails so we should keep the current height 
			break 
	
	// fix pointers 
	for i from 0 to newNode.height: // fix newNode's outgoing pointers 
		newNode.next[i] = toFix[i].next[i] 
		newNode.prev[i] = toFix[i] 
		
		// fix newNode's incoming pointers 
		if newNode.next[i] is not None: 
			newNode.next[i].prev[i] = newNode 
		newNode.prev[i].next[i] = newNode
```
---
### Probability of the coin-flip

It turns out that we don't have to do multiple flips of the coin to determine the height of a new node, instead it can be done in a single trial. A coin-flip is a **Bernoulli distribution**, in which we only have two possible outcomes: _success_ and _failure_. We say that _p_ is the probability of _success_, and we say that _q_, which equals 1–_p_, is the probability of _failure_.

What we described above, where we perform multiple coin-flips until we get our first tails, is synonymous to saying "Sample from a Bernoulli distribution until you see the first failure." It turns out that this statement is actually a probability distribution in itself: the **Geometric distribution**.

What we described above, where we perform multiple coin-flips until we get our first tails, is synonymous to saying "Sample from a Bernoulli distribution until you see the first failure." It turns out that this statement is actually a probability distribution in itself: the **Geometric distribution**:
$$Pr(X=k)=(1−p)^kp$$Where we need $k$ _failures_ (each with probability $1-p$), and then we need one _success_ (with probability $p$).

But wait! We want the number of flips until the first _failure_! Instead we trivially swap the success and failure: $$Pr(X=k)=p^k(1−p)$$
## Skip List: Average-Case Time Complexity

For a probabilistic data structure like `SkipList` that maintains a sorted sequence of elements in multiple levels of linked lists. Each element is promoted to a higher level with a fixed probability $p \in (0,1)$. The higher the level, the fewer nodes it has.

The performance of operations like `find`, `insert`, and `delete` depends on the number of comparisons we make during traversal, which in turn depends on the height distribution of the nodes.

## Expected Search Cost in a Skip List

We aim to prove that the **expected number of comparisons** required for a `find` operation in a Skip List is:
$$
\mathbb{E}[\text{comparisons}] = \frac{1}{p} \log_{\frac{1}{p}} n + \frac{1}{1 - p}
$$
and hence the average-case time complexity is $\mathcal{O}(\log n)$.

## Assumptions

- Each node appears at level $i$ with probability $p^{i-1}(1-p)$.
- The maximum level $L$ is $\mathcal{O}(\log_{1/p} n)$.
- The number of elements in the list is $n$.

## Structure of the Skip List

Level 0 contains all $n$ elements. Level 1 contains approximately $pn$ elements. Level 2 contains approximately $p^2 n$ elements, and so on. Generally, the number of elements at level $i$ is expected to be $p^i n$.

We only move *horizontally* until we can't go further without overshooting the target, then drop down vertically.

## Expected Number of Comparisons

Let’s compute the expected number of steps taken during the search for a key $k$:

1. At each level, we do a number of horizontal steps.
2. The expected number of horizontal steps at any level is $\frac{1}{p}$ (this arises from the geometric distribution).
3. The number of levels is approximately $\log_{1/p} n$.

Thus, the expected number of comparisons is:
$$
\mathbb{E}[\text{comparisons}] = \underbrace{\frac{1}{p}}_{\text{per level}} \cdot \underbrace{\log_{1/p} n}_{\text{levels}} = \frac{1}{p} \log_{\frac{1}{p}} n
$$

## Correction Factor

Additionally, we must account for one final vertical traversal at the end which adds a constant factor. Therefore, we add a constant term for the final level descent:
$$
\mathbb{E}[\text{comparisons}] = \frac{1}{p} \log_{\frac{1}{p}} n + \frac{1}{1 - p}
$$

## Conclusion

Hence, with high probability, the expected cost of a `find` operation in a well-balanced Skip List is:
$$
\mathcal{O}\left(\frac{1}{p} \log_{1/p} n\right) = \mathcal{O}(\log n)
$$

This makes Skip Lists an efficient and elegant alternative to balanced binary search trees, with simpler insertion and deletion logic and good expected performance.

---
## Circular Arrays

Another implementation of having both perks of `ArrayLists` and `LinkedLists` is the `Circular Arrays`. At the lowest level, it is really just a regular `Array List` with a clever implementation: 
- Recall that the `Linked List` has a head pointer and a tail pointer:
- When we want to insert an element to the beginning or end of a linked list, all we do is update a few pointers (constant-time operation)
- Similar to head and tail pointers, we take our Array List and use head and tail indices, (first element would be element of head index, last element is the element of tail index)
- As we add to the end of the Circular Array, we can simply increment tail, vice versa for head.
- As we increment tail, if we go out of bounds (tail becomes equal to size of array), we can simply wrap around index 0. Likewise, as we're decrementing head, if we ever go out of bounds (i.e head becomes -1), we can simply wrap around to the last index of the array (size -1).
- Similar to `Array List`, elements of `Circular Array` will be contiguous in the backing array.

```cpp
[][a][b][c][d][e][][]
1  2  3  4  5  6 7 8
```
## Insert front and back for Circular Arrays

```cpp
insertFront(element): // inserts element at the front of the Circular Array // check array size 
if n == array.length: 
	newArray = empty array of length 2*array.length 
	for i from 0 to n-1: // copy all elements from array to newArray 
		newArray[i] = array[(head+i)%array.length] 
	array = newArray // replace array with newArray 
	head = 0 // fix head and tail indices 
	tail = n-1 
	
	// insertion algorithm 
	head = head - 1 // decrement head index 
	if head == -1: // if we went out of bounds, wrap around 
		head = array.length-1 
	array[head] = element // perform insertion 
	n = n + 1 // increment size
```
```cpp
insertBack(element): // inserts element at the back of the Circular Array \
// check array size 
	if n == array.length: 
		newArray = empty array of length 2*array.length 
		for i from 0 to n-1: // copy all elements from array to newArray 
			newArray[i] = array[(head+i)%array.length]
		 array = newArray // replace array with newArray 
		 head = 0 // fix head and tail indices 
		 tail = n-1
		 
		  // insertion algorithm 
		  tail = tail + 1 // increment tail index 
		  if tail == array.length: // if we went out of bounds, wrap around
				 tail = 0 
		  array[tail] = element // perform insertion 
		  n = n + 1 // increment size
```
**STOP and Think:** How could we generalize this idea to allow for insertion into the middle of the **Circular Array**?

**ANS:** Generalize by `head + index` and `tail - index` and wrap around, `index` is the 'middle' we want to insert to, and depending on whether we want to insert from the back or the front.

### Removal at the front or back

Removal at the front or back of a **Circular Array** is fairly trivial. To remove from the front of a **Circular Array**, simply "erase" the element at the _head_ index (e.g. by setting it to a null value), and then increment the _head_ index (wrapping around, if need be). To remove from the back of a **Circular Array**, simply "erase" the element at the _tail_ index, and then decrement the _tail_ index (wrapping around, if need be).

```cpp
removeFront(): //removes element at the front of the Circular Array
	erase array[head]
	head = head + 1
	if head == array.length:
		head = 0
	n = n - 1

removeBack():
	erase array[tail]
	tail = tail - 1
	if tail == -1:
		tail = array.length - 1
	n = n - 1
```
**STOP and Think:** Is it necessary for us to perform the "erase" steps in these algorithms? What would happen if we didn't?

**ANS:** If we don't perform the "erase" steps, they will still exist in the arrays, and will be just replaced by the new ones in future insertions, so its all fine

---
### Accessing element at index(i) for Circular Arrays

So far, we've only looked at adding or removing elements from the front or back of a **Circular Array**. With a **Circular Array**, the backing structure is an **Array List**, meaning we have random access to any element given that we know which index of the backing array we need to query. If we want to access the element of a **Circular Array** at index _i_, where _i_ is with respect to the _head_ index (i.e., _head_ is _i_ = 0, irrespective of what index it is in the backing array), we can simply access the element at index **(**_**head**_ **+** _**i**_**) %** _**array.length**_ in the backing array.  By modding by the backing array's length, we ensure that the index we get from the operation (_head_ + _i_) wraps around to a valid index if it exceeds the boundaries of the array. For example:
- We have a `Circular Array` with total of `8` elements, head to be $i=0$.
- Accessing the element at $i=2$ of our list is at index $(7+2) \% 8 = 9 \% 8 = 1$ of the backing array.
- 
**STOP and Think:** Under what condition(s) would we be safe to omit the mod operation in the formula above? In other words, under what condition(s) would the formula _head_ + _i_ be valid?

**ANS:** Since we wrap around to avoid going out of bounds of the backing array, I'm thinking there are 2 different conditions we won't need to wrap around:
1. When the head index = index 0 of backing array (this way, valid indices are all in bounds of backing array)

2. When the index (head +i) is less than the size of the backing array (bc valid indices are still within bounds here as well)




## Hash tables

So far we have implemented a few data structures that effectively search for the existence of items within the data structure, we've looked at `Array List`, `Linked List`, `Randomized Search Tree`, `Skip List` and`Binary Search Tree`, then analyzed their time complexities in order to describe their performance:
- In an unsorted `Array List` and `Linked List`, the worst-case time complexity to find an element is $O(N)$.
- In a Randomized Search tree and a well-structured `Skip List`, the average-case time complexity to find an element is $O(log(N))$.
- In a sorted `Array List` and a balanced `Binary Search Tree`, the worst- case time complexity to find an element is $O(log(N))$.

However, there are limitations that these structures impose (even `2-3 trees`.)
1. They require that items be comparable. How do u decide where a new item goes in a BST? You have to answer the question "are you smaller or bigger than the root"? For some objects, this question may make no sense.
2. They give a complexity of $O(log(N))$. Is this good? Absolutely, but we may do better. With an array, if we knew the specific index we wanted to access, we could theoretically access our element of interest in $O(1)$ time. Formally, if we were looking for a key $k$ in an array $a$ **and**﻿ if we had a way of knowing that key $k$ would be at index $i$, we could find $k$ with a single $O(1)$ array access operation: $a[i]$.

**Using Data as Indices:** Since arrays have amazing runtime for its basic operations, there might be a good way to convert data into indices and store them in an array. We will trying improving complexity from $O(log(N))$ to $O(1)$. Let's not worry about comparability here, we are going to only consider storing and searching for integers.

Here's an idea: let's create an `ArrayList` of type `boolean` and size 2 billion, everything else is default.
- The `add(int x)` method simply sets the `x` position in our `ArrayList` to true. This takes `O(1)` time.
- The `contains(int x)` method simply returns whether the `x` position in our `ArrayList` is `true` or `false`. This also takes $O(1)$ time.

```java
public class DataIndexedIntegerSet{
	private boolean[] present;

	public DataIndexedIntegerset(){
		present = new boolean[2000000000];
	}

	public void add(int x){
		present[i] = true;
	}

	public boolean contains(int x){
		return present[i];
	}
}
```
What are some potential issues with this approach? It turns out that this is extremely wasteful. If we assume that a `boolean` takes 1 byte to store, the above needs `2GB` of space per `new DataIndexedIntegerSet()`. Moreover, the user may only insert a handful of items. Also what if someone wants to insert a `String` or other data types?

**A second approach:** Our `DataIndexedIntegerSet` only allows integers, but now we want to insert `String "cat"` into it. We'll call our data structure that can insert strings `DataIntexedEnglishWordSet`. The idea is: let's give every string a number, maybe "cat" can be `1`, "dog" can be `2`, "turtle" can be `3`.

The way this would work is - if someone wanted to add a "cat" to our data structure, we would 'figure out' that the number for "cat" is 1, and then set `present[1]` to be true. If someone wanted to ask us if "cat" is in our data structure, we would 'figure out' that the "cat" is 1, and check if `present[1]` is true.

But then if someone tries to insert the word "potatocactus", we don't know what to do. We need to develop a general strategy so that given a string, we can figure out a number representation for it.

Here are the two main strategies we chose to use:

**Strategy 1: Use the first letter.** A simple idea is to just use the first character of any given string to convert it to its number representation. However, if someone tried to insert two words with the same first letter, we have a **collision**, which we deal with using the next strategy. 

**Strategy 2: Avoid Collisions:** There are $26$ unique characters in the English lowercase alphabet. We assign each one a number: $a = 1, b = 2, ..., z = 26$. Now we can write any unique lowercase string in **base 26**. (Note that **base 26** simply means that we will use **26** as the multiplier, much like we used 10 and 2 as examples above.) 
- For example, "cat" = $3 * 26^{2} + 1 * 26^{1} + 20 * 26^{0}$

This representation gives a unique integer to every English word containing lowercase letters, much like using base 10 gives a unique representation to every number. We are guaranteed to not have collisions.

But only using lowercase letters is to restrictive, what if we want to store strings like '2pac' or 'eGg!'? Turns out we can use a character format called `ASCII`, which has an integer per character, and the base we will use is `126`.

```java
public static int asciiToInt(String s){
	int intRep = 0;
	for(int i=0; i < s.length(); i++){
		intRep = intRep * 126;
		intRep = intRep + s.charAt(i);
	}
	return intRep;
}
```
What about adding support for Chinese? The largest possible representation is 40959, so we need to use that as the base. So... to store a 3-character Chinese word, we need an array of size larger than **39 trillion** (with a T)!. This is getting out of hand... , and Java's maximum int size is about $2^{32}$, with a base of $126$, we will run into overflow even for short strings. Overflow can result in **collisions**, causing incorrect answers due.

so let's explore what we can do to improve this, namely, using `hashCode`.

---
We face the problem that not every object in Java can be easily converted to a number, however the idea behind hashing is the **transformation of any object into a numeric representation**. They key is to have a **hashing function** [[Algorithms#Hashing]] transform our keys into different values, and convert that number into an index to then access the array.

We achieve this through our own implementation of a `hashCode()` function, with a return value of an `int` type. This `int` type is our hash value. The built-in String class in Java might have the following code block:

```java
public class String{
	private int hashCode(){
		//implementation here
	}
}
```

**Hash Code:** A hash code "projects a value from a set with many(or even an infinite number of) members to a value from a set with fixed number of (fewer) numbers." Here our target set is the set of Java integers, which is a size of $4294967296$.

The **Pigeonhole principle** tells us that if there are more than $4294967296$ possible items, multiple items will share the same hash code.
- There are more than $4294967296$ planets.
	- Each has `mass`, `xPos`, `yPos`,`xVel`, etc
- There are more than $4294967296$ strings.
	- "one", "two", ... "nineteen quadrillion", ...
Hence, collisions are inevitable.

**Memory Inefficiency in Hash Codes:**  
An issue mentioned earlier is memory inefficiency: for a small range of hash values, we can get away with an array that individuates each hash value. That is, every index in the array would represent a unique hash value. This works well if our indices are small and close to zero. But remember that Java’s 32-bit integer type can support numbers anywhere between -2,147,483,648 and 2,147,483,647. Now, most of the time, our data won’t use anywhere near that many values. But even if we only wanted to support special characters, our array would still need to be 1,112,064 elements long!

Instead, we'll slightly modify our indexing strategy. Let's say we only want to support an array of length 10 so as to avoid allocating excessive amounts of memory. How can we turn a number that is potentially millions or billions large into a value between 0 and 9, inclusive?

**Handling Large number of members with Modulo Operator:** 
Since the size of array (buckets) is usually smaller than the number of possible hash codes, we can use the modulo operation (`index = hash% capcacity`) to map a hash code to an index, which can result in multiple keys sharing the same index. Here we want to be able to convert any number to a value between 0 and 9, inclusive. Given our discussion on the modulo operator, we can see that any number mod 10 will return an integer between 0 and 9. This what we need to index an array of size 10. To locate the index for any key:
```java
Math.floorMod(key.hashCode(), array.length)
```
where `array` is the underlying array representing our hash table.

_Quick Note_: In Java, the `Math.floorMod` function will perform the modulus operation while correctly accounting for negative integers, whereas `%` does not.

**Properties of unique HashCode:**
1. **Deterministic:** The `hashCode()` function of two objects A and B who are equal to each other (`A.equals(B) == true`) have the same hashcode. This also means the hash function cannot rely on attributes of the object that are not reflected in the `.equals()` method.
2. **Consistent:** The `hashCode()` function returns the same integer every time it is called on the same instance of an object. This means the `hashCode()` function returns the same integer every time it is called on the same instance of an object. This means the `hashCode()` function must be independent of time/ stopwatches, random number generators, or any methods that would not give us a consistent `hashCode()` across multiple `hashCode()` functions call on the same object instance.

Note that there are no requirements that state that unequal objects should have different hash function values.

One could argue that these two requirements are in fact the same requirement. We can restate the requirement of consistency. Imagine we make a pointer named `A` to an object `0` at 12:00 pm and a pointer named `B` to this same object `0` at 1:00 pm. We know that the hash code should return the same integer for both objects, due to the consistency requirement. However, how do we formally define our statement "this same object `0`" above? Technically, the only reason we consider `B` to be pointing to the same thing as `A` is because of the `.equals()` method! This is starting to sound a lot like the determinism requirement. 

**Implementation of Java's HashCode:**

```java
@Override
public int hashCode(){
	int h = cachedHashValue;
	if(h == 0 && this.length() > 0){
		for(int i = 0; i < this.length(); i++){
			h = 31 * h +this.charAt(i);
		}
		cachedHashValue = h;
	}
	return h;
}
```
As we can see, the strings are represented as a base of $31$, we use a small base here since real hash codes don't care about uniqueness. The calculated hash values are being stored / cached, such that future `hashCode` calls are faster. A mathematical representation for the `hashCode()`:
$$ h(s) = s_0 \times 31^{n-1} + s_1 \times 31^{n-2} + ... + s_{n-1}$$
**Why not base of 126?**
The reason why we would use a base of $32$ instead is because the larger intermediate values caused by a higher base can lead to major collisions and overflow. Using a base of 126, we have a large value for each character, and when we multiply these values by power of the base, the intermediate results becomes large very quickly. As we mentioned before, integer data types in Java are typically 32-bit for `int`, 64 bit for `long`. Using a base of 126 makes our final hash code very likely to exceed these limits, and leads to a loss of original value and magnitude as it overflows. For instance: $126^{32} = 126^{33} = 126^{34}$, any strings that end up in the same 32 characters are the same, as the upper characters are all multiplied by 0.

$31$ is a rather small prime number, and using a prime number as the base helps in better distributing the hash codes and reducing the likelihood of patterns in the input string leading to collisions. For instance, choosing a base of `10` in decimal or `16` in hex as modulus shows a clear pattern of hash value distribution: 
- $11 \% 10 \rightarrow 1$, $21 \% 10 \rightarrow 1$ , values with same last digits will collide.
But using a prime as modulus, the only pattern is that the multiple of the modulus will always hash into 0, otherwise hash values distribution are evenly spread in a **uniform distribution**.
Also randomizing collisions, it leads to faster retrieval times (collisions require more operation to find the correct data), preventing collision attacks, and helps with integrity check.

**Hashing a Collection:** Turns out lists are a lot like strings, where they are collections of items each with its own `hashCode`:

```java
@Override 
public int hashCode(){
	int hashCode = 1;
	for(Object o: this){
		hashCode = hashCode * 31; //elevate/smear the current hashCode;
		hashCode = hashCode + o.hashCode(); //add new item's hashCode;
	}
	return hashCode;
}
```
**Hashing a recursive data structure:** Computing the `hashCode` of a recursive data structures involves recursive computation, for instance a binary tree `hashCode` (assuming sentinel leaves):
```java
@Override
public int hashCode(){
	if(this.value == null){
		return 0;
	}
	return this.hashCode() + 31 * this.left.hashCode() + 
	31 * 31 * this.right.hashCode();

}
```

**Ideal HashCode:**
1. The `hashCode()` function must be valid.
2. The `hashCode()` function values should be spread as uniformly as possible over the set of all integers.
3. The `hashCode()` function should be relatively quick to compute [ideally O(1) constant time mathematical operations]

---

