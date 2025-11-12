# Chapter 10: K-Nearest Neighbors — Grokking Algorithms

## 1. Chapter Overview

In this chapter you will:
- Build a simple classifier using the **k-nearest neighbors (KNN)** algorithm.
- See how to perform **feature extraction** so data points can be compared.
- Understand **regression** with KNN (predicting a number such as a stock price or a movie rating).
- Learn when KNN is a good fit and where it falls short.

## 2. Classifying Fruit: Orange vs Grapefruit

We want to decide whether an unknown citrus fruit is an **orange** or a **grapefruit**. Grapefruits tend to be larger and redder; oranges are smaller and more orange.

Visual mental model: two axes — **color** and **size**.

```
        COLOR ↑ (orange → red)

 red     |                 T T T
         |            T T  T
 orange  | L L L L
         +--------------------------→ SIZE (small → large)

Legend: L = known orange, T = known grapefruit
```

### 2.1 Classifying a Mystery Fruit

We observe an unknown fruit (marked `?`). It is fairly large and somewhat red.

```
        COLOR ↑

 red     |             ?
         |         T T T
 orange  | L L L L
         +--------------------------→ SIZE
```

### 2.2 Using KNN (k = 3)

Pick the **k nearest neighbors**. With `k = 3`, we look at the three closest known fruits.

```
        COLOR ↑

 red     |         T T T
         |      (nearest neighbors)
         |          ?   ← mystery fruit
 orange  | L L L L
         +--------------------------→ SIZE
```

The majority label among the three nearest neighbors is **orange** ➜ classify the mystery fruit as an orange.

**Algorithm steps (classification version):**
1. Represent each example as a point in feature space (size, color, …).
2. Given a new point, compute its distance to every labeled point.
3. Take the majority label among the `k` closest points.
4. Assign that label to the new point.

KNN is simple but very effective for many problems; it is a good baseline whenever you need a classifier.

## 3. Building a Recommendation System (Collaborative Filtering)

Suppose you run Netflix and want to recommend movies. Treat each user as a point in feature space (features could be genre preferences, historical ratings, etc.). Users with similar tastes are near each other.

```
          (similar users cluster together in the space)
        •   •
      •  Priyanka  •
    •   •   •
```

To recommend movies to Priyanka, find her `k` nearest neighbors (say `k = 5`): Justin, JC, Joey, Lance, Chris share similar taste. Movies they liked are good candidates for Priyanka.

**Recommendation workflow with KNN:**
1. Find `k` nearest users to the target user.
2. Gather items (movies) those neighbors rated highly.
3. Recommend the top candidates to the target user.

This is the essence of user-based collaborative filtering using KNN.

---

*(More sections on feature extraction, distance metrics, regression, etc., will build on this foundation.)*

