(defn bisect
  [f neg-point pos-point close-enough?]
  (let [c (/ (+ neg-point pos-point) 2)]
    (if (close-enough? neg-point pos-point)
      c
      (do
          (if (zero? (f c))
            c
            (if (pos? (f c))
              (recur f neg-point c close-enough?)
              (recur f c pos-point close-enough?)))))))

(defn make-bisector
  [tolerance]
  (fn[f a b]
    (letfn [(eq? [a b]
              (< (if (< a b) (- b a) (- a b)) tolerance))]
      (if (neg? (f a))
        (if (neg? (f b)) nil (bisect f a b eq?))
        (if (pos? (f b)) nil (bisect f b a eq?))))))

(defn make-queue [] (list))

(defn push-to-queue [q x]
  (doall (concat q (list x))))

(defn peek-at-queue [q] (first q))

(defn pop-from-queue [q]
  (if (empty? q) (list) (rest q)))

(defn empty-queue? [q] (empty? q))