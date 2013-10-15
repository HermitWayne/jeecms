package org.github.jeecms.entity

import scala.beans.BeanProperty

class SlmBooking(
    @BeanProperty var id: java.lang.Integer,
    @BeanProperty var content: com.jeecms.cms.entity.main.Content,
	@BeanProperty var user: com.jeecms.cms.entity.main.CmsUser,
    @BeanProperty var title: String,
    @BeanProperty var realname: String,
    @BeanProperty var company: String,
    @BeanProperty var email: String,
    @BeanProperty var telphone: String,
    @BeanProperty var remark: String,
    @BeanProperty var status: java.lang.Integer,
    @BeanProperty var createtime: java.util.Date
)