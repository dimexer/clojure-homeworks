(defn bisect
  [f neg-point pos-point close-enough?]
  (let [c (/ (+ neg-point pos-point) 2)]
    (if (close-enough? neg-point pos-point)
      c
      (do
          (if (= 0 (f c))
            c
            (if (> (f c) 0)
              (recur f neg-point c close-enough?)
              (recur f c pos-point close-enough?)))))))
