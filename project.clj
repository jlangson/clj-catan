(defproject clj-catan "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [compojure "1.7.0"]
                 [ring "1.9.5"]
                 [org.clojure/data.json "2.4.0"]
                 [com.github.seancorfield/next.jdbc "1.2.780"]
                 [org.postgresql/postgresql "42.3.6"]
                 [com.taoensso/tufte "2.2.0"]]
  :profiles {:dev {:dependencies [[org.clojure/test.check "1.1.1"]
                                  [ring/ring-mock "0.4.0"]
                                  [clj-test-containers "0.7.1"]
                                  ]}}
  :repl-options {:init-ns clj-catan.core})
