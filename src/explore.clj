(ns explore
  (:require [clojure.java.io :as io]))

(defn explore [x, dir, db]
  (cond
    (or (= (first x) "-h") (= (first x) "--help")) (println "idiot explore: start a web server to explore the database\n\nUsage: idiot explore [-p <port>]\n\nArguments:\n   -p <port>   listen on the given port (default: 3000)")
    (= (.exists (io/as-file (str dir "/" db "/objects/"))) false) (println "Error: could not find database. (Did you run `idiot init`?)")
    (and (= (count x) 1) (= (first x) "-p")) (println "Error: you must specify a numeric port with '-p'.")
    (and (= (first x) "-p") (not (pos-int? (second x)))) (println "Error: the argument for '-p' must be a non-negative integer.")))
