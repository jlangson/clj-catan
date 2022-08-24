(ns clj-catan.app
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found]]
            [clj-catan.board :as board]))

(defn make-board [_]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str (board/setup-board))})

(def home-page
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello home-page"})

(def login
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello login"})

(defroutes app
  (GET "/make-board" [] make-board)
  (GET "/" [] home-page)
  (GET "/login" [] login)
  (not-found "404 not found!"))

(defn -main [& args]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer/parseInt (first args))}))