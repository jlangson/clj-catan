(ns clj-catan.board)

(def resources {"forest" 4
                "brick"  3
                "wheat"  4
                "sheep"  4
                "ore"    3})

; the keys in the hashmap are the type
; the values in the hashmap are the quantity
(def rolls {6  2
            8  2
            2  1
            3  2
            4  2
            5  2
            9  2
            10 2
            11 2
            12 1})

(def harbors {"brick"   1
              "generic" 4
              "sheep"   1
              "ore"     1
              "wheat"   1
              "wood"    1})

(def neighbors {1 [2 3 4 5 6 7]
                2 [1 3 7 8 9 10]
                3 [1 2 4 10 11 12]
                4 [1 3 5 12 13 14]
                5 [1 4 6 14 15 16]
                6 [1 5 7 16 17 18]
                7 [1 2 6 8 18 19]
                8 [2 7 8 18]
                9 [2 8 10]
                10 [2 3 9 11]
                11 [3 10 12]
                12 [3 4 11 13]
                13 [4 12 14]
                14 [4 5 13 15]
                15 [5 14 16]
                16 [5 6 15 17]
                17 [6 16 18]
                18 [6 7 17 19]
                19 [7 8 18]})

(defn place
  ;todo improve comment

  "Call this with a map, the neighbors keys or a range and a {}
  item-quantity is
  locations is
  output is"
  [item-quantity locations output]
  ;(println (format "item-quantity ======> %s" item-quantity))
  ;(println (format "locations ======> %s" locations))
  ;(println (format "output ======> %s" output))
  (println 1)
  (if (empty? locations)
    (do (println 2)
        output)
    (do (println 3)
        (let [location (rand-nth locations)
              _ (println 4)
              new-locations (remove #{location} locations)
              _ (println 5)
              item (rand-nth (keys item-quantity))
              _ (println 6)
              new-quantity (do (println (format "item ======> %s" item)) (if (nil? item)
                                 (do (println 7) 0)
                                 (dec (item-quantity item))))] ;ok
          (if
            (= 0 new-quantity)
            (do (println 9) (place (dissoc item-quantity item) new-locations (assoc output location item)))
            (do (println 10) (place (assoc item-quantity item new-quantity) new-locations (assoc output location item)))))))) ;todo this is the line

(defn remove-neighbors-and-self
  "Give a location and a map of neighbors, removes all the keys that are adjacent to that location"
  [location neighbors]
  (let [adjacents (neighbors location)]
    (apply dissoc neighbors (conj adjacents location))))


(defn place-6-8
  "Seeds the board by placing the sixes and eights on non-adjacent tiles"
  ([]
   (place-6-8 [6 6 8 8] neighbors (keys neighbors) {}))
  ([six-eight neighbors locations output]
   ;pick a random location
   ;place it there
   ;remove neighbors
   ;return when six-eight is empty
   (println (format "six-eight ======> %s" six-eight))
   (println (format "neighbors ======> %s" neighbors))
   (println (format "locations ======> %s" locations))
   (println (format "output ======> %s" output))
   (if (empty? six-eight)
     output
     (let [location (rand-nth locations)
           new-locations (remove-neighbors-and-self location locations)]
       (println "inside let")
       (place-6-8 (pop six-eight) neighbors new-locations (conj output {location (peek six-eight)}))))))

(defn count-locations [locations]
  (range 1 (reduce + 1 (vals locations))))

;todo switch to sorted maps?
(defn setup-board []
  {:harbors   (place harbors (count-locations harbors) {})               ; sum of values of harbors is 9
   ;todo make sure rolls use place-6-8
   :rolls     (place rolls (count-locations rolls) {})                 ;todo I think the bug has something to do with the values being numbers.
   :resources (place resources (count-locations resources) {})}) ;higher range b/c desert ;TODO change nil to desert

(defn arity
  ([] "hello")
  ([name] "hello jim")
  ([name name2] "hello nancy"))