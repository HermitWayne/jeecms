package org.github.jeecms.dao

import com.jeecms.common.hibernate3.Updater
import com.jeecms.common.page.Pagination
import org.github.jeecms.entity.SlmBooking

trait SlmBookingDao {
  def getPage(userId: Option[Integer], contentId: Option[Integer], siteId: Option[Integer],
      cacheable: Boolean, pageNo: Int, pageSize: Int): Pagination
  def findById(id: Integer): SlmBooking
  def save(bean: SlmBooking): SlmBooking
  def updateByUpdater(updater: Updater[SlmBooking]): SlmBooking
  def deleteById(id: Integer): SlmBooking
}