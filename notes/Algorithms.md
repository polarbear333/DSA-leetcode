
# Randomized Algorithms
The phenomenon of _randomness_ has had numerous applications throughout history, including being used in dice games, coin-flips, shuffling cards etc.

To Computer Scientists, _randomness_ is essential to a special class of algorithms known as **Randomized Algorithms**: algorithms that employ a degree of randomness as part of their logic. **Randomized Algorithms** can be broken down into two classes of algorithms:\

* ***Las Vegas Algorithms**, which use the random input to reduce the expected running time or memory usage but are guaranteed to terminate with a correct result

* **Monte Carlo Algorithms**, which have a chance of producing an incorrect result (but hopefully perform "pretty well" on average).

In the context of data structures specifically, _randomness_ is essential to a special class of data structures known as **Randomized Data Structures**, which are data structures that incorporate some form of random input to determine their structure and data organization, which in turn affects their performance in finding, inserting, and removing elements.



# Recursion and Dynamic Programming

We will use the classic Fibonacci function as an example of recursion and use of dynamic programming. First, let's write out the Fibonacci function as a recap of how it works:
$$ F(n) = \begin{cases} n & \text{if } n \leq 2 \\ F(n-1) + F(n-2) & \text{if } n > 2 \end{cases} $$

In foundation computer science courses, we are taught to learn recursion to calculate the Fibonacci function using the naive approach, which repeatedly calculates the same Fibonacci number over and over. This is because it breaks down the function into two separate parts like a tree structure, but calculating it repeatedly. For example, $F(4)$ is being calculated 3 times in the naive approach. This leads to an exponential time complexity of $O(2^n)$ which is very inefficient as $n$ becomes large. 

Dynamic programming solves the Fibonacci problem efficiently by avoiding these redundant calculations. We can use Memoization which involves recursion to store the result of already calculated Fibonacci numbers. When the function is called for a number that's already in the memory, it simply retrieves the result instead of recalculating it. Here are both naive and DP approaches:

```
int fib(int n){
	if (n < = 2) return 1;
	return fib(n-1) + fib(n-2);
}
```

```
vector<int> saved(n, -1); #Creates a vector for storing fib results
int fib(int n){
	if(n <= 2) return 1;
	if(saved[n] == -1){
	 saved[n] = fib(n-1) + fib(n-2);
	}
	return saved[n];
}
```

# Tower of Hanoi
Imagine we have a mathematical puzzle of 3 towers and `N` number of disks of various diameters or size. The disks are stacked on the left most tower in a decreasing size, smallest on top and largest at the bottom. We want to move the entire stack to the right, without changing the conical structure.

We can first give a name to identify these towers: The left most tower is called `F`, which stands for from, the second is called `A`, which stands for assist. Lastly, the third one is called `T`, which stands for to. Then we can formulate a mathematical problem: 
$$
f(n, from, assist, to)
$$
where we move `n` number of disks from `F` to `T` using `A` .

Just like Fibonacci function, we can break it down as several subproblems: 
```
void hanoi (int n, F, A, T){
	if (n == 1){
			printf ("move %d from %c to %c\n", n, F, T);
			return
	}
	hanoi (n - 1, F, T, A);
	printf ("move %d from %c to %c\n", n, F, T);
	hanoi (n - 1, A, F, T);
}
```
