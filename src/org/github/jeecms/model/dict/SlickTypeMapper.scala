package org.github.jeecms.model.dict

import scala.slick.lifted.MappedTypeMapper

object SlickTypeMapper {
  private def slickEnumMapper[E <: Enumeration#Value](xfun: (Int) => E) = {
    MappedTypeMapper.base[E, Int](
      (e => e.id), (i => xfun(i))
    )
  }
  implicit val slickCommonStatusMapper =
    slickEnumMapper(CommonStatus.apply)

  implicit val slickJavaDateStatusTypeMapper =
    MappedTypeMapper.base[java.util.Date, java.sql.Timestamp](
      (d => new java.sql.Timestamp(d.getTime())),
      (t => new java.util.Date(t.getTime()))
    )
}
