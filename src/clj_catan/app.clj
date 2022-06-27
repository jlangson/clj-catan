(ns clj-catan.app
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap.reload]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found]]
            [clojure.data.json :as json]
            [clojure.string :as str]))


(defn make-board [])

(def home-page [])

(def login [])

(defroutes app
  (GET "/make-board" [] make-board)
  (GET "/" [] home-page)
  (GET "/" [] login)
  (not-found "404 not found!"))


(defn -main [& args]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer/parseInt (first args))}))