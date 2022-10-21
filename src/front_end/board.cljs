(ns front-end.board)

; the keys are the type
; the values in the hashmap are the quantity
(def resources {"lumber" 4
                "brick"  3
                "grain"  4
                "wool"  4
                "ore"    3})

; the keys are the dice rolls that go on tiles.
; there is no 7 b/c 7 is not placed on any tiles
; the values are the quantity of each dice roll that goes on the board
; although 6 and 8 are valid rolls, they are special rolls that are handled by place-6-8
(def rolls {2  1
            3  2
            4  2
            5  2
            ;6  2
            ;8  2
            9  2
            10 2
            11 2
            12 1})

; the keys are the type
; the values are the quantity
(def harbors {"brick"   1
              "generic" 4
              "wool"   1
              "ore"     1
              "grain"   1
              "lumber"    1})

; the keys are the location
; the value are the adjacent tiles
; see "doc/catan overview.odg or doc/catan overview.jpeg" for a picture of the tiles
(def neighbors {1 [2 4 5]
                2 [1 3 5 6 ]
                3 [2 6 7]
                4 [1 5 8 9]
                5 [1 2 4 6 9 10]
                6 [2 3 5 7 10 11]
                7 [3 6 11 12]
                8 [4 9 13]
                9 [4 5 8 10 13 14]
                10 [5 6 9 11 14 15]
                11 [6 7 10 12 15 16]
                12 [7 11 16]
                13 [8 9 14 17]
                14 [9 10 13 15 17 18]
                15 [10 11 14 16 18 19]
                16 [11 12 15 19]
                17 [13 14 18]
                18 [14 15 17 19]
                19 [15 16 18]})
(defn place
  "Takes game assets and assigns it a location on the board
  kv is a map of the asset and the quantity of that asset."
  ([kv locations]
   (place kv locations {}))
  ([kv locations output]
   (if (empty? locations)
     output
     (let [location (rand-nth locations)
           new-locations (remove #{location} locations)
           item (rand-nth (keys kv))
           new-quantity (if (nil? item)
                          0
                          (dec (kv item)))]
       (if
         (= 0 new-quantity)
         (place (dissoc kv item) new-locations (assoc output location item))
         (place (assoc kv item new-quantity) new-locations (assoc output location item)))))))

(defn remove-neighbors-and-self
  "Give a location and a map of neighbors, removes all the keys that are adjacent to that location"
  [location neighbors]
  (let [adjacents (neighbors location)]
    (apply dissoc neighbors (conj adjacents location))))

(defn place-6-8
  "Seeds the board by placing the 6's and 8's on non-adjacent tiles
  Boards where a 6 or an 8 is adjacent to another 6 or 8 are illegal"
  ([]
   (place-6-8 [6 6 8 8] neighbors {}))
  ([six-eight-v neighbors  output]
   (if (empty? six-eight-v)
     output
     (let [location (rand-nth (keys neighbors))
           new-locations (remove-neighbors-and-self location neighbors)]
       (place-6-8 (pop six-eight-v) new-locations (conj output {location (peek six-eight-v)}))))))

(defn count-locations [locations]
  (range 1 (reduce + 1 (vals locations))))

(defn setup-board []
  (let [p6-8s (place-6-8)
        locations-without-6-8s (keys (apply dissoc neighbors (keys p6-8s)))]
    {:harbors   (into (sorted-map) (place harbors (count-locations harbors)))
     :rolls     (into (sorted-map) (merge p6-8s (place rolls locations-without-6-8s)))
     :resources (into (sorted-map) (place resources (count-locations resources)))})) ;higher range b/c desert

(comment
  (require '[flow-storm.api :as fs-api])
  (fs-api/local-connect)
  (fs-api/stop)

  (apply dissoc neighbors (keys {19 8, 7 8, 9 6, 1 6}))
  )
