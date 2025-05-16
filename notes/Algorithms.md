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

## Random Number Generation
Randomness underpins a wide range of algorithms and data structures, from Monte Carlo simulations to randomized hashing schemes. This section provides a rigorous account of how random numbers are generated, classified, and transformed into non-uniform distributions.

### 1. True Randomness
**Definition.** A _true random number_ is sampled from a physical process whose outcome is fundamentally unpredictable (i.e., possesses high entropy).

**Properties:**
1. **Entropy Source:** Physical phenomena (e.g., thermal noise, atmospheric noise, quantum phenomena) supply entropy.  
2. **Blocking Behavior:** Collection of sufficient entropy may require waiting on hardware sensors, resulting in latency.  
3. **Unpredictability Guarantee:** Given identical system conditions, successive draws remain non-repeatable and non-deterministic.

**Use Cases:** Secure key generation, cryptographic nonces, high-assurance randomness for protocols.

### 2. Pseudo-Random Number Generation (PRNG)
**Definition.** A _pseudo-random number generator_ is a deterministic algorithm $G$ that, given an initial seed $s_0\in\mathcal S$, produces a sequence $\{s_n\}_{n\ge0}$ and outputs values $\{r_n\}$ that approximate samples from a uniform distribution.

Formally,
$$
s_{n+1} = f(s_n),\quad
r_n = h(s_n),
$$
where $f:\mathcal S\to\mathcal S$ is the state transition, and $h:\mathcal S\to [0,1]$ maps state to a real in $[0,1]$.

**Characteristics:**
- **Periodicity:** Sequence repeats after period $T\le|\mathcal S|$.  
- **Reproducibility:** Identical seed $\Rightarrow$ identical sequence.  
- **Speed:** Algorithmic, non-blocking; suitable for high-throughput simulations.

**Example: Linear Congruential Generator (LCG)**
$$
s_{n+1} = (a\,s_n + c)\bmod m,\quad
r_n = \frac{s_n}{m},
$$

with constants $a,c,m$. Guarantees period $m$ under standard parameter choices.

### 3. Hybrid Random Number Generation

**Motivation:** Leverage true entropy to seed fast PRNGs, combining unpredictability with throughput.

**Procedure:**

1. Obtain a single true random sample $s^*$ from a hardware RNG.
2. Initialize PRNG: $s_0 \leftarrow s^*$.
3. Generate $\{r_n\}$ via the PRNG recurrence.
    
**Benefits:**
- _Single Entropy Draw:_ Only the initial seed requires a physical measurement.
- _Efficiency:_ Subsequent draws incur only algorithmic cost.
- _Security:_ Seed unpredictability ensures sequence cannot be predicted without s∗s^*.
    
### 4. Random Number Generation in C++

C++ standard library provides `<random>` since C++11.
**Uniform Engine:** e.g., `std::mt19937`

```cpp
#include <random>

std::random_device rd;                         // True entropy source (if available)
std::mt19937     gen(rd());                    // Mersenne Twister seeded by rd()
std::uniform_int_distribution<int> dist(1, 6); // Uniform integers in [1,6]
int roll = dist(gen);                          // Sample roll of a die
```

**Distributions:** Any engine can be coupled with distribution objects:

- `std::uniform_real_distribution<T>`
- `std::normal_distribution<T>`
- `std::exponential_distribution<T>`
- etc.

**Legacy API (`<cstdlib>`):**

```cpp
#include <cstdlib>
#include <ctime>

srand(static_cast<unsigned>(time(nullptr)));
int r = rand();          // 0 ≤ r ≤ RAND_MAX
int uniform = r % N;     // [0, N-1] (bias if RAND_MAX+1 is not multiple of N)
```

### 5. From Uniform to Non-Uniform Distributions

Given access to i.i.d. samples $U\sim\mathrm{Uniform}(0,1)$, one may generate arbitrary target distributions FF via the _inversion method_:

**Theorem (Inversion).** If $F$ is a continuous CDF on $\mathbb R$, then
$$X = F^{-1}(U)$$
has distribution $F$.

**Examples:**
- **Exponential ($\lambda>0$):**
    
    $$X = -\tfrac1\lambda \ln(1-U),\quad U\sim\mathrm{Uniform}(0,1)$$
- **Gaussian ($\mu,\,\sigma^2$):** Box–Muller transform
    
    $$ Z_1 = \sqrt{-2\ln U_1}\cos(2\pi U_2),\quad Z_2 = \sqrt{-2\ln U_1}\sin(2\pi U_2),$$
    with each $Z_i\sim N(0,1)$.
    
**Rejection Sampling:** When $F^{-1}$ is unavailable:

1. Sample $Y$ from proposal $g(y)$ with known envelope constant $M$ such that $f(y)\le M g(y)$.
2. Accept $Y$ with probability $\tfrac{f(Y)}{M g(Y)}$; otherwise reject and repeat.
---

### Beyond Uniform Distributions:

Once non-uniform samplers are in place, one can build more complex stochastic processes:
- **Markov Chain Monte Carlo (MCMC):** Construct a Markov chain with stationary distribution $F$.
- **Stratified & Importance Sampling:** Partition the domain or weight samples to reduce variance.
- **Correlation & Transformations:** Generate multivariate distributions via copulas or linear transforms.
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

### <u>3-SAT</u>

The input to 3-SAT consists of a Boolean formula in conjunctive normal form (CNF) with 3 (distinct) variables per clause. For example:
$$f(x_1, x_2, x_3) = (x_1 \lor x_2 \lor x_3) \land (\overline{x}_1 \lor \overline{x}_2 \lor \overline{x}_3) \land (x_1 \lor \overline{x}_2 \lor x_3) \land (\overline{x}_1 \lor x_2 \lor x_3)$$
This is a 3-SAT formula with $m=4$ clauses and $n=3$ variables, it is satisfied by the assignment $(x_1,x_2,x_3) = (t,f,t)$. In the 3-SAT problem, we are given a 3-SAT formula $f(x_1,..., x_n)$ with $m$ clauses and $n$ variables and want to find a satisfying assignment.

3-SAT is the quintessential NP-complete search problem, and there is no polynomial time algorithm to solve it unless $P=NP$. However this does not prevent us from _approximate_ the problem. The goal is now to find an assignment that satisfies as many clauses as possible. Of course an exact algorithm for this maximization version implies a polynomial time algorithm for the decision version. Instead we will design algorithms that do not guarantee the maximum, but are competitive up to a multiplicative factor when compared to the optimum solution. 

Given a SAT formula $f$, let $OPT$ denote the maximum number of clauses that are satisfiable. For $\alpha \in [0,1]$, an $\alpha-$approximation algorithm for SAT is an algorithm that produces an assignment that satisfies at least $\alpha$ $OPT$ clauses. While obtaining an exact algorithm is NP-Hard, for fixed $\alpha < 1$, it is not necessarily NP-Hard to obtain an $\alpha$-approximation algorithm for SAT.

**Simple randomization approximation algorithm for SAT:** Given a formula $f(x_1,...,x_n)$, for each variable $x_i$, flip a fair coin and assign $x_i = t$ or $x_i = f$ accordingly. We will show that, on average, this random assignment satisfies at least $(7 / 8)m$ clauses out of $m$ total. Moreover, we will be able to _derandomize_ the above algorithm and obtain a deterministic algorithm that (always) satisfies at least. Here's an pseudocode for the algorithm:

random-SAT$(f(x_1,...,x_n))$:
1. For each $i \in [n]$, draw $x_i \in {t, f}$ independently and uniformly at random.
2. Return $x_1,...,x_n$.

Surely, an algorithm as simple as the randomized algorithm above could not be very good. In fact it is the best possible polynomial time algorithm unless P = NP. The PCP theorem states that for all constants ϵ > 0, getting better than a (7/8 + ϵ)- approximation to 3SAT is NP-Hard. “PCP” standards for probabilistically checkable proofs. The PCP theorem gives similar hardness of approximation results for many other problems besides SAT. The PCP theorem (as the name suggests) has strong connections to randomized algorithms.

**Theorem 1.1:** There is a polynomial time algorithm that given any 3-SAT formula computes an assignment that satisfies at least $\frac{7}{8}$th of the clauses. Moreover, for all $\epsilon > 0$, a polynomial time approximation algorithm with approximation ratio $(\frac{7}{8} + \epsilon)$ implies that P=NP.

### <u>Sorting:</u>
The next problem we will discuss is _sorting_.  The goal is to take an unordered list of $n$ comparable elements (e.g numbers) and return them as a list in sorted order. The reader likely knows that the `merge-sort` algorithm runs in $O(n log n)$, and that there is a $\Omega(n log n)$-time lower bound for any sorting algorithm in the comparison model. Here we will study a randomized algorithm that is remarkably simple, called `quick-sort`, that is often the preferred one in practice. The idea is we select an element _uniformly at random_ out of the list to serve as a _pivot_. Divide the elements into those smaller and larger than the pivot, and recurse on both halves. Here's an pseudocode for `quicksort`:

`quick-sort`$(A[1..n]):$
// For simplicity, we assume all elements are distinct, otherwise break ties consistently
1. If $n \leq 1$, then return $A$.
2. Select $i \in [n]$ uniformly at random. (pivot)
3. $B[1..k] \leftarrow$ recursively sort the set of elements less than $A[i]$.
4. $C[1..l] \leftarrow$ recursively sort the elements greater than $A[i]$.
5. Return the concatenation of $B, A[i], C$.

**Worse-case running time:** 
Observe that the running time of `quick-sort` is proportional to the total number of comparisons made by the algorithm. It is certainly possible that the algorithm makes $\Omega(n^{2})$ comparisons. (How?) 

In a limited sense that algorithm has a worst-case $O(n^{2})$ time, however the algorithm is randomized, a more useful measure is the _average_ number of comparisons. We will show that `quick-sort` takes $O(nlogn)$ time on average _against any input_. This is still a worst-case analysis in the sense that it holds for all _inputs_. (This is not to be confused with the performance of an algorithm against a randomized input from a fixed distribution — that is called average case analysis.)
We will also show that the algorithm takes $O(n log n)$ time with extremely high probability. In summary we will prove the following theorem in this chapter.

**Theorem 1.2.** Given a list of $n$ comparable elements, `quick-sort` returns the elements in a sorted list in $O(n log n) expected time with high probability.

### <u>Selection:</u>
The last problem we mention is selection. The input, similar to sorting, includes an unordered list of n comparable elements. Given an index $k \in [n]$, the goal is to find the $k$th smallest element in the list. 

The obvious solution is to sort the list, which takes $O(nlogn)$ time. But in fact one can do better: the "median-of-medians" divide-and-conquer algorithm, which runs in $O(n)$ time. The algorithm is a bit of tricky and hard to describe. Here's an simpler alternative, which is similar to quick-sort: pick a pivot uniformly at random, and compute its rank $l$. Depending on whether $k=l, k<l, \text{or} \space k>l$ either return the pivot, recurse on the subset of elements less than the pivot, or recurse on the subset greater than the pivot. Here's an pseudocode:

`quick-select`$(A[1..n],k)$
// The goal is to find the rank $k$ elements in $A[1..n]. We assume for simplicity that all elements are distinct.

1. Randomly select $i \in [n]$ uniformly at random.
2. Compute the rank $l$ of $A[i]$.
3. If $l = k$, then return $A[i]$.
4. If $l>k$, then recursively search for the rank $k$ element among the set of $l-1$ elements less than $A[l]$, and return it.
5. If $l < k$, then recursively search for the rank $k - l$ element among the set of $n - l$ elements greater than $A[l]$, and return it.

We will prove the following theorem which states that quick-select takes O(n) time in expectation. Or rather, you will prove it, in exercise 1.2, employing the new tools gained from analyzing randomized SAT and sorting.

**Theorem 1.3.** `quick-select`$(A[1..n],k)$ returns the rank $k$ element in $O(n)$ time in expectation and with high probability.





---
Consider this following problem which came up when email services was first implemented, and security was not a thing back then, everybody was using easily breakable passwords like `1234`, and we want to handle this by using a list of unacceptable passwords. The company anticipates that this list will grow remarkably large so we should find a space-efficient way of storing the list. 
We would also be able to query the storage structure to see if a password is acceptable or not.

This can be formalized into a mathematical setting:
- We have a huge universe of elements $U = \{0,1,...,N-1\}$, and we want to maintain a subset $S\subset U$, where $|S| = m$. We want to store the subset in some structure $H$ such that $|H| = n = cm$. where $c$ is a constant $>=1$. 
- The following operations have to be accommodated: insertion of an element into the subset $S$ and for any element $x$ being able to check where $x \in S$. 
- Deletion isn't considered here. We are willing to accommodate a smaller number of false positive response to our queries, i.e. if $x \notin S$, then we want to answer no, but we are okay with rarely answering the query wrongly.  

In the motivating example, the universe would correspond to the set of all passwords, and the subset would correspond to the set of unacceptable passwords. To complete the analogy, if a password is unacceptable, we want to **GUARANTEE** that it isn't used. So given a password $x$, if $x \in S$, then we always want to reject the password but if $x \notin S$, then we are okay with occasionally making the user pick a new password because the cost is only mild annoyance. However, we want to keep the probability of such false positive low. 

### <u>Bloom FIlters:</u>
The structure of bloom filters is a simple $0-1$ array $H$ of size $n$, initially all $0$. One of the major assumptions we make is that we are given $k$ hash functions, $h_1,h_2,...,h_k$ that map elements from the universe $U$ to $\{0,1,...,n-1\}$, that they map elements from the universe to indices of $H$. 

We also assume that the hash functions we are given are random and independent of each other. In this context, random means that an element when hashed under a particular function has equal chance of being mapped to any location in $H$. Independent means that this probability does not change even when conditioned on what indices this or any other element is hashed to by any subset of the $k-1$ other hash functions.

**Bloom filter algorithms for operations:** 

---
**Insertion**:
input: $x$
output: the updated $H$ structure, with $x$ inserted

for $i \leftarrow 1$ to $k$ do
	compute $h_i(x)$
	Set $H[h_i(x)]$ to $1$ 
	
---
**Query**:
input: $x$
output: **YES** or **NO**

Set count to $0$
for $i \leftarrow 1$ to $k$ do
	compute $h_i(x)$
	 if $H[h_i(x)] = 1$ then
		 Increment count by 1
if $count=k$ then
	return **YES**
return **NO**

---
Insertion simply computes the hash values for the input element under the different hash functions and makes all the correspond bits 1. Querying simply computes the hash values for the input element and checks if all the corresponding indices are set to 1. IF yes, then it assumes that the bits were set when the element was inserted earlier and say **YES**, else it says **NO**. 

**Bloom Filter Querying Analysis:** An important note is that when $x$ has previously been inserted, all of the corresponding hash bits are set to $1$ and that does not change with any additional insertion or query operations. Hence, if $x \in S$, then the algorithm will always correctly output **YES**. The analysis hence reduces to considering the probability of a false positive. Clearly, false positives are possible; If the insertion of some other elements caused all the bits corresponding to $x$ to be set to 1, then the algorithm will output the wrong result. 

Now, consider the addition of one element $x$. Consider the probability that for a hash, that $x$ is indexed to some index $i$. 
$$ \forall k: Pr(h_k(x)=i) = \frac{1}{n} \Rightarrow Pr(h_k(x) \neq i) = 1 - \frac{1}{n}$$
The above equation follows from the fact that we assume that the hash functions are completely random. Now since the hash functions are completely independent of each other, the probability that **NONE** of the hash function is indexed to i can be easily calculated. Note that this is also the probability that the index $H(i)$ is set to $0$ after insertion. 
$$Pr(H[i] = 0 \space \text{after the first insertion}) = (1-\frac{1}{n})^{k}$$
Now we assume that the elements are randomly drawn and hence the insertions are independent of each other in terms of the bits they set. Consider that all $m$ insertions are done. Then, the probability that $H[i]$ is $0$, is the probability that $H[i]$ is $0$ after the first insertion multiplied by the probability that $H[i]$ is $0$ after the second insertion and so on. Hence: 
$$
Pr(H[i] = 0 \space \text{after all insertions}) = (1-\frac{1}{n})^{mk} 
$$
$$ 
Pr(H[i]=1 \space \text{after all insertions}) = 1-(1-\frac{1}{n})^{mk}= 1-(1-\frac{1}{n})^{nm\frac{k}{n}}\approx 1-e^{-m\frac{k}{n}}
$$
Now for a false positive, all the $k$ hashed bits that are corresponded to $x$ have to be set to $1$. Since the hash functions are independent to each other, the probability that the hashed bits are set to $1$ is simply a product of the individual probabilities which can be bounded by: 
$$Pr(\text{False Positive}) = (1 - e^{\frac{-mk}{n}})^{k} = (1-e^{\frac{-k}{c}})^{k}$$
where the last equality follows because $n=cm$.

Now we can find the value of $k$ that minimizes the probability of a false positive by simply differentiating the probability with respect to $k$ and setting it to $0$, and then taking the second derivative to check that it is indeed a global minimum.
Set $f = \left(1 - e^{-k/c}\right)^k$. Now set $g = \log_e(f) = k \log_e\left(1 - e^{-k/c}\right)$.

$$\frac{d}{dk}(g) = \ln\left(1 - e^{-k/c}\right) + \frac{k}{c} \frac{e^{-k/c}}{\left(1 - e^{-k/c}\right)} = 0$$
Solving the above equation, we get the optimal value for $k$ as $k = cln2$. Taking the second derivative and substituting this value for $k$, we see that the value is $\geq 0$ indicating that this point is indeed a global minimum. Now, going back to the equation for $k$ and substituting:
$$Pr(\text{False Positive}) = (1-e^{-\frac{k}{c}})^{k} = (1-e^{-ln2})^{cln2} =
((\frac{1}{2})^{ln2})^{c} = 0.61^{c}$$
Note that for $c=100$, $Pr(\text{False positive}) = 1.3 \times 10^{-21}$ which is remarkably low.

---

