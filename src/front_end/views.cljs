(ns front-end.views)

(def color-classes
            {"ore"   "bg-gray-700"
             "sheep" "bg-purple-800"
             "wheat" "bg-yellow-500"
             "brick" "bg-yellow-900"
             "wood"  "bg-green-700"})

(defn map->grid [m]
  [:div.grid.grid-cols-5.gap-1
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
   [:div.text-white.text-center.p-2]
   [:div.text-white.text-center.p-2]])


(comment
  [:canvas {:width  500
            :height 500
            :id     "cv"
            :style  {:border "1px solid black" :display "block"}}]
  [:button {:on-click #(re-frame.core/dispatch [:draw-grid (.getElementById js/document "Draw grid")])}]
  )
