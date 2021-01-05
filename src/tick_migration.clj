(ns tick-migration
  (:require [tick.alpha.api :as tick]
            [clj-time.core :as t]
            [clj-time.coerce :as tc]
            [clj-time.format :as tf]))

;;; simple time/date functions
(t/now);; => #clj-time/date-time "2021-01-05T10:25:25.909Z"
(tick/now);; => #time/instant "2021-01-05T10:25:31.482018Z"

(t/today);; => #object[org.joda.time.LocalDate 0x56fc39be "2021-01-05"]
(tick/today);; => #time/date "2021-01-05"

;; compare timestamps
(t/before? (t/now) (t/plus (t/now) (t/hours 1)));; => true
(tick/< (tick/now)
        (-> (tick/now)
            (tick/>> (tick/new-duration 1 :hours))));; => true
