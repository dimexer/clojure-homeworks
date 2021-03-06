(defn last-3-first-3
  [p]
  (count (for [v1 (vals p)
               v2 (vals p)
               :when (and (vector? v1)
                          (vector? v2)
                          (= (subvec v1 (- (count v1) 3)) (subvec v2 0 3)))]
           v2)))

(defn player-average
  [p]
  (apply + (for [s (vals p)
                  :when (not (instance? clojure.lang.LazySeq s))]
              (if (map? s) (vals s) (apply + (flatten s))))))

(defn sum-square
  [x y]
  (+ (* x x) (* y y)))

(defn mid
  [p opp]
  (let [opp-aver (player-average opp)
        vec-val (fn [v] (/ (apply + (for [x (flatten v)] (* x x)))) (count v))]
    (println opp-aver)
    (count (for [v (vals p)
                 :when (and (vector? v) (> (vec-val v) opp-aver))]
             v))))

(defn game
  [p1 p2]
;  [(last-3-first-3 p1) (last-3-first-3 p2)])
  [(mid p1 p2) (mid p2 p1)])


(println (game {:v0 [() () '(1) '(2) '(3)] :v1 ['(1) '(2) '(3) () () ()]
                :v2 ['(1) '(2) '(3) '(10) '(1)] }
               {:v0 [() () '(1) '(2) '(3)] :v1 ['(1) '(2) '(3) () () ()]
                :v2 ['(1) '(2) '(3) '(15) '(20)] }))
