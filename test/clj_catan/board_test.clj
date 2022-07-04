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

  )

; todo try generating a vector and converting to a hashmap?
(def gen-map (gen/hash-map "place" gen/nat))

(defspec place-inverse-test 100
  (prop/for-all [board-vector (gen/vector (gen/vector gen/nat 2 2))]

   (let [board-map (apply assoc {} (flatten board-vector))
         board (b/place board-map (b/count-locations board-map) {})]
     (= board-map false))))

