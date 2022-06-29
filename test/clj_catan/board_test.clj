(ns clj-catan.board-test
  (:require [clojure.test :refer :all]
            [clj-catan.board :as b]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.generators :as gen]))

;todo write inverse test for (place)

(defn place-inverse [map]
  )

;todo generate a hashmap with numerical values. keys can be strings or ints
(comment
  ;todo generate keys as a range of ints. try making a vector and converting to hashmap
  (gen/sample (gen/hash-map gen/nat gen/boolean :b gen/nat))

  )

; todo try generating a vector and converting to a hashmap?
(def gen-map (gen/hash-map "place" gen/nat))

(defspec place-inverse-test 100
  (prop/for-all [board-map gen-map]
   (let [board (b/place board-map (range 1 (reduce + 1 (vals board-map))) {})]
     (= false true))))

