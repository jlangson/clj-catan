(ns clj-catan.board-test
  (:require [clojure.test :refer :all]
            [clj-catan.board :as b]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]))

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
  [hash-map]
  (->> hash-map
    (into [])
    (map second)
    (frequencies)))

(defspec place-inverse-test 100
  (prop/for-all [board-vector (gen/vector (gen/vector gen/nat 2 2))]

   (let [board-map (apply assoc {} (flatten board-vector))
         board (b/place board-map (b/count-locations board-map) {})]
     (= board-map false))))

(comment
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
