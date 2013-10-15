package org.github.jeecms.service

import com.jeecms.common.page.Pagination
import org.github.jeecms.entity.SlmBooking

trait SlmBookingService {
  def getPage(userId: Option[Integer], contentId: Option[Integer], siteId: Option[Integer],
      cacheable: Boolean, pageNo: Int, pageSize: Int): Pagination
  def findById(id: Integer): SlmBooking
  def save(bean: SlmBooking): SlmBooking
  def update(bean: SlmBooking): SlmBooking
  def deleteById(id: Integer): SlmBooking
  def deleteByIds(ids: Array[Integer]): Array[SlmBooking]
}