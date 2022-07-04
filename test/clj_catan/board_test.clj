(ns clj-catan.board-test
  (:require [clojure.test :refer :all]
            [clj-catan.board :as b]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]))

(comment
  ;generate the vector
  (gen/sample (gen/vector (gen/vector gen/nat 2 2)))
  (gen/sample (gen/vector gen/nat 2 2))
  ;vector -> hashmap
  (apply assoc {} (flatten (gen/sample (gen/vector gen/nat 2 2))))

  (map reverse (into [] (:harbors (b/setup-board))))
  (as-> (b/setup-board) b
    (:harbors b)
    (into [] b)
    (map reverse b))

  (->> (b/setup-board)
    (:harbors)
    (into [])
    (map reverse))
  )

(defn count-reversed-keys
  ;todo wrong description. look at the hashmaps and think more clearly.
  ; it is about the count, not the sum
  "Takes a map, reverses kv and changes values to the count of the former keys
  ex: {1 :a
       2 :a
       3 :b
       2 :b
       18 :c}
       ->
       {:a 2
       :b 2
       :c 1"
  [hash-map])

(defspec place-inverse-test 100
  (prop/for-all [board-vector (gen/vector (gen/vector gen/nat 2 2))]

   (let [board-map (apply assoc {} (flatten board-vector))
         board (b/place board-map (b/count-locations board-map) {})]
     (= board-map false))))

