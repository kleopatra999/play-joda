/*
 * Copyright (C) 2009-2016 Lightbend Inc. <https://www.lightbend.com>
 */
package play.api.libs.json

import play.api.data.validation.ValidationError

trait LowPriorityJodaDefaultReads {

  /**
   * Reads for the `org.joda.time.DateTime` type.
   *
   * @param pattern a date pattern, as specified in `java.text.SimpleDateFormat`.
   * @param corrector a simple string transformation function that can be used to transform input String before parsing. Useful when standards are not exactly respected and require a few tweaks
   */
  def jodaDateReads(pattern: String, corrector: String => String = identity): Reads[org.joda.time.DateTime] = new Reads[org.joda.time.DateTime] {
    import org.joda.time.DateTime

    val df = org.joda.time.format.DateTimeFormat.forPattern(pattern)

    def reads(json: JsValue): JsResult[DateTime] = json match {
      case JsNumber(d) => JsSuccess(new DateTime(d.toLong))
      case JsString(s) => parseDate(corrector(s)) match {
        case Some(d) => JsSuccess(d)
        case None => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.date.format", pattern))))
      }
      case _ => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.date"))))
    }

    private def parseDate(input: String): Option[DateTime] =
      scala.util.control.Exception.allCatch[DateTime] opt (DateTime.parse(input, df))

  }

  /**
   * the default implicit JodaDate reads
   */
  implicit val DefaultJodaDateReads = jodaDateReads("yyyy-MM-dd")

  /**
   * Reads for the `org.joda.time.LocalDate` type.
   *
   * @param pattern a date pattern, as specified in `org.joda.time.format.DateTimeFormat`.
   * @param corrector string transformation function (See jodaDateReads)
   */
  def jodaLocalDateReads(pattern: String, corrector: String => String = identity): Reads[org.joda.time.LocalDate] = new Reads[org.joda.time.LocalDate] {

    import org.joda.time.LocalDate
    import org.joda.time.format.{ DateTimeFormat, ISODateTimeFormat }

    val df = if (pattern == "") ISODateTimeFormat.localDateParser else DateTimeFormat.forPattern(pattern)

    def reads(json: JsValue): JsResult[LocalDate] = json match {
      case JsString(s) => parseDate(corrector(s)) match {
        case Some(d) => JsSuccess(d)
        case None => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.date.format", pattern))))
      }
      case _ => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.date"))))
    }

    private def parseDate(input: String): Option[LocalDate] =
      scala.util.control.Exception.allCatch[LocalDate] opt (LocalDate.parse(input, df))
  }

  /**
   * the default implicit joda.time.LocalDate reads
   */
  implicit val DefaultJodaLocalDateReads = jodaLocalDateReads("")

  /**
   * Reads for the `org.joda.time.LocalTime` type.
   *
   * @param pattern a date pattern, as specified in `org.joda.time.format.DateTimeFormat`.
   * @param corrector string transformation function (See jodaTimeReads)
   */
  def jodaLocalTimeReads(pattern: String, corrector: String => String = identity): Reads[org.joda.time.LocalTime] = new Reads[org.joda.time.LocalTime] {

    import org.joda.time.LocalTime
    import org.joda.time.format.{ DateTimeFormat, ISODateTimeFormat }

    val df = if (pattern == "") ISODateTimeFormat.localTimeParser else DateTimeFormat.forPattern(pattern)

    def reads(json: JsValue): JsResult[LocalTime] = json match {
      case JsNumber(n) => JsSuccess(new LocalTime(n.toLong))
      case JsString(s) => parseTime(corrector(s)) match {
        case Some(d) => JsSuccess(d)
        case None => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.time.format", pattern))))
      }
      case _ => JsError(Seq(JsPath() -> Seq(ValidationError("error.expected.time"))))
    }

    private def parseTime(input: String): Option[LocalTime] =
      scala.util.control.Exception.allCatch[LocalTime] opt (LocalTime.parse(input, df))
  }

  /**
   * the default implicit joda.time.LocalTime reads
   */
  implicit val DefaultJodaLocalTimeReads = jodaLocalTimeReads("")

}

object JodaReads extends LowPriorityJodaDefaultReads
