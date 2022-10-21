(ns front-end.views)

(def color-classes
            {"ore"   "bg-gray-700"
             "sheep" "bg-purple-800"
             "wheat" "bg-yellow-500"
             "brick" "bg-yellow-900"
             "wood"  "bg-green-700"})

; for repl. todo remove from prod
(def board-map
  {:harbors   {7 "generic", 1 "generic", 4 "sheep", 6 "generic", 3 "brick", 2 "ore", 9 "wheat", 5 "wood", 8 "generic"},
   :rolls     {7 3, 1 8, 4 4, 15 2, 13 11, 6 nil, 17 10, 3 11, 12 5, 2 9, 19 6, 11 8, 9 6, 5 4, 14 3, 16 10, 10 5, 18 9, 8 12},
   :resources {7  "brick",
               1  "brick",
               4  "forest",
               15 "wheat",
               13 "forest",
               6  "sheep",
               17 "ore",
               3  "wheat",
               12 "sheep",
               2  "wheat",
               11 "sheep",
               9  "wheat",
               5  "forest",
               14 "ore",
               16 "brick",
               10 "ore",
               18 "forest",
               8  "sheep"}})
(def m board-map)



(defn map->grid [m]
  (let [get-tile-values (fn [m n] (str (get-in m [:resources n]) " " (get-in m [:rolls n])))
        get-tile-css (fn [m n] (color-classes (str (get-in m [:resources n]))))]
    (def get-tile-values get-tile-values)                   ;for repl. todo remove from prod
    (def get-tile-css get-tile-css)
    [:div.grid.grid-cols-5.gap-1
     [:div.text-white.text-center.p-2
      {:class (get-tile-css m 1)}
      (get-tile-values m 1)]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]
     [:div.text-white.text-center.p-2]]))


(comment


  (def board-map
    {:harbors   {7 "generic", 1 "generic", 4 "sheep", 6 "generic", 3 "brick", 2 "ore", 9 "wheat", 5 "wood", 8 "generic"},
     :rolls     {7 3, 1 8, 4 4, 15 2, 13 11, 6 nil, 17 10, 3 11, 12 5, 2 9, 19 6, 11 8, 9 6, 5 4, 14 3, 16 10, 10 5, 18 9, 8 12},
     :resources {7  "brick",
                 1  "brick",
                 4  "forest",
                 15 "wheat",
                 13 "forest",
                 6  "sheep",
                 17 "ore",
                 3  "wheat",
                 12 "sheep",
                 2  "wheat",
                 11 "sheep",
                 9  "wheat",
                 5  "forest",
                 14 "ore",
                 16 "brick",
                 10 "ore",
                 18 "forest",
                 8  "sheep"}})
  (def m board-map)



  )

(comment
  [:canvas {:width  500
                   :height 500
                   :id     "cv"
                   :style  {:border "1px solid black" :display "block"}}]
  [:button {:on-click #(re-frame.core/dispatch [:draw-grid (.getElementById js/document "Draw grid")])}])
