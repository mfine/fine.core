(ns fine.core)

(defmacro def-
  "same as def, yielding non-public def"
  [name & decls]
    (list* `def (with-meta name (assoc (meta name) :private true)) decls))

(defmacro if-let*
  "When test is true, evaluates body with test-binding bound to the value of test
   and other-bindings bound, if not, yields else"
  ([test-binding other-bindings then]
     `(if-let* ~test-binding ~other-bindings ~then nil))
  ([test-binding other-bindings then else]
     (let [form (test-binding 0) tst (test-binding 1)]
       `(let [temp# ~tst]
          (if temp#
            (let [~form temp#]
              (let ~other-bindings
                ~then))
            ~else)))))

(defmacro when-let*
  "When test is true, evaluates body with test-binding bound to the value of test
   and other-bindings bound"
  [test-binding other-bindings & body]
  (let [form (test-binding 0) tst (test-binding 1)]
    `(let [temp# ~tst]
       (when temp#
         (let [~form temp#]
           (let ~other-bindings
             ~@body))))))
