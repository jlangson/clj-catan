(ns clj-catan.board)

; the keys in the hashmap are the type
; the values in the hashmap are the quantity
(def resources {"forest" 4
                "brick"  3
                "wheat"  4
                "sheep"  4
                "ore"    3})

; the keys in the hashmap are the dice rolls that go on tiles.
; there is no 7 b/c 7 is not placed on any tiles
; the values in the hashmap are the quantity of each dice roll that goes on the board
(def rolls {2  1
            3  2
            4  2
            5  2
            6  2
            8  2
            9  2
            10 2
            11 2
            12 1})

; the keys in the hashmap are the type
; the values in the hashmap are the quantity
(def harbors {"brick"   1
              "generic" 4
              "sheep"   1
              "ore"     1
              "wheat"   1
              "wood"    1})

; the keys in the hashmap is the location
; the value in the hashmap are the adjacent tiles
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
  (if (empty? locations)
    output
    (let [location (rand-nth locations)
          new-locations (remove #{location} locations)
          item (rand-nth (keys item-quantity))
          new-quantity (if (nil? item)
                         0
                         (dec (item-quantity item)))]
      (if
        (= 0 new-quantity)
        (place (dissoc item-quantity item) new-locations (assoc output location item))
        (place (assoc item-quantity item new-quantity) new-locations (assoc output location item))))))

(defn remove-neighbors-and-self
  "Give a location and a map of neighbors, removes all the keys that are adjacent to that location"
  [location neighbors]
  (let [adjacents (neighbors location)]
    (apply dissoc neighbors (conj adjacents location))))


(defn place-6-8
  "Seeds the board by placing the sixes and eights on non-adjacent tiles"
  ([]
   (place-6-8 [6 6 8 8] neighbors {}))
  ([six-eight-v neighbors  output]
   (if (empty? six-eight-v)
     output
     (let [location (rand-nth (keys neighbors))
           new-locations (remove-neighbors-and-self location neighbors)]
       (place-6-8 (pop six-eight-v) neighbors (conj output {location (peek six-eight-v)}))))))

;(defn place-6-8-two
;  ([]
;   (place-6-8-two [6 6 8 8] neighbors (keys neighbors) {}))
;  ([six-eight-v neighbors locations output]
;   (if (empty? six-eight)
;     output
;     (let [location (rand-nth locations)
;           new-locations (remove-neighbors-and-self location locations)]
;       (place-6-8-two (rest six-eight-v) neighbors new-locations)))))

(defn count-locations [locations]
  (range 1 (reduce + 1 (vals locations))))

;todo switch to sorted maps?
(defn setup-board []
  {:harbors   (place harbors (count-locations harbors) {})               ; sum of values of harbors is 9
   ;todo make sure rolls use place-6-8
   :rolls     (place rolls (count-locations rolls) {})                 ;todo I think the bug has something to do with the values being numbers.
   :resources (place resources (count-locations resources) {})}) ;higher range b/c desert ;TODO change nil to desert