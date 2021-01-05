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
