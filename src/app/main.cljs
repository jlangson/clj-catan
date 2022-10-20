(ns app.main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [front-end.board :as b]
            [front-end.views :as views]))

(def board (r/atom (b/setup-board)))

(defn app []
  (let [state (r/atom true)]
    [:div
     [:h1.text-blue-600 {:on-click #(js/alert "you clicked h1") }
      "Your random board"]
     [:br]
     [views/grid]
     [:p (str @board)]
     [:button {:on-click #(reset! board (b/setup-board))}
      "Make a new board!"]]))

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