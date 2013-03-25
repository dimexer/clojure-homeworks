(defn normalize
  [player]
  {:vectors (for [x (vals player)
                   :when (vector? x)] x)
   :maps (for [x (vals player)
                :when (map? x)] x)
   :functions (for [x (vals player)
                    :when (fn? x)] x)
   :sets (for [x (vals player)
              :when (set? x)] x)})


(defn l3f3
  [pl]
  (count (for [x (:vectors pl) y (:vectors pl)
               :when (= (subvec x (- (count x) 3)) (subvec y 0 3 ))]
           x)))

(defn calc-vec-val
  [v]
  (println (/ (apply + (map #(* % %) (flatten v))) (count v)))
  (/ (apply + (map #(* % %) (flatten v))) (count v)))


(defn aver
  [player opp]
  (let [opp-nums (flatten [(:vectors opp) (opp :sets) (map vals (opp :maps))])
        opp-aver (/ (apply + opp-nums) (count opp-nums))]
    (println "OPP-AVER" opp-aver)
    (count (filter #(> (calc-vec-val %) opp-aver) (:vectors player)))))

(defn game
  [p1 p2]
  (let [p1-norm (normalize p1)
        p2-norm (normalize p2)]
    [(l3f3 p1-norm) (l3f3 p2-norm)]))

(println (aver (normalize {:v0 [() () '(1) '(2) '(3)] :v1 ['(1) '(2) '(3) () () ()]
                :v2 ['(1) '(2) '(3) '(10) '(1)] })
               (normalize {:v0 [() () '(1) '(2) '(3)] :v1 ['(1) '(2) '(3) () () ()]
                :v2 ['(1) '(2) '(3) '(15) '(20)] })))
