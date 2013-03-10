(ns homeworks.task01.sample-test
  (:use clojure.test
        clojure.contrib.math))

(load-file "src/homeworks/task01/solution.clj")

(deftest sample-test
  (testing "half interval method"
    (let [difference 0.001
          fuzzy-equals? #(< (abs (- %1 %2)) difference)
          f #(+ 1 (+ % %))]
      (is (fuzzy-equals? (- 1/2) (bisect f -100 100 fuzzy-equals?)))
      (is (fuzzy-equals? 0 (bisect #(+ % %) -100 100 fuzzy-equals?)))
      (is (fuzzy-equals? 1 (bisect #(- 1 (* (* % %) %)) 100 -100 fuzzy-equals?)))
      (is (fuzzy-equals? -1 (bisect #(+ (* (* % %) %) 1) -100 100 fuzzy-equals?)))
      (is (fuzzy-equals? (- 1/2) ((make-bisector difference) f -100 100)))
      (is (fuzzy-equals? -1 ((make-bisector difference) #(+ (* (* % %) %) 1) -100 100)))
      (is (fuzzy-equals? 0 ((make-bisector difference) #(+ % %) -100 100)))))

  (testing "queue"
    (is (= "baba"
           (peek-at-queue
             (push-to-queue
               (push-to-queue
                 (pop-from-queue
                   (push-to-queue (make-queue) "wink-wink"))
                 "baba")
               "wink-wink"))))))

(run-tests)
