
### Subsets

Suppose we look at the set $\textbf{A} = \{1, 2, 3, 4, 5\}$. How many subsets of $A$ contains exactly 3 elements?

First a simpler question: How many subsets of $A$ are there total? In other words, what is $|P(A)|$ (the cardinality of the power set of $A$)? Think about how we would build a subset, we need to decide, for each of the elements of $A$, whether or not to include the element in our subset. So we need to decide "yes" or "no" for the element 1. And for each choice we make, we need to decide "yes" or "no" for the element 2, and so on. For each of the 5 elements, we have 2 choices. Therefore the number of subsets is simply $2 \cdot 2 \cdot 2 \cdot 2 \cdot 2 = 2^{5}$.

Of those 32 subsets, how many have 3 elements? We cannot just use the multiplicative principle here. Maybe we want to say we have 2 choices (yes/no) for the first element, 2 choices for the second, 2 choices for the third, and then only 1 choice for the other two. But what if we said “no” to one of the first three elements? Then we would have two choices for the 4th element. What a mess!

Another (bad) idea: we need to pick three elements to be in our subset. There are 5 elements to choose from. So there are 5 choices for the first element, and for each of those 4 choices for the second, and then 3 for the third (last) element. The multiplicative principle would say then that there are a total of $5 \cdot 4 \cdot 3=60$ ways to select the 3-element subset. But this cannot be correct ($60>32$ for one thing). One of the outcomes we would get from these choices would be the set $\{3,2,5\}$, by choosing the element 3 first, then the element 2, then the element 5. Another outcome would be $\{5,2,3\}$ by choosing the element 5 first, then the element 2, then the element 3. But these are the same set! We can correct this by dividing: for each set of three elements, there are 6 outcomes counted among our 60 (since there are 3 choices for which element we list first, 2 for which we list second, and 1 for which we list last). So we expect there to be 10 3-element subsets of $A$. Therefore: $$ 3! = 3 \cdot 2 \cdot 1 = 6, \frac{60}{6} = 10$$
## Bit String

"Bit" is short for "binary digit", so a **bit string** is a string of binary digits. The **binary digits** are simply the numbers 0 and 1. All of the following are bit strings: $$ 1001\space 0 \space 1111 \space 1010101010$$
  
The number of bits (0’s or 1’s) in the string is the length of the string; the strings above have lengths 4, 1, 4, and 10 respectively. We also can ask how many of the bits are 1’s. The number of 1’s in a bit string is the weight of the string; the weights of the above strings are 2, 0, 4, and 5 respectively.

> [!INFO] Bit Strings
> - An n-bit string is a bit string of length $n$, which means a string containing $n$ symbols, each of which is a bit, either $0$ or $1$.
> - The **weight** of a bit string is the number of 1's in it
> -  $B^{n}$ is the set of all $n$-bit strings.
> - $B^{n}_{k}$ is the set of all $n$-bit strings of weight $k$.

For example, the elements of the set $B^{3}_{2}$ are the strings 011, 101, and 110.
Those are the only strings containing three bits exactly two of which are 1's.

The counting questions: How many bit strings have length 5? How many of those have weight 3? In other words, we are asking for the cardinalities $|B^{5}|$, and $|B^{5}_{3}|$.

To find the number of 5-bit strings is straight forward. We have 5 bits, and each can either be a 0 or a 1. So there are 2 choices for the first bit, 2 choices for the second, and so on. By the multiplicative principle, there are $2 \cdot 2 \cdot 2 \cdot 2 \cdot 2 = 2^{5} = 32$ such strings. 

Finding the number of 5-bit strings of weight 3 is harder. Think about how such a string could start. The first bit must be either a 0 or a 1. In the first case (the string starts with a 0), we must then decide on four more bits. To have a total of three 1's, among those four remaining bits there must be three 1's. To count all of these strings, we must include all 4-bits strings of weight 3. In the second case (the string starts with a 1), we still have four bits to choose, but now only two of them can be 1's, so we should look at all the 4-bit strings of weight 2. So the strings in $|B^{5}_{3}|$ all have the form of $1B^{4}_{2}$ (that is, a 1 followed by a string from $B^{4}_{2}$) or $0B^{4}_{3}$. These two sets are disjoint, so we can use the additive principle. $$|B^{5}_{3}| = |B^{4}_{2}| + |B^{4}_{3}|$$
This is an example of **recurrence relation.** We represented one instance of our counting problem in terms of two simpler instances of the problem. If we only knew the cardinalities of $B^{4}_{2}$ and $B^{4}_{3}$. Repeating the same reasoning, 
$$ |B^{4}_{2}| = |B^{3}_{1}| + |B^{3}_{2}| \space \text{and} \space |B^{4}_{3}| = |B^{3}_{2}| + |B^{3}_{3}|$$
 We can keep going down, but this should be good enough. Both $B^{3}_{1}$ and $B^{3}_{2}$ contain 3 bit strings: we must pick one of the three bits to be a 1(three ways to do that) or one of the three bits to be a 0 (three ways to do that). Also, $B^{3}_{3}$ contains just one string: 111. Thus $|B^{4}_{2}| = 6$ and $|B^{4}_{3}| = 4$, which puts $|B^{5}_{3}|$ at a total of 10 strings. 
 
But wait, 32 and 10 were the answers to the counting questions about subsets. Coincidence? Not at all. Each bit string can be thought of as a _code_ for a subset. To represent the subsets of , $A=\{1,2,3,4,5\}$, we can use 5-bit strings, one bit for each element of .A. Each bit in the string is a 0 if its corresponding element of A is not in the subset, and a 1 if the element of A is in the subset. Remember, deciding the subset amounted to a sequence of five yes/no votes for the elements of .A. Instead of yes, we put a 1; instead of no, we put a 0.

For example, the bit string 11001 represents the subset $\{1,2,5\}$ since the first, second and fifth bits are 1’s. The subset $\{3,5\}$ would be coded by the string 00101. What we really have here is a bijection from $P(A)$ to $B^{5}$.

Now for a subset to contain exactly three elements, the corresponding bit string must contain exactly three 1’s. In other words, the weight must be 3. Thus counting the number of 3-element subsets of A is the same as counting the number 5-bit strings of weight 3.

## Lattice Paths

The **integer lattice** is the set of all points in the Cartesian plane for which both the $x$ and $y$ coordinates are integers. If you like to draw graphs on graph paper, the lattice is the set of all intersections of the grid lines.

A **lattice path** is one of the shortest possible paths connecting two points on the lattice, moving only horizontally and vertically. For example, here are three possible lattice paths from the point $(0,0)$ to $(3,2)$. 
![[Pasted image 20250520152024.png]] ![[Pasted image 20250520152046.png]] ![[Pasted image 20250520152053.png]]

Notice to ensure the path is the _shortest_ possible, each move must be either to the right or up. Additionally, in this case, note that no matter what path we take, we must make three steps right and two steps up. No matter what order we make these steps, there will always be 5 steps. Thus each path has _length_ 5.

**Counting Question:** How many lattice paths are there between $(0,0)\space \text{and} \space (3,2)$? 
We can list which direction we trave on each of the 5 steps. One path maybe RRUUR, or maybe UURRR, or perhaps RURRU. (Corresponding to the paths drawn above). So how many such strings of R's and U's are there? 

Notice that each of these strings must contain 5 symbols. Exactly 3 of them must be R's (since our destination is 3 units to the right). This seems awfully familiar, in fact, what if we used $1$'s instead of R's and $0$'s instead of U's? Then we would just have a 5-bit strings of weight 3. There are 10 of those, so there are 10 lattice paths from (0,0) to (3,2). The correspondence between bit strings and lattice paths does not stop there. Here is another way to count lattice paths. Consider the lattice shown below:

 ![[Pasted image 20250520180610.png]]
 
 Any lattice path from $(0,0)$ to $(3,2)$ must pass through exactly one of $A$ and $B$. The point $A$ is 4 steps away from $(0,0)$ and two of them are towards the right. The number of lattice paths to $A$ is the same as the number of 4-bit strings of weight 2, namely 6. The point B is 4 steps away from $(0,0)$, but now 3 of them are towards the right. So the number of paths to point B is the same as the number of 4-bit strings of weight 3, namely 4. So the total number of paths to $(3,2)$ is just $6+4$. This is the same way we calculated the number of 5-bit strings of weight 3. The point: the **exact same recurrence relation exists for bit strings and for lattice paths.**

### Combinations of unique lattice paths
In fact, we can construct the problem into combinatorics to find total number of unique paths. Given a $(m \times n)$ grid, we will start at $(0,0)$ of the top left corner, and wants to find the total number of unique path travelling to $(m-1, n-1)$ of the bottom right corner, where we can only move downwards or right. 

Similarly, there are no shortest path for this question, hence no matter what order we make these steps, the total amount of steps required will always correspond with the height and length of the grid. In this case it will be $(m-1)$ steps downwards and $(n-1)$ steps to the right. Total paths would be $(m-1) + (n-1) = (m+n-2)$. 

The problem now reduces to choosing the positions for the down (or right) moves among the total steps. We can use the **Binomial coefficient** formula to find the total combinations:
$$C(m + n - 2, m - 1) = \frac{(m + n - 2)!}{(m - 1)! (n - 1)!}$$

Now we can move on to calculate the formula in code, but first we need to consider efficient ways of calculating the value. While we can use the **recurrence relation** given above: $T(n,k)=T(n−1,k)+T(n−1,k−1)+O(1)$, where we find the value of $C(n,k)$ using the optimal solutions of subproblems $C(n-1, k-1)$ and $C(n-1,k)$ in DP, as the subproblems in the recursion tree are computed multiple times. However the most efficient time complexity in this approach is $O(n \cdot k)$  and $O(k)$ space complexity.  

Instead we can use the properties of factorials to calculate it in $O(k)$ time and using constant space.
In binomial coefficient, the product terms in the numerator $(N \cdot N-1 \cdot \ldots \cdot (N-k+1))$ are always perfectly divisible by the product terms in the numerator $(k \cdot (k-1) \cdot \ldots \cdot 1)$. (which always ending up as an integer).  Therefore we can initialize an 64-bit integer that equals to 1, and iteratively multiplying it by the numerator term and divide by the denominator term, taking only $O(1)$ space. If we multiply the numerator and denominator naively, the intermediate product will easily overflow.  Here's an implementation of the approach in C++:
```cpp
class Solution { 
	public: 
	int uniquePaths(int m, int n) { 
		int64_t ans = 1; 
		for (int i = 0; i < min(n,m) -1; i++){ 
			ans *= n + m - 2 - i; 
			ans /= i +1;  
			return ans; 
		} 
	};
```

## Binomial Coefficients

Binomial coefficients are the coefficients in the expanded version of a binomial, such as $(x+y)^{5}$. What happens when we multiply such a binomial out? We will expand $(x+y)^{n}$ for various values of $n$. Each of these are done by multiplying everything out (i.e FOIL-ing) and then collecting like terms:
$$\begin{align}
(x+y)^1 = x + y \\
(x+y)^2 = x^2 + 2xy + y^2 \\
(x+y)^3 = x^3 + 3x^2y + 3xy^2 + y^3 \\
(x+y)^4 = x^4 + 4x^3y + 6x^2y^2 + 4xy^3 + y^4
\end{align}
$$
  
In fact, there is a quicker way to expand the above binomials. For example, consider the next one, .$(x+y)5$. What we are really doing is multiplying out, 
$$\begin{equation*}
(x+y)(x+y)(x+y)(x+y)(x+y)\text{.}
\end{equation*}
$$
Let's try looking back to the case of $(x+y)^3 = (x+y)(x+y)(x+y)$. Why do we only have one $x^3$ and $y^3$ but three $x^2 y$ and $xy^2$ terms? Every time we distribute over an $(x+y)$ we create two copies of what is left, one multiplied by $x$, the other multiplied by $y$. To get $x^3$, we need to pick the "multiplied by $x$" side every time (we don't have any $y$'s in the term). This will only happen once. On the other hand, to get $x^2 y$ we need to select the $x$ side twice and the $y$ side once. In other words, we need to pick one of the three $(x+y)$ terms to "contribute" their $y$. 

Similarly, in the expansion of $(x+y)^5$, there will be only one $x^5$ term and one $y^5$ term. This is because to get an $x^5$, we need to use the $x$ term in each of the copies of the binomial $(x+y)$, and similarly for $y^5$. What about $x^4 y$? To get terms like this, we need to use four $x$'s and one $y$, so we need exactly one of the five binomials to contribute a $y$. There are 5 choices for this, so there are 5 ways to get $x^4 y$, so the coefficient of $x^4 y$ is 5. This is also the coefficient for $xy^4$ for the same (but opposite) reason: there are 5 ways to pick which of the 5 binomials contribute the single $x$. So far we have $$(x+y)^5 = x^5 + 5x^4 y+ ?x^3y^3 + ?x^2y^3 + 5xy^4 + y^5$$
We still need the coefficients of $x^3 y$ and $x^2 y^3$. In both cases, we need to pick exactly 3 of the 5 binomials to contribute one variables, the other two to contribute the other. This seems familiar, we have 5 things, each one can be one of two things, and we need a total 3 of one of them. That's just like taking 5 bits and making sure exactly 3 of them are 1's. So the coefficient of $x^3 y^2$ (and also $x^2y^3$) will be exactly the same as the number of bit strings of length 5 and weight 3, which we found earlier to be 10. So we have: $$(x+y)^5 = x^5 + 5x^4 y+ 10x^3y^3 + 10x^2y^3 + 5xy^4 + y^5$$
These numbers we keep seeing over and over again. They are the number of subsets of a particular size, the number of bit strings of a particular weight, the number of lattice paths, and the coefficients of these binomial products. We will call them binomial coefficients. We even have a special symbol for them: $\binom{n}{k}.$ 

>[!INFO] Binomial Coefficients
>For each integer $n /geq 0$ and integer $k$ with $0 \leq k \leq n$, there is a number $$\binom{n}{k}$$ read "$n$ choose $k$". We have:
>- $\binom{n}{k} = |B^n_k|$, the number of $n$-bit strings of weight $k$.
>- $\binom{n}{k}$ is the number of subsets of a set of size $n$ each with cardinality $k$.
>- $\binom{n}{k}$ is the number of lattice paths of length $n$ containing $k$ steps to the right.
>- $\binom{n}{k}$ is the coefficient of $x^ky^{n-k}$ in the expansion of $(x+y)^n$.
>- $\binom{n}{k}$ is the number of ways to select $k$ objects from a total of $n$ objects.

The last bullet point is usually taken as the definition of  $\binom{n}{k}$. Out of $n$ objects we must choose $k$ of them, so there are $n$ choose $k$ ways of doing this. Each of our counting problems above can be viewed in this way:

- How many subsets of $\{1,2,3,4,5\}$ contain exactly $3$ elements? We must choose 3 of the 5 elements to be in our subset. There are  $\binom{5}{3}$ ways to do this, so there are  $\binom{5}{3}$ such subsets.
    
- How many bit strings have length $5$ and weight $3$? We must choose 3 of the 5 bits to be 1’s. There are  $\binom{5}{3}$ ways to do this, so there are  $\binom{5}{3}$ such bit strings.
    
- How many lattice paths are there from $(0,0)$ to $(3,2)$? We must choose 3 of the 5 steps to be towards the right. There are  $\binom{5}{3}$ ways to do this, so there are  $\binom{5}{3}$ such lattice paths.
    
- What is the coefficient of $x^3y^2$ in the expansion of $(x+y)^5$? We must choose 3 of the 5 copies of the binomial to contribute an $x$. There are  $\binom{5}{3}$ ways to do this, so the coefficient is  $\binom{5}{3}$.

It should be clear that in each case above, we have the right answer. All we had to do is phrase the question correctly and it became obvious that $\binom{5}{3}$ is correct. However, this does not tell us that the answer is in fact 10 in each case. We will eventually find a formula for $\binom{n}{k}$, but for now, look back at how we arrived at the answer 10 in our counting problems above. It all came down to bit strings, and we have a recurrence relation for bit strings:
$$ |B^n_k| = |B^{n-1}_{k-1}| + |B^{n-1}_{k}|$$
Remember, this is because we can start the bit string with either a 1 or 0. In both cases , we have $n - 1$ more bits to pick. The strings starting with 1 must contain $k-1$ more 1's, while the strings starting with 0 still need $k$ more 1's. 

Since $|B^n_{k}| = \binom{n}{k}$, the same recurrence relation holds for binomial coefficients: 
$$\binom{n}{k} = \binom{n-1}{k-1} + \binom{n-1}{k}$$

## Pascal Triangle

Let’s arrange the binomial coefficients (nk) into a triangle like follows:
![[Pasted image 20250521142046.png]]

This can continue as far down as we like. The recurrence relation for $\binom{n}{k}$ tells us that each entry in the triangle is the sum of the two entries above it. The entries on the sides of the triangle are always 1. This is because $\binom{n}{0}=1$ for all n since there is only one way to pick 0 of $n$ objects and $\binom{n}{n}=1$ since there is one way to select all n out of n objects. Using the recurrence relation, and the fact that the sides of the triangle are 1’s, we can easily replace all the entries above with the correct values of $\binom{n}{k}$. Doing so gives us Pascal’s triangle.

We can use Pascal’s triangle to calculate binomial coefficients. For example, using the triangle below, we can find $\binom{12}{6} = 924$.
