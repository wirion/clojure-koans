(ns koans.14-recursion
  (:require [koan-engine.core :refer :all]))

(defn is-even? [n]
  (if (= n 0)
    true
    (not (is-even? (dec n)))))

(defn is-even-bigint? [n]
  (loop [n   n
         acc true]
    (if (= n 0)
      true
      (recur (dec n) (not acc)))))

(defn recursive-reverse [coll]
  (loop [new-list '()
         old-list coll]
    (if (empty? old-list)
      new-list
      (recur (cons (first old-list) new-list) (rest old-list)))))

;; (defn recursive-reverse-lame [coll]
;;   (if (empty? coll)
;;     coll
;;     (append (recursive-reverse (rest coll)) (first coll))))

;; (defn append [collection element]
;;   (seq (conj (vec collection) element)))

;; (defn factorial [n]
;;   (if (< n 2)
;;     n
;;     (*' n (factorial (dec n)))))

(defn factorial [n]
  (loop [n n
         acc 1]
    (if (< n 2)
      acc
      (recur (dec n) (*' acc n)))))

(meditations
  "Recursion ends with a base case"
  (= true (is-even? 0))

  "And starts by moving toward that base case"
  (= false (is-even? 1))

  "Having too many stack frames requires explicit tail calls with recur"
  (= false (is-even? 1N))

  "Reversing directions is easy when you have not gone far"
  (= '(1) (recursive-reverse [1]))

  "Yet it becomes more difficult the more steps you take"
  (= '(5 4 3 2 1) (recursive-reverse [1 2 3 4 5]))

  "Simple things may appear simple."
  (= 1 (factorial 1))

  "They may require other simple steps."
  (= 2 (factorial 2))

  "Sometimes a slightly bigger step is necessary"
  (= 6 (factorial 3))

  "And eventually you must think harder"
  (= 24 (factorial 4))

  "You can even deal with very large numbers"
  (< 1000000000000000000000000N (factorial 1000N))

  "But what happens when the machine limits you?"
  (< 1000000000000000000000000N (factorial 100003N)))
