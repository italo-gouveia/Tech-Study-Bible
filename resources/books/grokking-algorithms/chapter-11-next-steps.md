# Chapter 11: Next Steps — Grokking Algorithms

## 1. Chapter Overview

In this chapter you will:
- See a brief summary of **ten algorithms** not covered in this book and their uses.
- Get recommendations on what to read next based on your interests.

---

## 2. Binary Search Trees

Let's revisit binary search. When a user logs into Facebook, Facebook needs to search a large array to check if that username exists. As mentioned earlier, the fastest way to search a sorted array is binary search.

**Problem:** Every time a new user is created, you need to insert the username into the array and re-sort it, because binary search only works on sorted arrays.

Wouldn't it be nice if you could insert the new username directly into the correct slot without re-sorting? This is the idea behind the **binary search tree (BST)** data structure.

### 2.1 What a Binary Search Tree Looks Like

Here's a simple binary search tree:

```
        DAVID
       /     \
     ADIT   MANNING
            /     \
        MAGGIE     MIKE
```

### 2.2 Rules

- All nodes to the **left** have **smaller** values.
- All nodes to the **right** have **larger** values.

### 2.3 Example: Searching for "Maggie"

**Step 1:** Start at the root (DAVID).  
Since "Maggie" comes after "David" alphabetically, go right.

```
        DAVID  ← start here
           \
          MANNING
         /       \
     MAGGIE       MIKE
```

**Step 2:** Now at MANNING.  
Since "Maggie" comes before "Manning", go left.

```
            DAVID
               \
              MANNING
             /
         MAGGIE  ← found!
```

**Found Maggie!**

### 2.4 Performance Comparison

| Operation | Array | Binary Search Tree |
|-----------|-------|-------------------|
| Search    | O(log n) | O(log n) |
| Insertion | O(n)  | O(log n) |
| Deletion  | O(n)  | O(log n) |

Binary search trees are **much faster** for insertions and deletions on average. However, this depends on the tree being **balanced**.

### 2.5 Unbalanced Trees

A tree can become unbalanced like this:

```
2
 \
  4
   \
    7
     \
     20
       \
       58
```

It "leans" to the right. This makes operations degrade from O(log n) to **O(n)** in the worst case.

### 2.6 Solutions: Self-Balancing Trees

There are special trees that **automatically balance** themselves:

- **B-trees**
- **Red-black trees**
- **Heaps**
- **Splay trees**

These structures are widely used in databases and file systems.

---

## 3. Inverted Indexes

Here's a simplified version of how a search engine works.

### 3.1 Example: Three Web Pages

**Page A:** "HI THERE"  
**Page B:** "HI ADIT"  
**Page C:** "HI THERE WE GO"

### 3.2 Building the Index

Create a hash table mapping each word to the pages where it appears:

```
WORD   | PAGES
-------|---------
HI     | A, B, C
THERE  | A, C
ADIT   | B
WE     | C
GO     | C
```

### 3.3 Query Processing

**If the user searches for "hi":**  
Return pages: **A, B, C**

**If the user searches for "there":**  
Return pages: **A, C**

This data structure is called an **inverted index** and is used by search engines like Google, Bing, and even local search systems.

---

## 4. The Fourier Transform

The Fourier Transform is one of the rare algorithms that manages to be both brilliant and elegant at the same time. It has thousands of applications.

### 4.1 The Smoothie Analogy

A famous analogy for explaining Fourier: imagine a **smoothie** (a blended drink with multiple ingredients). The Fourier Transform is like a method to **separate each ingredient** from that smoothie.

Similarly, it separates a complex signal into **individual frequencies**.

### 4.2 Applications

- **Audio processing:** boost or reduce bass/treble in music
- **Audio compression:** MP3 format
- **Image compression:** JPEG format
- **Music recognition:** Shazam app
- **Earthquake prediction**
- **DNA analysis**

### 4.3 How It Works (Conceptually)

Fourier allows you to identify exactly which frequencies compose a signal. Once you know this, you can remove less important frequencies — which creates **compression**.

### 4.4 Reference

**Kalid**, "An Interactive Guide to the Fourier Transform", Better Explained.

---

## 5. Parallel Algorithms

The next three topics deal with **scalability** and handling massive amounts of data.

### 5.1 The Shift to Parallelism

**Before:** Computers kept getting faster. To make your algorithm faster, you just waited a few years and computers would be faster.

**Now:** We're near the end of that era. Instead, laptops and computers provide **multiple processing cores**. To make your algorithm faster, you need to make it run **in parallel** across all cores at once!

### 5.2 Example: Parallel Sorting

The best possible performance for a sorting algorithm is approximately:

```
O(n log n)
```

We also know it's **impossible** to sort an array in O(n) unless you use a **parallel algorithm**!

There's a parallel version of Quicksort that can sort an array with runtime approximately:

```
O(n)
```

### 5.3 Challenges of Parallel Algorithms

Parallel algorithms are **hard to design**, and it's difficult to estimate how well they'll work. Even with two cores in your laptop, that doesn't guarantee the computer will be **twice as fast**.

There are several reasons for this:

#### 5.3.1 Parallelism Management

Imagine you need to sort an array of 1,000 items.

**How do you divide this task between two cores?**

You give 500 items to each core to sort, and then both arrays need to be **merged back together** — and merging arrays takes time.

#### 5.3.2 Load Balancing

Suppose you have **ten tasks** that need to be executed, and each core receives five tasks.

- **Core A** receives simple tasks and finishes in **2 seconds**.
- **Core B** receives complex tasks and takes **1 minute**.

Core A sits idle for **58 seconds** while Core B does the heavy work.

---

## 6. MapReduce

There's a special type of parallel algorithm that's becoming very popular: **distributed algorithms**.

You can run a parallel algorithm on your laptop if you only need 2 to 4 cores. But what if you need **hundreds of cores**?

In these situations, you write the algorithm to run across **multiple machines**.

The **MapReduce** algorithm is one such algorithm and can be used in the **Apache Hadoop** framework.

### 6.1 Why Distributed Algorithms Are Useful

Imagine you have a hash table with **billions or trillions** of items, and you want to run a complex SQL query on it.

**This doesn't fit on a single machine.**

If each task takes 10 seconds, and you need to process **1 million tasks**, a single computer would take **months**.

**Solution?**  
Run the tasks across **multiple machines**, finishing everything in **days**.

### 6.2 How MapReduce Works

MapReduce works with two simple ideas:

- **Map function**
- **Reduce function**

### 6.3 The Map Function

The **map** function takes an array and applies the same function to each item.

**Example:**

```python
arr1 = [1, 2, 3, 4, 5]
arr2 = map(lambda x: 2 * x, arr1)
# Result: [2, 4, 6, 8, 10]
```

**Visual representation:**

```
[1] [2] [3] [4] [5]
 ↓   ↓   ↓   ↓   ↓
[2] [4] [6] [8] [10]
```

Now imagine that `map` downloads a list of URLs:

```python
arr1 = URLs
arr2 = map(download_page, arr1)
```

- **With 1,000 URLs:** hours.
- **With 100 machines:** minutes.

**This is MapReduce.**

### 6.4 The Reduce Function

**Map** transforms an array into another array.  
**Reduce** transforms an array into a **single value**.

**Visual example:**

```
[1] [2] [3] [4] [5]
  \   \   \   \   \
         [15]
```

**Code:**

```python
arr1 = [1, 2, 3, 4, 5]
reduce(lambda x, y: x + y, arr1)
# Result: 15
```

MapReduce **combines** these two functions to process massive amounts of data across multiple machines.

---

## 7. Bloom Filters and HyperLogLog

Imagine you work at **Reddit**.

Every time someone posts a link, you need to check if it's been posted before.

**Stories that have never been posted are more valuable.**

Or consider **Google**, wanting to know if a page has been crawled before.

Or **Bitly**, checking if a link points to a malicious place.

**All these problems are the same:**

➜ You need to check if an item belongs to a **gigantic set**.

### 7.1 The Problem

A hash table could solve this — but the hash would be **enormous**, because Google indexes **trillions of pages**.

### 7.2 Problem Illustration

**Set of pages:**

```
scribd.com
audit.io
xkcd.com
facebook.com
itch.io
instagram.com
```

**Hash table:**

```
facebook.com → YES
adit.io      → YES
itch.io      → YES
```

**Query:**

```
adit.io → YES
```

Great! But the hash would need to be **way too large**.

### 7.3 Bloom Filters

Bloom Filters offer a solution. They are **probabilistic data structures** that provide an answer that **may be wrong**, but will **probably be correct**.

Instead of asking a hash table, you can ask a Bloom filter if a URL has been crawled before. A hash table would give an **exact result**, but a Bloom filter will give a result that is **probably correct**.

**Key properties:**

- **False positives are possible.**  
  Google might say "You've already crawled this site" even if it's not true.

- **False negatives are NOT possible.**  
  If the Bloom filter says "You haven't crawled this site yet", then you **definitely** haven't crawled it.

Bloom Filters are great because they use **very little space**.

- A full hash table for each URL crawled by Google would be enormous.
- A Bloom filter uses **much less space** — but with less precision.

Bitly can use a Bloom filter to know if a link has been shortened without needing to store everything.

### 7.4 HyperLogLog

In the same style, there's another algorithm called **HyperLogLog**.

Imagine Google wants to count the number of **unique searches** performed by users.

Answering this exactly would require **a lot of space** (storing all distinct searches).

HyperLogLog **approximates** the number of unique elements in a set using only a **fraction of the memory**.

It:
- **doesn't provide an exact answer**, but
- **provides an excellent approximation**, using
- **very little space**.

If you have lots of data and can accept an approximate answer, HyperLogLog is ideal.

---

## 8. SHA Algorithms

Do you remember the hashing technique from Chapter 5?

Suppose you have a key and want to place an associated value in an array, using a hash function.

```
Array: [0] [1] [2] ... [25]
```

You use the hash function to find the slot where the value should be inserted, and place the value there.

```
"plums" → hash function → [slot 7]
```

This allows searching the array in **constant time (O(1))**.

**SHA**, however, is a type of **secure hash**, used to generate a **unique string** for any input.

### 8.1 Comparing Files

If you generate a SHA hash for a file and someone else generates a SHA hash for a different file, you can compare them without seeing the content:

```
"hello"     → 2cf24db...
"algorithm" → b1eb2ec...
"password"  → 5e88489...
```

SHA strings are longer; these are abbreviated.

### 8.2 Checking Passwords

When Gmail stores your password, it doesn't store the password itself, but the **SHA hash** of the password.

When you type your password:
1. The system calculates the hash
2. Compares it with the stored hash
3. If they're equal, the password is correct

**Example:**

```
"abc123" → "6ca13d..."
```

But the **reverse process is not possible**:  
Given "6ca13d", there's no way to discover what the original password was.

**This is why SHA is secure.**

### 8.3 Locally Sensitive Hash

SHA has a characteristic: it is **locally insensitive**.

If you change just one character:

```
"hand" → cd6357...
"head" → e392da...
```

They're **completely different**.

This is good for passwords, but bad when we want to measure **similarity** between strings.

For this, there's **Simhash**, which generates **similar hashes** for similar inputs.

**Uses of Simhash:**

- Google uses it to detect duplicate content on the web.
- Teachers can check for plagiarism.
- Scribd uses it to block uploads of protected books.

Simhash is useful when you want to verify **similarity**.

---

## 9. Diffie-Hellman Key Exchange

The Diffie-Hellman key exchange solves an old problem:

**How can two people agree on a secret without sending it directly?**

### 9.1 Example of Simple Cipher (Illustrative Only)

**Message:** 9, 6, 13, 13, 16  24, 16, 19, 13, 5

- Trying to decipher with a=1, b=2 → "ifmmp xpsme" (doesn't make sense)
- Trying with a=2, b=3 → "hello world" (it worked!)

Simple ciphers can be broken easily.

### 9.2 How Diffie-Hellman Solves It

Diffie-Hellman solves two problems:

1. The parties **don't need to agree on the cipher beforehand**.
2. The encrypted messages are **extremely difficult to decode**.

The exchange creates two keys:

- **Public key** (can be shared)
- **Private key** (kept secret)

A message encrypted with a public key can only be read by the corresponding private key.

---

## 10. Linear Programming

Linear programming is used to **maximize something** with **limited resources**.

### 10.1 Example 1 — T-Shirt and Bag Factory

**T-shirts:** 1 meter of fabric + 2 buttons → profit R$ 2  
**Bags:** 2 meters + 2 buttons → profit R$ 3

**Resources:** 11 meters of fabric, 20 buttons

**Question:** How many t-shirts and bags will maximize profit?

### 10.2 Example 2 — Political Marketing

- Want to maximize votes in SF and Chicago
- Each vote costs a certain amount
- Limited time (50 days)
- Budget: R$ 1,500

**Question:** What is the maximum number of votes?

Linear programming solves this type of optimization using the **Simplex algorithm**.

---

## 11. Epilogue

The author concludes by explaining that:

- This book showed only the **beginning**.
- The best way to learn is to find something that sparks your interest and **dive deep**.
- The 10 algorithms presented give a **solid foundation** to move forward.

---

## 12. Summary

This chapter introduced ten important algorithms and concepts not covered in detail earlier:

1. **Binary Search Trees:** Faster insertions/deletions than arrays, but require balancing.
2. **Inverted Indexes:** Core data structure for search engines.
3. **Fourier Transform:** Powerful tool for signal processing, compression, and analysis.
4. **Parallel Algorithms:** Running algorithms across multiple cores, with challenges in management and load balancing.
5. **MapReduce:** Distributed algorithm combining map and reduce functions for processing massive datasets.
6. **Bloom Filters:** Probabilistic data structures for membership testing with minimal memory (false positives possible, false negatives not).
7. **HyperLogLog:** Approximate counting of unique elements using very little space.
8. **SHA Algorithms:** Secure hashing for file comparison and password storage (one-way, locally insensitive).
9. **Simhash:** Locally sensitive hashing for detecting similarity and duplicate content.
10. **Diffie-Hellman Key Exchange:** Cryptographic protocol for secure key exchange without pre-sharing secrets.
11. **Linear Programming:** Optimization technique for maximizing outcomes with limited resources (Simplex algorithm).

These algorithms and data structures represent just the beginning of what's possible. The best way to learn is to find something that sparks your interest and dive deep. The foundation provided in this book gives you a solid base to explore further.

