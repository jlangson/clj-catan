(ns app.main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [front-end.board :as b]
            [front-end.views :as views]))

(def board (r/atom (b/setup-board)))

(defn grid []
  [:div.border.border-purple-400
   [:h2.text-2xl.my-2 "this is a board grid"]
   [:div
    [:div "wood 1"]
    [:div "ore 2"]
    [:div "forest 3"]]
   [:div
    [:div "sheep 4"]
    [:div "wheat 5"]
    [:div "brick 6"]]])

(defn app []
  (let [state (r/atom true)]
    [:div
     [:h1.text-6xl.m-9 {:on-click #(js/alert "you clicked h1") }
      "Your random board"]
     [:div.m-12
      [:br]
      [grid]
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