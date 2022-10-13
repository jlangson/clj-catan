(ns main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))


(defn app []
  [:div
   [:p "I am a component"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])


(defn mount! []
  (rdom/render [app]
                  (.getElementById js/document "app")))

(defn main! []
  (mount!))

(defn reload! []
  (mount!))

;(defn ^:export start! []
;  (rdom/render [simple-component] (js/document.getElementById "app")))

;(defn reload! [])