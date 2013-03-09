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

(defn make-bisector
  [tolerance]
  (fn[f a b]
    (defn tol[a b]
      (if (< a b) (< (- b a) tolerance) (< (- a b) tolerance)))
    (if (neg? (f a))
;      (if (neg? (f b)) nil (bisect f a b (fn [a b](< (- a b) tolerance))))
;      (if (pos? (f b)) nil (bisect f b a (fn [a b](< (- a b) tolerance)))))))
      (if (neg? (f b)) nil (bisect f a b tol))
      (if (pos? (f b)) nil (bisect f a b tol)))))

