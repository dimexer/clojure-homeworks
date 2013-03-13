(defn bisect [f negp posp close-enough?]
  (let [c (/ (+ negp posp) 2)]
    (if (close-enough? negp posp) c
      (do (if (zero? (f c)) c
            (if (pos? (f c))
              (recur f negp c close-enough?)
              (recur f c posp close-enough?)))))))

(defn make-bisector [tol]
  (fn[f a b]
    (letfn [(eq? [a b]
              (< (if (< a b) (- b a) (- a b)) tol))]
      (if (neg? (f a))
        (if (neg? (f b)) nil (bisect f a b eq?))
        (if (pos? (f b)) nil (bisect f b a eq?))))))

(defn make-queue [] (vector))

(defn push-to-queue [q x] (conj q x))

(defn peek-at-queue [q] (first q))

(defn pop-from-queue [q] (vec (subvec q 1)))

(defn empty-queue? [q] (empty? q))