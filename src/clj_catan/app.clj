(ns clj-catan.app
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap.reload]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found]]
            [clojure.data.json :as json]
            [clojure.string :as str]))
