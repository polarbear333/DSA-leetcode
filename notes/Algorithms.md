****
# Randomized Algorithms
The phenomenon of _randomness_ has had numerous applications throughout history, including being used in dice games, coin-flips, shuffling cards etc.

To Computer Scientists, _randomness_ is essential to a special class of algorithms known as **Randomized Algorithms**: algorithms that employ a degree of randomness as part of their logic. **Randomized Algorithms** can be broken down into two classes of algorithms:\

* ***Las Vegas Algorithms**, which use the random input to reduce the expected running time or memory usage but are guaranteed to terminate with a correct result

* **Monte Carlo Algorithms**, which have a chance of producing an incorrect result (but hopefully perform "pretty well" on average).

In the context of data structures specifically, _randomness_ is essential to a special class of data structures known as **Randomized Data Structures**, which are data structures that incorporate some form of random input to determine their structure and data organization, which in turn affects their performance in finding, inserting, and removing elements.

While we used the vague term _randomness_ above, but we can be more concrete and specify that these are all applications of **random number generation**. For example, 
* rolling a _k_-sided die can be considered equivalent to generating a random number from 1 to _k_,
* flipping a coin can be considered equivalent to generating a random number that can only be 0 or 1, etc.

Even with **Randomized Algorithms** and **Randomized Data Structures**, any applied randomness can be broken down into randomly generating numbers.

---
##  True vs. Pseudo Random Number Generation

### 🎲 What is True Randomness?

- **True random numbers** are generated from physical phenomena that are inherently unpredictable (high entropy).
    
- Common physical sources of randomness include:
    
    - Atmospheric noise
        
    - Thermal noise
        
    - Quantum effects
        
- These sources introduce **natural entropy** (disorder) into the system.
    

### Why is True Randomness Slow?

- Entropy harvesting is limited by the **speed of physical measurement**.
    
- This process is **blocking** — the system must wait until enough entropy is collected.
    
- As a result, **true random number generation is slow** compared to modern processor speeds.
    

---

### Pseudo-Random Number Generation (PRNG)

- **Pseudo-random numbers** are generated algorithmically using a starting value called a **seed**.
    
- Given the same seed, the PRNG will always produce the **same sequence** of numbers.
    
- PRNGs appear random, but are actually **fully deterministic**.
    

#### Example:

> Seed: `42`  
> → Generates a sequence of numbers that looks random.  
> → Reseeding with `42` will regenerate the **same sequence** every time.

### 🚀 Speed Advantage

- PRNGs are **much faster** than true RNGs.
    
- Not bound by physical limits, they are ideal for simulations, games, and non-cryptographic applications.
    

## 🔁 Hybrid Random Number Generation (True + Pseudo)

### ⚡ Combining Both Approaches

- We can seed a **pseudo-random number generator (PRNG)** using a **true random number**.
    
- This provides both:
    
    - Speed (thanks to PRNG)
        
    - Unpredictability (thanks to true entropy)
        
- This hybrid method is practical for many real-world applications.
    

### 🧊 Example: Thermal Noise as True Random Seed

1. Measure a physical source of randomness (e.g., thermal noise).
    
2. Use this to seed the PRNG.
    
3. Each execution produces a different sequence of numbers since the seed changes.
    

> 📌 Only one true random number is needed to seed the PRNG, making this method efficient and non-blocking.

---

## 🎲 Random Number Generation in C++

### 🔢 `rand()` and `srand()`

- `rand()` generates a random number in `[0, RAND_MAX]`.
    
- `srand()` is used to seed the random number generator.
    

```cpp
#include <cstdlib>
#include <ctime>

srand(time(NULL));   // seed random number generator using system time
int number = rand(); // generate random number from 0 through RAND_MAX
```

### 🎚️ Custom Ranges Using Modulo

Generate a number from 0 to 99:

```cpp
int number = rand() % 100; // outputs 0-99
```

Generate a number from 1 to 100:

```cpp
int number = (rand() % 100) + 1; // outputs 1-100
```

### 📦 Sampling from a Vector

```cpp
#include <vector>
#include <string>
using namespace std;

vector<string> myVec;
// populate myVec
int index = rand() % myVec.size(); // random index
string element = myVec[index];    // retrieve element
```

---

## 📊 Beyond Uniform Distributions

- The examples above are all **Uniform Distribution** (equal probability).
    
- With more techniques, we can sample from:
    
    - Gaussian
        
    - Exponential
        
    - Poisson
        
- These advanced distributions are built upon uniform sampling.
    

> 🧠 All random sampling can be **reduced to uniform sampling** + transformation.

---

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

## Hashing

A hash function is an algorithm that takes an input of any length and produces a fixed-size output, known as a **hash** or **hash-value**. Key properties include:
- **Deterministic:**  A hash function must consistently produce the same output of the same input.
- **Fixed Output Size:** The output of a hash function should have a fixed size, regardless of the size of the input. 
- **Efficiency:** The hash function should be able to process input quickly.
- **Uniformity:** The hash function should distribute the hash values uniformly across the output space to avoid clustering. 
- **Collision Resistance:**  A good hash function ensures that different inputs almost never produce the same hash value (collision resistance)

**One-way function:** 
Many hash functions are designed to be one-way, meaning it's computationally infeasible to reverse the process and find the original input of the hash value.

**Hash Functions:**
A **hash function** is a mapping:
$$h: U \rightarrow \{0, 1, ..., m - 1\}$$
Where:
- $U$ is the universe of possible keys (possibly infinite or very large),
- $m$ is the size of the hash table (often a prime or power of two).

**Definition:**
Let $U$ be the universe of keys (e.g., all strings), and let $m \in \mathbb{N}$ be the number of buckets.
A hash function $h: U \rightarrow \{0, 1, \dots, m - 1\}$ satisfies:

- **Determinism**: For all $k \in U$, $h(k)$ always returns the same result.
- **Compression**: It maps a large input space to a smaller range.
- **Uniformity**: Ideally, each slot in the hash table is equally likely to be the output. 
- **Neither surjective nor injective:** Hash functions can map multiple inputs to the same output to cause collision, and may not produce every possible outputs in the hash range.

**Types of hash functions:**
1. **Division method:** This method involves dividing the key by a prime number and using the remainder as the hash value. $$h(k)=kmodm$$Where $k$ is the key and $m$ is a prime number.

Simple but sensitive to $m$. Best to pick $m$ as a **prime number** not close to a power of 2 to avoid patterns in input.

2. **Multiplication Method (Knuth’s):** Knuth multiplicative hash is used to compute an hash value in $\{0, 1, 2, ..., 2^{p} -1\}$ from an integer $k$: $$h(k)=⌊m⋅(k\alpha mod1)⌋$$
	- $\alpha$ is a constant in $(0, 1)$ — often $\alpha = \frac{\sqrt{5}-1}{2} \approx 0.618033$
	- More robust to patterns in $k$, useful when $m$ is a power of $2$.

	**How algorithm works:**
	Suppose that $p$ is in between 0 and 32, the algorithm goes like this:
		- Compute alpha as the closest integer to $2^{32} \frac{(-1 + \sqrt(5))}{2}$. We get $\alpha = 2,654,435,769$.
		- Compute $k * \alpha$ and reduce the result modulo $2^{32}$:
	    $$k * \alpha = n_0 * 2^{32} + n_1 \text{ with } 0 <= n_1 < 2^{32}$$
		- Keep the highest $p$ bits of $n_1$:
$$n_1 = m_1 * 2^{(32-p)} + m_2 \text{ with } 0 <= m_2 < 2^{(32 - p)}$$
	**Implementation:**
	```cpp
	std::uint32_t knuth(int x, int p) {
	    assert(p >= 0 && p <=32);
	
	    const std::uint32_t knuth = 2654435769;
	    const std::uint32_t y = x;
	    return (y * knuth) >> (32 - p);
	}
	```
	Forgetting to shift by $(32-p)$ is a major mistake, as you would lose all the properties of the hash. It would transform an even sequence into an even sequence which would be very bad as all the odd slots would stay unoccupied. Bare in mind that most hash tables implementations don't allow this kind of signature in their interface, as they only allow:
	```cpp
	uint_32t hash(int x)
	```
	and reduce `hash(x)` modulo $2^{p}$ to compute the hash value for x. Those hash tables cannot accept the Knuth multiplicative hash. This might be a reason why so many people completely ruined the algorithm by forgetting to take the higher p bits. So you can't use the Knuth multiplicative hash with `std::unordered_map` or `std::unordered_set`. But I think that those hash tables use a prime number as a size, so the Knuth multiplicative hash is not useful in this case. Using `hash(x) = x` would be a good fit for those tables.

3. **Universal Hashing:**
	Let $\mathcal{H}$ be a set of hash functions:
	- A family $\mathcal{H}$ is **universal** if: $$\Pr_{h \in \mathcal{H}}[h(x) = h(y)] \leq \frac{1}{m} \quad \text{for all } x \neq y$$
	- Used to randomize choice of hash function to reduce worst-case behavior.

**Handling Collisions:** In hashing, a collision occurs when we have multiple elements that have the same index in our array. There are two common methods to deal with collisions in hash tables:
1. **Linear Probing:** Store the colliding keys elsewhere in the array, potentially in the next open array space. This method can be seen with distributed hash tables, which you will see in later computer science courses that you may take.
2. **External Chaining:** A simpler solution is to store all the keys with the same hash value together in a collection of their own, such as a `LinkedList`. This collection of entries sharing a single index is called a **bucket**.