package org.github.jeecms.service.impl

import scala.beans.BeanProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.jeecms.common.hibernate3.Updater
import com.jeecms.common.page.Pagination
import org.github.jeecms.service.SlmBookingService
import org.github.jeecms.dao.SlmBookingDao
import org.github.jeecms.entity.SlmBooking

@Service
@Transactional
class SlmBookingServiceImpl() extends SlmBookingService {
  @Autowired @BeanProperty var dao: SlmBookingDao = _
  @Transactional(readOnly = true)
  def getPage(userId: Option[Integer], contentId: Option[Integer], siteId: Option[Integer],
      cacheable: Boolean, pageNo: Int, pageSize: Int): Pagination = 
        dao.getPage(userId, contentId, siteId, cacheable, pageNo, pageSize)
  @Transactional(readOnly = true)
  def findById(id: Integer): SlmBooking = dao.findById(id)
  def save(bean: SlmBooking): SlmBooking = dao.save(bean)
  def update(bean: SlmBooking): SlmBooking = dao.updateByUpdater(new Updater(bean))
  def deleteById(id: Integer): SlmBooking = dao.deleteById(id)
  def deleteByIds(ids: Array[Integer]): Array[SlmBooking] = ids.map(b => deleteById(b))
}