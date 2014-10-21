(ns fine.core-test
  (:require [clojure.test :refer :all]
            [fine.core :refer :all]))

(defn exception
  "Use this function to ensure that execution of a program doesn't
  reach certain point."
  []
  (throw (new Exception "Exception which should never occur")))

(deftest test-if-let*
  (are [x y] (= x y)
       [1 2] (if-let* [a 1] [z 2]
           [a z])
       [2 3] (if-let* [[a b] '(1 2)] [z 3]
           [b z])
       nil (if-let* [a false] [z (exception)]
             (exception))
       1 (if-let* [a false] [z (exception)]
           a 1)
       1 (if-let* [[a b] nil] [z (exception)]
             b 1)
       1 (if-let* [a false] [z (exception)]
           (exception) 1)
       ))

(deftest test-when-let*
  (are [x y] (= x y)
       [1 2] (when-let* [a 1] [z 2]
           [a z])
       [2 3] (when-let* [[a b] '(1 2)] [z 3]
           [b z])
       nil (when-let* [a false] [z (exception)]
             (exception))
       ))
