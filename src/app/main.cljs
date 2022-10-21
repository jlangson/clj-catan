(ns app.main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [front-end.board :as b]
            [front-end.views :as views]))

(def board (r/atom (b/setup-board)))

(defn grid-proto []
  [:div.border.border-purple-400
   [:h2.text-2xl.my-2 "board grid prototype"]
   [:div.grid.grid-cols-5.gap-1
    [:div.text-white.text-center.p-2.bg-green-700 "lumber 1"]
    [:div.text-white.text-center.p-2.bg-gray-700 "ore 2"]
    [:div.text-white.text-center.p-2.bg-green-700  "lumber 3"]
    [:div.text-white.text-center.p-2.bg-purple-800 "wool 4"]
    [:div.text-white.text-center.p-2.bg-yellow-500 "grain 5"]
    [:div.text-white.text-center.p-2.bg-yellow-900 "brick 6"]
    [:div.text-white.text-center.p-2.bg-pink-700 "desert 7"]
    [:div.text-white.text-center.p-2.bg-yellow-900 "brick 8"]
    [:div.text-white.text-center.p-2.bg-gray-700 "ore 9"]
    [:div.text-white.text-center.p-2.bg-green-700 "lumber 10"]
    [:div.text-white.text-center.p-2.bg-purple-800 "wool 11"]
    [:div.text-white.text-center.p-2.bg-yellow-500 "grain 12"]
    [:div.text-white.text-center.p-2.bg-yellow-900 "brick 13"]
    [:div.text-white.text-center.p-2.bg-green-700 "lumber 14"]
    [:div.text-white.text-center.p-2.bg-gray-700 "ore 15"]
    [:div.text-white.text-center.p-2.bg-yellow-500 "grain 16"]
    [:div.text-white.text-center.p-2.bg-purple-800 "wool 17"]
    [:div.text-white.text-center.p-2.bg-yellow-500 "grain 18"]
    [:div.text-white.text-center.p-2.bg-yellow-900 "brick 19"]]])

(defn app []
  (let [state (r/atom true)]
    [:div
     [:h1.text-6xl.m-9 "Your random board"]
     [:div.m-12
      [:br]
      [views/map->grid @board]
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