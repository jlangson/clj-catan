(ns front-end.views)

(def color-classes
            {"ore"   "bg-gray-700"
             "wool" "bg-purple-800"
             "grain" "bg-yellow-500"
             "brick" "bg-yellow-900"
             "lumber"  "bg-green-700"})

; for repl. todo remove from prod
(def board-map
  {:harbors   {7 "generic", 1 "generic", 4 "wool", 6 "generic", 3 "brick", 2 "ore", 9 "grain", 5 "lumber", 8 "generic"},
   :rolls     {7 3, 1 8, 4 4, 15 2, 13 11, 6 nil, 17 10, 3 11, 12 5, 2 9, 19 6, 11 8, 9 6, 5 4, 14 3, 16 10, 10 5, 18 9, 8 12},
   :resources {7  "brick",
               1  "brick",
               4  "lumber",
               15 "grain",
               13 "lumber",
               6  "wool",
               17 "ore",
               3  "grain",
               12 "wool",
               2  "grain",
               11 "wool",
               9  "grain",
               5  "lumber",
               14 "ore",
               16 "brick",
               10 "ore",
               18 "lumber",
               8  "wool"}})
(def m board-map) ; for repl. todo remove from prod




;todo add desert
(defn map->grid [m]
  [:div.grid.grid-cols-5.gap-1
   (doall
     (for [i (as-> m $
               (get $ :rolls)
               (count $)
               (inc $)
               (range 1 $))
           :let [get-tile-values (fn [m n] (str (get-in m [:resources n]) " roll=" (get-in m [:rolls n])))
                 get-tile-css (fn [m n] (color-classes (str (get-in m [:resources n]))))]]
       [:div.text-white.text-center.p-2
        {:class (get-tile-css m i)}
        (get-tile-values m i)]))])


(comment


  (def board-map
    {:harbors   {7 "generic", 1 "generic", 4 "wool", 6 "generic", 3 "brick", 2 "ore", 9 "grain", 5 "lumber", 8 "generic"},
     :rolls     {7 3, 1 8, 4 4, 15 2, 13 11, 6 nil, 17 10, 3 11, 12 5, 2 9, 19 6, 11 8, 9 6, 5 4, 14 3, 16 10, 10 5, 18 9, 8 12},
     :resources {7  "brick",
                 1  "brick",
                 4  "lumber",
                 15 "grain",
                 13 "lumber",
                 6  "wool",
                 17 "ore",
                 3  "grain",
                 12 "wool",
                 2  "grain",
                 11 "wool",
                 9  "grain",
                 5  "lumber",
                 14 "ore",
                 16 "brick",
                 10 "ore",
                 18 "lumber",
                 8  "wool"}})
  (def m board-map)



  )

(comment
  [:canvas {:width  500
                   :height 500
                   :id     "cv"
                   :style  {:border "1px solid black" :display "block"}}]
  [:button {:on-click #(re-frame.core/dispatch [:draw-grid (.getElementById js/document "Draw grid")])}])
