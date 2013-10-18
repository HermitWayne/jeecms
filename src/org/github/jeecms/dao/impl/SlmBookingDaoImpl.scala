package org.github.jeecms.dao.impl


import org.springframework.stereotype.Repository
import com.jeecms.common.hibernate3.{
  Finder, HibernateBaseDao, Updater
}
import com.jeecms.common.page.Pagination

import org.github.jeecms.dao.SlmBookingDao
import org.github.jeecms.entity.SlmBooking

@Repository
class SlmBookingDaoImpl() extends HibernateBaseDao[SlmBooking, Integer] with SlmBookingDao {
  override def getEntityClass() : Class[SlmBooking] = classOf[SlmBooking]
  
  def getPage(userId: Option[Integer], contentId: Option[Integer], siteId: Option[Integer],
      cacheable: Boolean, pageNo: Int, pageSize: Int): Pagination = {
    val f = Finder.create("from SlmBooking booking where 1 = 1")
    //userId.foreach(t => f.append(" and booking.user.id = :userId ").setParam("userId", userId))
    //contentId.foreach(t => f.append(" and booking.content.id = :contentId ").setParam("contentId", contentId))
    //siteId.foreach(t => f.append(" and booking.content.site.id = :siteId ").setParam("siteId", siteId))
    f.append(" and status != 1 ")
    f.append(" order by status ")
    f.setCacheable(cacheable)
    find(f, pageNo, pageSize)
  }
  
  def findById(id: Integer): SlmBooking = get(id)
  def save(bean: SlmBooking): SlmBooking = getSession.save(bean).asInstanceOf[SlmBooking]
  def deleteById(id: Integer): SlmBooking = {
    val entity = get(id)
    if (entity != null) {
      getSession.delete(entity)
    }
    entity
  }
}