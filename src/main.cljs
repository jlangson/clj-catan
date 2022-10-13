(ns main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [front-end.board :as board]
            ))

(defn make-board-button []
  [:div
   [:button "Generate Board"]])

(defn app []
  [:div
   [:h1 "Your random board"]
   [:br]
   [:p (str (board/setup-board))]
   (make-board-button)])

(defn mount! []
  (rdom/render [app]
                  (.getElementById js/document "app")))

(defn main! []
  (mount!))

(defn reload! []
  (mount!))