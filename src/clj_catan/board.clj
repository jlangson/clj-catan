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

(def harbors {"bricK"   1
              "generic" 4
              "sheep"   1
              "ore"   1
              "wheat"   1
              "wood"    1})

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

(defn setup-board []
  {:harbors   (place harbors (range 1 10) {})               ; sum of values of harbors is 9
   :rolls     (place rolls (range 1 19) {})
   :resources (place resources (range 1 20) {})}) ;higher range b/c desert

(comment
  (for [kv rolls]
    (reduce + 0 (second kv)))

  (for [kv rolls]
    (let [values (second kv)]
      (println (type values))
      (->> values
           vector
           (apply +))))

  (for [kv (into [] rolls)]
    (apply + (second )))
  )