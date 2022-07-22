(ns clj-catan.board-test
  (:require [clojure.test :refer :all]
            [clj-catan.board :as b]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]
            [taoensso.tufte :as tufte :refer (defnp p profiled profile)]))

(tufte/add-basic-println-handler! {})

(defn v->hm [v]
  (apply assoc {} (flatten v)))

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
  (profile
    {} (p ::count-reversed-keys (->> m
                                  (into [])
                                  (map second)
                                  frequencies))))

(defspec place-inverse-test 75
  (prop/for-all [board-v (gen/not-empty (gen/vector (gen/vector gen/nat 2 2)))]
    (let [board-m (v->hm board-v)
          board (b/place board-m (b/count-locations board-m) {})]
      (= true))))

;todo write generator
;randomly select a key
;remove key
;see if maps are equal
(defspec remove-neighbors-and-self-function-test 100
  (prop/for-all [v (gen/not-empty (gen/vector (gen/vector gen/small-integer 2 100)))]
    (let [m (v->hm v)
          rand-k (rand-nth (keys m))]
      (= (b/remove-neighbors-and-self m rand-k) (remove rand-k m)))))

(comment


  (gen/sample
    (gen/not-empty (gen/vector (gen/vector gen/int 2 100))))
  (gen/sample
    (gen/not-empty (gen/vector (gen/vector gen/keyword 2))))
  (gen/sample
    (gen/not-empty (gen/hash-map :a (gen/vector gen/nat))))
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
