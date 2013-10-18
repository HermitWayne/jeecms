package org.github.jeecms.entity

import scala.beans.BeanProperty

class SlmBooking(
    private var id: java.lang.Integer,
    private var content: com.jeecms.cms.entity.main.Content,
	private var user: com.jeecms.cms.entity.main.CmsUser,
	private var title: String,
    private var realname: String,
    private var company: String,
    private var email: String,
    private var telphone: String,
    private var remark: String,
    private var status: java.lang.Integer,
    private var createtime: java.util.Date,
    private var modifytime: java.util.Date
) {
  def this() = {
    this(null, null, null, null, null, null, null, null, null, null, null, null)
  }
  def getId(): java.lang.Integer = id
  def setId(id: java.lang.Integer) {this.id = id}
  def getContent(): com.jeecms.cms.entity.main.Content = content
  def setContent(content: com.jeecms.cms.entity.main.Content) {this.content = content}
  def getUser(): com.jeecms.cms.entity.main.CmsUser = user
  def setUser(user: com.jeecms.cms.entity.main.CmsUser) = this.user = user
  def getTitle(): String = title
  def setTitle(title: String) {this.title = title}
  def getRealname(): String = realname
  def setRealname(realname: String) {this.realname = realname}
  def getCompany(): String = company
  def setCompany(company: String) {this.company = company}
  def getEmail(): String = email
  def setEmail(email: String) {this.email = email}
  def getTelphone(): String = telphone
  def setTelphone(telphone: String) {this.telphone = telphone}
  def getRemark(): String = remark
  def setRemark(remark: String) {this.remark = remark}
  def getStatus(): Integer = status
  def setStatus(status: Integer) {this.status = status}
  def getCreatetime(): java.util.Date = createtime
  def setCreatetime(createtime: java.util.Date) {this.createtime = createtime}
  def getModifytime(): java.util.Date = modifytime
  def setModifytime(modifytime: java.util.Date) {this.modifytime = modifytime}
}