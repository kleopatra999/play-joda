/*
 * Copyright (C) 2009-2016 Lightbend Inc. <https://www.lightbend.com>
 */
package play.api.libs.json

import org.specs2.mutable._

object JsonJodaValidSpec extends Specification {
  "JSON reads" should {
    "validate Dates" in {

      Json.toJson[java.util.Date](dd).validate[java.util.Date] must beEqualTo(JsSuccess(dd))
      JsNumber(dd.getTime).validate[java.util.Date] must beEqualTo(JsSuccess(dd))

      val dj = new org.joda.time.DateTime()
      val dfj = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd")
      val ddj = org.joda.time.DateTime.parse(dfj.print(dj), dfj)

      Json.toJson[org.joda.time.DateTime](ddj).validate[org.joda.time.DateTime] must beEqualTo(JsSuccess(ddj))
      JsNumber(ddj.getMillis).validate[org.joda.time.DateTime] must beEqualTo(JsSuccess(ddj))

      val ldj = org.joda.time.LocalDate.parse(dfj.print(dj), dfj)
      Json.toJson[org.joda.time.LocalDate](ldj).validate[org.joda.time.LocalDate] must beEqualTo(JsSuccess(ldj))

      val dtfj = org.joda.time.format.DateTimeFormat.forPattern("HH:mm:ss.SSS")
      val ltj = org.joda.time.LocalTime.parse(dtfj.print(dj), dtfj)
      Json.toJson[org.joda.time.LocalTime](ltj).validate[org.joda.time.LocalTime] must beEqualTo(JsSuccess(ltj))
    }


  }

}