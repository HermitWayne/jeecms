package org.github.jeecms.action.member

import scala.beans.BeanProperty;

import org.json.JSONObject;
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{
  RequestMapping, RequestMethod
}
import javax.servlet.http.{
  HttpServletRequest => JRequest, HttpServletResponse => JResponse
}
import com.jeecms.common.web.ResponseUtils
import com.jeecms.cms.web.CmsUtils
import com.jeecms.cms.manager.main.ContentMng



@Controller
class BookingAct {
  @Autowired @BeanProperty
  var contentMng: ContentMng = _
  
  @RequestMapping(value = Array("/booking/booking.jspx"), 
      method = Array(RequestMethod.POST))
  def postBooking(request: JRequest, response: JResponse, 
      cid: Integer, booking: org.github.jeecms.entity.SlmBooking): Unit = {
    val user = CmsUtils.getUser(request)
    val ret = new JSONObject()
    
    if (user == null) 
      ret.put("result", -1)
    else if (!checkBooking(cid, booking))
      ret.put("result", -2)
    else if (contentMng.findById(cid) == null)
      ret.put("result", -3)
    else {
      val bid = org.github.jeecms.model.SlmBookings.create(cid,user.getId, 
          booking.getTitle, booking.getRealname, 
          if (booking.getCompany == null) "" else booking.getCompany, 
          booking.getEmail, 
          booking.getTelphone, 
          if (booking.getRemark == null) "" else booking.getRemark)
      val cnt = org.github.jeecms.model.SlmBookings.booking(cid, bid)
      if (cnt == 0) ret.put("result", -3)
      else ret.put("result", bid)
    }
    ResponseUtils.renderJson(response, ret.toString)
  }
  
  private def checkBooking(cid: Integer, b: org.github.jeecms.entity.SlmBooking) = {
    if (cid == null || b.getTitle == null || b.getTitle.isEmpty() || 
        b.getRealname == null || b.getRealname.isEmpty() || 
        b.getTelphone == null || b.getTelphone.isEmpty()) false
    else true
  }
}