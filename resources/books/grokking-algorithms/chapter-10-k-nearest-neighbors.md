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

---

## 4. Feature Extraction

In the grapefruit/orange example we measured two features: **size** and **redness**. Feature extraction is the process of turning real-world objects into vectors of numbers so distances can be computed.

### 4.1 Fruit Example

Suppose we have three labeled fruits:

- Fruit A: size = 2, redness = 2  
- Fruit B: size = 2, redness = 1  
- Fruit C: size = 4, redness = 5

Plotting (size on X, redness on Y):

```
redness ↑
        |
    5   |            C
        |
    2   |    A
    1   |    B
        +--------------------------→ size
             2           4
```

Visually A and B look close. To quantify that, use the Euclidean distance:

```
distance(A, B) = √[(2-2)² + (2-1)²] = √1 = 1
```

### 4.2 User Vectors for Recommendations

Each Netflix user is represented as a vector of preference scores.

Example categories: Comedy, Action, Drama, Horror, Romance.

| User     | Comedy | Action | Drama | Horror | Romance |
|----------|--------|--------|-------|--------|---------|
| Priyanka |   3    |   4    |  4    |   1    |   4     |
| Justin   |   4    |   5    |  5    |   1    |   5     |
| Morpheus |   2    |   2    |  3    |   1    |   3     |

Vectors:
- Priyanka → (3, 4, 4, 1, 4)
- Justin   → (4, 5, 5, 1, 5)
- Morpheus → (2, 2, 3, 1, 3)

Euclidean distance in 5D:

```
distance(Priyanka, Justin) =
  √[(3-4)² + (4-5)² + (4-5)² + (1-1)² + (4-5)²]
  = √(1+1+1+0+1) = √4 = 2

distance(Priyanka, Morpheus) =
  √[(3-2)² + (4-2)² + (4-3)² + (1-1)² + (4-3)²]
  = √(1+4+1+0+1) = √7 ≈ 2.65
```

Priyanka is closer to Justin than to Morpheus → recommend movies Justin liked.

> The more ratings a user provides, the more reliable their vector and distances become.

### 4.3 Exercises

**Exercise 10.1**  
Yogi rates movies on a 1–10 scale while Pinky uses 1–5. How do we compare them?

> **Answer:** Normalize the vectors before computing distances (e.g., divide Yogi’s ratings by 2 so both users are on 0–5). In general apply min–max or z-score normalization so all features share the same scale.

**Exercise 10.2**  
Suppose influencers should count more heavily.

> **Answer:** Use **weighted KNN**: multiply distances (or votes) by a weight factor. For example, define `weight = influencer_weight` and either (a) scale their feature vector contribution or (b) use `distance = √Σ w_i (x_i - y_i)²` where important users/features get larger weights. Voting can also be weighted inversely to distance times influencer weight.

---

## 5. Regression with KNN

KNN can predict numeric values (regression) by averaging neighbor labels.

### 5.1 Movie Rating Prediction

To predict Priyanka’s rating for a new movie, average the ratings of her nearest neighbors:

| Neighbor | Rating |
|----------|--------|
| Justin   |   5    |
| JC       |   4    |
| Joey     |   4    |
| Lance    |   5    |
| Chris    |   3    |

Average = (5 + 4 + 4 + 5 + 3) / 5 = 4.2 → predict Priyanka will rate it ≈ 4.2 stars.

### 5.2 Bakery Demand Example

Features:  
- Weather (1–5)  
- Weekend? (1=yes, 0=no)  
- Game today? (1=yes, 0=no)

Historical data:

| Day | (Weather, Weekend, Game) | Breads sold |
|-----|--------------------------|-------------|
| A   | (5, 1, 0)                | 300         |
| B   | (3, 1, 1)                | 225         |
| C   | (1, 1, 0)                | 75          |
| D   | (4, 1, 1)                | 200         |
| E   | (4, 0, 0)                | 150         |
| F   | (2, 0, 0)                | 50          |

Today’s features: (4, 1, 0).  
Find the 4 nearest neighbors → A, B, D, E.  
Average = (300 + 225 + 200 + 150) / 4 = 218.75 → bake about 219 breads.

---

## 6. When Euclidean Distance Isn’t Enough (Cosine Similarity)

Sometimes magnitude should not matter, only direction. **Cosine similarity** measures the angle between vectors — useful when comparing patterns regardless of scale (e.g., user rating patterns).

Cosine similarity formula:

```
cos_sim(a, b) = (a · b) / (||a|| ||b||)
```

Values range from -1 (opposite) to 1 (identical direction). For recommendation tasks it’s common to use cosine similarity instead of distance.

---

## 7. Choosing Good Features

Success hinges on selecting relevant, unbiased features correlated with the target:
- **Relevant:** Should actually affect the classification/regression outcome.
- **Unbiased:** Avoid introducing unfairness (e.g., using gender for tasks where it shouldn’t matter).
- **Correlated:** Features must change meaningfully across classes.

Bad example: deciding movie recommendations using cat pictures from user profiles.  
Good example: genre ratings, watch history, demographic features when appropriate.

There is rarely a perfect answer; good feature engineering often requires domain knowledge.

---

## 8. Exercise 10.3

Netflix has millions of users, and we picked `k = 5` neighbors. Is `k = 5` too low or too high?

> **Answer:** There is no fixed best `k`. You typically tune `k` via validation: try multiple values (e.g., 1, 3, 5, 10, 20) and evaluate accuracy or recommendation quality. Small `k` may overfit/noisy; large `k` may blur distinctions. Use cross-validation/hold-out sets to pick the `k` that performs best. With millions of users, you can test various `k` efficiently.

---

## 9. Introduction to Machine Learning

KNN is an entry point to machine learning: we train on data, then generalize. More examples:

### 9.1 Optical Character Recognition (OCR)

- OCR converts text images into machine-readable characters.
- Training: gather many labeled images of digits/letters → extract features (edges, corners, loops).
- Prediction: extract features from a new glyph, compare to training examples via KNN (or more advanced models).

Example feature sketches:

```
Digit "7":                      Digit "3":

████████                         ███████
     ██                              ██
    ██                             ████
   ██                                ██
   ██                            █████

Captured features → stroke counts, angles, curve presence, intersections.
```

Even complex systems like OCR, speech recognition, face recognition start from basic ideas such as feature extraction + distance.

### 9.2 Training Phase

The process of scanning existing labeled items, extracting features, and storing them is the **training** phase. Most ML algorithms require training before making predictions.

### 9.3 Spam Filtering with Naive Bayes

- Train on sample emails (spam vs ham).
- For each word compute probabilities `P(word | spam)` and `P(word | ham)`.
- For a new subject like “You won ten million dollars!” compute likelihood that it is spam using Bayes’ theorem.

Training examples:

```
Subject line                             Label
-----------------------------------------------
"Update your password!"                  Not spam
"You won 1 million reais!"               Spam
"Send me your password"                  Spam
"Nigerian prince sent 10 million reais!" Spam
"Happy birthday!"                        Not spam
```

Tokenize the incoming subject, look up each word’s spam/ham likelihood, combine via Bayes.

Naive Bayes is another simple, effective classifier. It can also classify fruits given size/color by modelling probabilities per feature.

### 9.4 Predicting the Stock Market

- Feature extraction is hard; the market has too many variables and noise.
- Naive rules like “if stock goes up yesterday, it goes up today” rarely hold reliably.
- Forecasting chaotic systems is extremely difficult; ML has limitations and can’t guarantee success.

Illustrative volatility:

```
Day:    Mon  Tue  Wed  Thu  Fri
Price:  50   53   51   56   52
Rule "up yesterday ⇒ up today" fails on Wed & Fri.
```

### 9.5 Recap of ML Concepts

- KNN works for classification and regression.
- Feature extraction: convert items to numeric vectors.
- Choosing relevant, unbiased, correlated features is critical.
- Training: feed labeled examples to the model so it can generalize.
- ML has broad applications (recommendations, OCR, spam filtering, etc.) but also limits (e.g., stock prediction).
