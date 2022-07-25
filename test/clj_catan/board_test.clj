(ns clj-catan.board-test
  (:require [clojure.test :refer :all]
            [clj-catan.board :as b]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]
            [clojure.set :as set]))

(defn count-reversed-keys
  "Takes a map, reverses kv and changes values to the frequencies of the former keys
  ex: {1 :a
       2 :a
       3 :b
       2 :b
       18 :c}
       ->
       {:a 2
       :b 2
       :c 1}"
  [m]
  (->> m
    (into [])
    (map second)
    frequencies))

; 100 runs causes stack overflow
(defspec place-inverse-test 75
  (prop/for-all [board-v (gen/not-empty (gen/vector (gen/vector (gen/fmap inc gen/nat) 2 2)))]
    (let [board-m (apply assoc {} (flatten board-v))
          board (b/place board-m (b/count-locations board-m) {})]
      (= board-m (count-reversed-keys board)))))

(deftest remove-neighbors-and-self
  (let [neigbhors {1  [2 3 :a]
                   2  [4 5 6]
                   3  [7 8 9]
                   :a [5 6 7]
                   :b [12 13 11]}
        expected {:b [12 13 11]}]
    (is (= expected (b/remove-neighbors-and-self 1 neigbhors)))))

(defn adjacent?
  "Determines if any of the locations are adjacent to each other
  by comparing if any locations are in the values of the map
  [1 2 3]
  {1 [2 3 4]
   2 [1 3 4]
   3 [1 4 5]}
   => true

   [1 19]
   {1 [1 2 3]
   19 [4 5 7]}
   => false"
  [locations-v neighbors-m]
  (let [loc-set (set locations-v)
        neighbors-set (set (flatten (map neighbors-m locations-v)))]
    (seq (set/intersection loc-set neighbors-set))))

; call b/place-6-8 100 times
; make sure none of the tiles are adjacent
(deftest place-6-8-test
  (dotimes [i 100]
    (is (not (adjacent? (keys (b/place-6-8)) b/neighbors)))))

(comment
  (for [loc [1 2 19]]
    (println (b/neighbors loc))
    (conj (b/neighbors loc))
    )
  (set (flatten (map b/neighbors [1 2 3])))
  (map set (vals b/neighbors))
  (apply assoc {} flatten [[6 6] [9 7] [7 7] [9 0] [4 1] [5 1] [2 3]])
  ;generate the vector
  (gen/sample (gen/vector (gen/vector gen/nat 2 2)))
  (gen/sample (gen/vector gen/nat 2 2))
  ;vector -> hashmap
  (apply assoc {} (flatten (gen/sample (gen/vector gen/nat 2 2))))

  (count-reversed-keys (apply assoc {} (flatten (gen/sample (gen/vector gen/nat 2 2)))))

  (frequencies (map second (into [] (:harbors (b/setup-board)))))
  ;an outline of count-reversed keys
  (->> (b/setup-board)
    (:harbors)
    (into [])
    (map second)
    (frequencies))

  (count-reversed-keys (:harbors (b/setup-board)))
  (count-reversed-keys (:rolls (b/setup-board)))
  (count-reversed-keys (:resources (b/setup-board)))
  )