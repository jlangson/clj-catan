(ns clj-catan.board)

(def resources {"forest" 4
                "brick"  3
                "wheat"  4
                "sheep"  4
                "ore"    3})

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
  [item-quantity locations output]
  (if (empty? locations)
    output
    (let [location (rand-nth locations)
          new-locations (remove #{location} locations)
          item (rand-nth (keys item-quantity))
          new-quantity (dec (item-quantity item))]
      (if
        (= 0 new-quantity)
        (place (dissoc item-quantity item) new-locations (assoc output location item))
        (place (assoc item-quantity item new-quantity) new-locations (assoc output location item))))))

(defn remove-neighbors-and-self
  "Give a location and a map of neighbors, removes all the keys that are adjacent to that location"
  [location neighbors]
  (let [adjacents (neighbors location)]
    (apply dissoc neighbors (conj adjacents location))))


(def place-6-8
  "Seeds the board by placing the sixes and eights on non-adjacent tiles"
  [])

(defn setup-board []
  {:harbors   (place harbors (range 1 10) {})               ; sum of values of harbors is 9
   :rolls     (place rolls (range 1 19) {})
   :resources (place resources (range 1 20) {})}) ;higher range b/c desert

(comment

  )