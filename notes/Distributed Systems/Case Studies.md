
## <u>Recommendation System:</u>

Deep learning has been dominating recommendation models as the gigantic amount of user data is a natural fit for massively data-driven neural models. However, efforts to leverage the power of deep learning in industry-level recommendation systems are constantly encountered with problems arising from data derived from real-world user behaviors: 

1. Features are mostly sparse, categorical and dynamically changing
2. The underlying distribution of training data is non-stationary, a.k.a Concept Drift

### Sparsity and Dyanamism

The data for recommendation mostly contain sparse categorical features, which appears with low frequency. Mapping them to a high-dimensional embedding space would give a rise to a series of issues:

1. Unlike language models where number of word-piece are limited, the amount of users and ranking items are orders of magnitude larger. It would be hard to fit an enormous embedding table into single host memory.
2. The size of embedding table is expected to grow over time as more users and items are admitted, while frameworks like Tensorflow uses a fixed-size dense variables to represent embedding table.

In practice, many system adopt **low-collision hashing** as a way to reduce memory footprint and allow growing of IDs. This relies on an over-idealistic assumption:
1. IDs in the embeddings table is distributed evenly in frequency
2. Collisions are harmless to the model quality
3. 
Unfortunately this is rarely true for a real-world recommendation system, where a small group of users or items have significantly more occurrences. With the organic growth of embedding table size, chances of hash key collision increases and lead to deterioration of model quality

```mermaid
flowchart LR
   subgraph Streaming Path
     A[Client Event] --> B(Event Bus)
     B --> C[Feature Store]
     C --> D[Online Trainer]
     D --> E[Live Model Store]
     E --> F[Model Server]
     F --> G[Client Recommendations]
   end

   subgraph Storage & Index Layer
     H[CuckooHashMap] 
     I[HNSW ANN Graph Index]
     J[Social Graph + PageRank Scores]
   end

   subgraph Batch Path
     B --> K[Offline Aggregator]
     K --> L[Batch Trainer]
     L --> E
     K --> M[PageRank Job]
     M --> J
   end
   G -.→ [H & I & J] "Lookup embeddings, ANN neighbors, and influence scores"
   D -.→ H ‘‘Upserts new embeddings’’
   D -.→ I ‘‘Inserts into ANN graph via callback’’
   D -.→ J ‘‘Logs graph edges for social interactions’’
```


---
## Maintaining a corporate network

Imagine you are in charge of maintaining a corporate network (or a major website such as Amazon)
- High speed, high traffic volume, a lot of users

It is expected to perform at near perfect reliability, and constantly under attack by malicious hackers. Monitoring what is going on through the network is also complex:
- Why is it slow? 
- Which machine is being compromised?
- Which application is eating too much bandwidth

**IP Network Monitoring:** Any monitoring software/ engine must be lightweight and not add to the network load. (i.e. these algorithms need smart data structure to track important statistics in real-time.) Consider a toy example:
- Is some IP address sending a lot of data to my network?
	- Which IP sends the most data in last 1 minute?
	- How many different IP in last 5 minutes?
	- Have I seen this IP in the last 5 minutes?

- **IP address format:** 192.168.0.0 (10001011001…0010)
- **IPV4** has 32 bits, while **IPV6** has 128 bits.
- Cannot afford to maintain a table of **all possible IP  addresses** to see how much traffic each is sending.
- These are data structure problems, where obvious/naïve solutions are no good, and require creative/clever ideas.

### Examples of data structures / algorithms for network monitoring:
1. **Bandwidth Monitoring / Traffic Profiling:**
	**Goal:** Detect heavy-hitters, high-bandwidth flows, or application abuse.
	-  **Count-Min Sketch (CMS):** A probabilistic data structure used to estimate the frequency of elements in a stream of data, especially when memory is constrained. We use this to approximate frequency count of flows/IPs/URLs in network traffics.
	- It is **fixed-size**, **sub-linear space**, with **fast update / query in O(1)**
	- Example: Tracking most bandwidth consuming IPs.
**Comprehensive Read - Lecture from Stanford:** [https://cs344-stanford.github.io/lectures/Lecture-4-HHD.pdf]
2.  **Detecting Anomalies or Compromised Machines**
	**Goal: Identify strange communication patter**ns (e.g., beaconing, lateral movement).
	 -  **Graph Structures (Adjacency Lists + Timestamps):** We can model machines as nodes and communications as timestamped edges.
	 - Enables algorithms like PageRank, spectral clustering, or anomaly scoring.
	 - **Time-Decayed Bloom Filters:** Track recent accesses or connections while forgetting stale ones.
3. **Packet/Flow Logging Under Memory Constraints:**
	**Goal:** Log activity from 10M+ flows/sec without using petabytes of storage.
		- **Bloom filters / Cuckoo Filters:** Set membership ("Have i seen this IP address before?") using open-addressing hashtable. It is probabilistic, space-efficient, and allows fast query/update. 
		- **HyperLogLog:** To estimate the number of unique users/flows (cardinality) with fixed memory, and good enough of estimates.
4. **Latency and Bottleneck Analysis:**
	**Goal:** Identify where latency is introduced in the system.
	- **Ring Buffers + Sliding Windows:** Store recent N packets/events per node for rolling stats.
	-  Efficient in-memory circular structure, often combined with moving averages.
	- **Time-series Sketches (e.g., t-digest, HDRHistogram):** For tracking latency percentiles (p95, p99) over sliding windows.
5. **Intrusion Detection and Threat Hunting**
	**Goal:** Detect port scanning, unusual logins, or shell injections
	- **Suffix Trees / Tries + Regex Matching:** Deep packet inspection or matching known malicious patterns in payloads.
	- Trie-based prefix matching is fast; regex over tries can detect polymorphic signatures