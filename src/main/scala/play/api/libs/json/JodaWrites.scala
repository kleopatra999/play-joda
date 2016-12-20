/*
 * Copyright (C) 2009-2016 Lightbend Inc. <https://www.lightbend.com>
 */
package play.api.libs.json

trait LowPriorityJodaDefaultWrites {

  /**
   * Serializer for org.joda.time.DateTime
   * @param pattern the pattern used by SimpleDateFormat
   */
  def jodaDateWrites(pattern: String): Writes[org.joda.time.DateTime] = new Writes[org.joda.time.DateTime] {
    private val df = org.joda.time.format.DateTimeFormat.forPattern(pattern)
    def writes(d: org.joda.time.DateTime): JsValue = JsString(d.toString(df))
  }

  /**
   * Default Serializer org.joda.time.DateTime -> JsNumber(d.getMillis (nb of ms))
   */
  implicit object DefaultJodaDateWrites extends Writes[org.joda.time.DateTime] {
    def writes(d: org.joda.time.DateTime): JsValue = JsNumber(d.getMillis)
  }

  /**
   * Serializer for org.joda.time.LocalDate
   * @param pattern the pattern used by org.joda.time.format.DateTimeFormat
   */
  def jodaLocalDateWrites(pattern: String): Writes[org.joda.time.LocalDate] = new Writes[org.joda.time.LocalDate] {
    private val df = org.joda.time.format.DateTimeFormat.forPattern(pattern)
    def writes(d: org.joda.time.LocalDate): JsValue = JsString(d.toString(df))
  }

  /**
   * Default Serializer org.joda.time.LocalDate -> JsString(ISO8601 format (yyyy-MM-dd))
   */
  implicit object DefaultJodaLocalDateWrites extends Writes[org.joda.time.LocalDate] {
    def writes(d: org.joda.time.LocalDate): JsValue = JsString(d.toString)
  }

  /**
   * Serializer for org.joda.time.LocalTime
   * @param pattern the pattern used by org.joda.time.format.DateTimeFormat
   */
  def jodaLocalTimeWrites(pattern: String): Writes[org.joda.time.LocalTime] = new Writes[org.joda.time.LocalTime] {
    def writes(d: org.joda.time.LocalTime): JsValue = JsString(d.toString(pattern))
  }

  /**
   * Default Serializer org.joda.time.LocalDate -> JsString(ISO8601 format (HH:mm:ss.SSS))
   */
  implicit object DefaultJodaLocalTimeWrites extends Writes[org.joda.time.LocalTime] {
    def writes(d: org.joda.time.LocalTime): JsValue = JsString(d.toString)
  }

}

object JodaWrites extends LowPriorityJodaDefaultWrites
