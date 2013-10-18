package org.github.jeecms.action.admin

import scala.beans.BeanProperty
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{
  RequestMapping, RequestMethod
}
import org.springframework.ui.ModelMap
import javax.servlet.http.{
  HttpServletRequest => JRequest, HttpServletResponse => JResponse
}
import com.jeecms.cms.web.{CmsUtils, WebErrors}
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.page.SimplePage.cpn
import org.github.jeecms.service.SlmBookingService

@Controller
class BookingAct {
  @Autowired @BeanProperty var service: SlmBookingService = _
  
  @RequestMapping(Array("/booking/v_list.do"))
  def list(pageNo: Integer, request: JRequest, model: ModelMap) = {
    val pagination = service.getPage(None, None,Some(CmsUtils.getSiteId(request)), false,
        cpn(pageNo), CookieUtils.getPageSize(request))
    model.addAttribute("pagination",pagination)
    model.addAttribute("pageNo",pagination.getPageNo())
    "booking/list"
  }
  @RequestMapping(Array("/booking/v_view.do"))
  def view(id: Integer, pageNo: Integer, request: JRequest, model: ModelMap) = {
    if (id != null) {
      val booking = service.findById(id)
      org.github.jeecms.model.SlmBookings.read(id)
      model.addAttribute("booking", booking)
    }
    "booking/view"
  }
  @RequestMapping(Array("/booking/o_delete.do"))
  def delete(ids: Array[Integer], pageNo: Integer, request: JRequest, model: ModelMap) = {
    ids.map(org.github.jeecms.model.SlmBookings.delete(_))
    list(pageNo, request, model)
  }
}