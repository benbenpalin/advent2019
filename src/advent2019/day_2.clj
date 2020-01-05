(ns advent2019.day-2)
(require '[clojure.string :as str])

(def test-val "1,12,2,3,2,3,11,0,99,30,40,50")

(def puzzle-input-1-of-2"1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,13,19,1,10,19,23,2,9,23,27,1,6,27,31,1,10,31,35,1,35,10,39,1,9,39,43,1,6,43,47,1,10,47,51,1,6,51,55,2,13,55,59,1,6,59,63,1,10,63,67,2,67,9,71,1,71,5,75,1,13,75,79,2,79,13,83,1,83,9,87,2,10,87,91,2,91,6,95,2,13,95,99,1,10,99,103,2,9,103,107,1,107,5,111,2,9,111,115,1,5,115,119,1,9,119,123,2,123,6,127,1,5,127,131,1,10,131,135,1,135,6,139,1,139,5,143,1,143,9,147,1,5,147,151,1,151,13,155,1,5,155,159,1,2,159,163,1,163,6,0,99,2,0,14,0")

(defn sep-string [code]
  (map #(Integer. %)
    (str/split code #",")))

(defn perform-operation [program operation start]
  (let [add1 (program (program (inc start)))
        add2 (program (program (+ 2 start)))
        result (operation add1 add2)]
    (assoc program (program (+ 3 start)) result)))

(defn run-program [program]
  (loop [prog (into [] (sep-string program))
         start 0]
    (if (not= 99 (prog start))
      (case (prog start)
        1 (recur (perform-operation prog + start) (+ 4 start))
        2 (recur (perform-operation prog * start) (+ 4 start)))
      (str/join "," prog))))



(def solution-1-of-2 (run-program puzzle-input-1-of-2))
