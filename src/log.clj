(ns log
  (:require [clojure.java.io :as io]))

(defn log [x, dir, db]
  (cond
    (or (= (first x) "-h") (= (first x) "--help")) (println "idiot log: print abbreviated commit addresses and commit summaries\n\nUsage: idiot log --oneline [-n <count>] [<ref>]\n\nArguments:\n   -n <count>   stop after <count> revisions (default: don't stop)\n   <ref>        a reference; see the rev-parse command (default: HEAD)")
    (= (.exists (io/as-file (str dir "/" db "/objects/"))) false) (println "Error: could not find database. (Did you run `idiot init`?)")
    (not= (first x) "--oneline") (println "Error: log requires the --oneline switch")
    (and (= (count x) 2) (= (second x) "-n")) (println "Error: you must specify a numeric count with '-n'.")
    (and (= (second x) "-n") (not (pos-int? (rest (second x))))) (println "Error: the argument for '-n' must be a non-negative integer.")))
