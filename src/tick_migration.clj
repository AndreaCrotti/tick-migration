(ns tick-migration
  (:require [tick.alpha.api :as t]
            [clj-time.core :as ct]
            [clj-time.coerce :as cc]
            [clj-time.format :as cf]))

;;; simple time/date functions
(ct/now);; => #clj-time/date-time "2021-01-05T10:25:25.909Z"
(t/now);; => #time/instant "2021-01-05T10:25:31.482018Z"

(ct/today);; => #object[org.joda.time.LocalDate 0x56fc39be "2021-01-05"]
(t/today);; => #time/date "2021-01-05"

;; compare timestamps
(ct/before? (ct/now) (ct/plus (ct/now) (ct/hours 1)));; => true
(t/< (t/now)
     (-> (t/now)
         (t/>> (t/new-duration 1 :hours))));; => true

;; formatting dates
(cf/unparse (cf/formatter "YYYY-MM-DD") (ct/now));; => "2021-01-05"
(t/format (t/formatter "YYYY-MM-DD") ());; => "2021-01-05"

;; formatting times

;; parsing dates

;; parsing times

;; convert to other types

;; keep both old and new versions at the same time

(def ^:dynamic *use-tick* false)

(defmacro with-tick
  "Evaluate body setting the tick dynamic"
  [& body]
  `(binding [*use-tick* true]
     ~@body))

(defn with-both-versions [t]
  (binding [*use-tick* false]
    (t))
  (with-tick (t)))
