(ns rev-list
  (:require [clojure.java.io :as io]))

(defn rev-list [x, dir, db]
  (cond
    (or (= (first x) "-h") (= (first x) "--help")) (println "idiot rev-list: list preceding revisions, latest first\n\nUsage: idiot rev-list [-n <count>] [<ref>]\n\nArguments:\n   -n <count>   stop after <count> revisions (default: don't stop)\n   <ref>        a reference; see the rev-parse command (default: HEAD)")
    (= (.exists (io/as-file (str dir "/" db "/objects/"))) false) (println "Error: could not find database. (Did you run `idiot init`?)")
    (and (= (count x) 1) (= (first x) "-n")) (println "Error: you must specify a numeric count with '-n'.")
    (and (= (first x) "-n") (not (pos-int? (second x)))) (println "Error: the argument for '-n' must be a non-negative integer.")))