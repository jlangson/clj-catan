(ns front-end.views)

(def colors {"ore" "black"
             "sheep" "blue"                                 ;blue so it is not confused with wood. todo choose better colors
             "wheat" "yellow"
             "brick" "brown"
             "wood" "green"})

(defn grid []
  [:h2 "this is a grid"])

(comment
  [:canvas {:width  500
            :height 500
            :id     "cv"
            :style  {:border "1px solid black" :display "block"}}]
  [:button {:on-click #(re-frame.core/dispatch [:draw-grid (.getElementById js/document "Draw grid")])}]
  )
