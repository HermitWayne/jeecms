package org.github.jeecms.service.impl

import java.util.{Map => JMap}
import com.jeecms.cms.service.ContentListenerAbstract
import com.jeecms.cms.entity.main.Content

class BookingContentListener extends ContentListenerAbstract {
  override def afterSave(content: Content) {
    formatData(content)
  }
  override def afterChange(content: Content, map: JMap[String, Object]) {
    formatData(content)
  }
  
  private def formatData(content: Content) {
    try {
    if (content.getAttr().get("_slm_magic") == "booking") {
      val data = content.getAttr()
      content.getContentExt().setTitle("[%s] %s %s ".format(data.get("bookingdate"), data.get("position"), data.get("target")))
    }
    } catch {
      case _: Exception =>
    }
  }
}