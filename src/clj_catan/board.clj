(ns clj-catan.board)

(def resources {"forest" 4
                "brick" 3
                "wheat" 4
                "sheep" 4
                "ore" 3})

(def rolls {6 2
            8 2
            2 1
            3 2
            4 2
            5 2
            9 2
            10 2
            11 2
            12 1})

(def harbors {"bricK" 1
              "generic" 4
              "sheep" 1
              "stone "1
              "wheat" 1
              "wood" 1})



(defn place
  "Takes a kvs and a number of locations. Puts the kvs into those many locations, randomly"
  ([map]
  (place [map (range 1 19)]))                               ;19th slot is the desert
  ([map locations]
   ))

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