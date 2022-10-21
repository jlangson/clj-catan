(ns app.main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [front-end.board :as b]
            [front-end.views :as views]))

(def board (r/atom (b/setup-board)))

(defn grid-proto []
  [:div.border.border-purple-400
   [:h2.text-2xl.my-2 "board grid prototype"]
   [:div.grid.grid-cols-5.space-x-1.space-y-1
    [:div.text-white.text-center.p-2.ml-1.mt-1.bg-green-700 "wood 1"] ;without the extra ml-1 and mt-1 the first element is slightly off compared to rest of grid
    [:div.text-white.text-center.p-2.bg-gray-700 "ore 2"]
    [:div.text-white.text-center.p-2.bg-green-700  "wood 3"]
    [:div.text-white.text-center.p-2.bg-purple-800 "sheep 4"]
    [:div.text-white.text-center.p-2.bg-yellow-500 "wheat 5"]
    [:div.text-white.text-center.p-2.bg-yellow-900 "brick 6"]
    [:div.text-white.text-center.p-2.bg-pink-700 "desert 7"]
    [:div.text-white.text-center.p-2.bg-yellow-900 "brick 8"]
    [:div.text-white.text-center.p-2.bg-gray-700 "ore 9"]
    [:div.text-white.text-center.p-2.bg-green-700 "wood 10"]
    [:div.text-white.text-center.p-2.bg-purple-800 "sheep 11"]
    [:div.text-white.text-center.p-2.bg-yellow-500 "wheat 12"]
    [:div.text-white.text-center.p-2.bg-yellow-900 "brick 13"]
    [:div.text-white.text-center.p-2.bg-green-700 "wood 14"]
    [:div.text-white.text-center.p-2.bg-gray-700 "ore 15"]
    [:div.text-white.text-center.p-2.bg-yellow-500 "wheat 16"]
    [:div.text-white.text-center.p-2.bg-purple-800 "sheep 17"]
    [:div.text-white.text-center.p-2.bg-yellow-500 "wheat 18"]
    [:div.text-white.text-center.p-2.bg-yellow-900 "brick 19"]]])

(defn app []
  (let [state (r/atom true)]
    [:div
     [:h1.text-6xl.m-9 {:on-click #(js/alert "you clicked h1") }
      "Your random board"]
     [:div.m-12
      [:br]
      [grid-proto]
      [:div.border.border-yellow-300.my-5
       [:p "Board data hashmap:"]
       [:div (str @board)]]
      [:button.border.bg-indigo-500.border-indigo-500.text-white.rounded-md.px-4.py-2.m-2.hover:bg-indigo-600
       {:on-click #(reset! board (b/setup-board))}
       "Make a new board!"]]]))

(defn mount! []
  (rdom/render [app]
                  (.getElementById js/document "app")))

(defn main! []
  (mount!))

(defn reload! []
  (mount!))


(comment

  (do
    (reset! board (b/setup-board))
    (print @board))


  )