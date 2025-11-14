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

## 5. Summary

This chapter introduced three important concepts:

1. **Binary Search Trees:** Faster insertions/deletions than arrays, but require balancing.
2. **Inverted Indexes:** Core data structure for search engines.
3. **Fourier Transform:** Powerful tool for signal processing, compression, and analysis.

These are just a few of the many algorithms and data structures worth exploring as you continue your learning journey.

