package org.github.jeecms.model

import scala.slick.driver.MySQLDriver.simple._
import Database.threadLocalSession
import org.github.jeecms.bean.SpringBean
import dict.SlickTypeMapper._

case class SlmBooking(id: Int,
  contentid: Int,
  userid: Int,
  title: String,
  realname: String,
  company: String,
  email: String,
  telphone: String,
  remark: String,
  status: dict.CommonStatus.Value,
  createtime: java.util.Date,
  modifytime: java.util.Date
)

object SlmBookings extends Table[SlmBooking]("ut_booking") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def contentid = column[Int]("contentid")
  def userid = column[Int]("userid")
  def title = column[String]("title", O.DBType("VARCHAR(80)"))
  def realname = column[String]("realname", O.DBType("VARCHAR(200)"))
  def company = column[String]("company", O.DBType("VARCHAR(100)"))
  def email = column[String]("email", O.DBType("VARCHAR(80)"))
  def telphone = column[String]("telphone", O.DBType("VARCHAR(80)"))
  def remark = column[String]("remark", O.DBType("TEXT"))
  def status = column[dict.CommonStatus.Value]("status")
  def createtime = column[java.util.Date]("createtime", O.DBType("DATETIME"))
  def modifytime = column[java.util.Date]("modifytime", O.DBType("DATETIME"))
  
  def * = id ~ contentid ~ userid ~ title ~ 
  	realname ~ company ~ email ~ telphone ~ remark ~ 
  	status ~ createtime ~ modifytime <> (SlmBooking, SlmBooking unapply _)
  	
  lazy val database = Database.forDataSource(SpringBean.bean[javax.sql.DataSource]("dataSource"))
  
  def create(contentid: Int, userid: Int, title: String,
      realname: String, company: String, email: String, telphone: String, remark: String):Int = database withSession {
  	 val now = new java.util.Date
  	 this.contentid ~ this.userid ~ 
  	 	this.title ~ this.realname ~ this.company ~ this.email ~ 
  	 	this.telphone ~ this.remark ~ 
  	 	status ~ createtime ~ modifytime returning id insert ((contentid, userid, 
  	 	    title, realname, company, email, telphone, remark,
  	 	    dict.CommonStatus.Deleted, now, now))
  }
  def booking(contentid: Int, id: Int) = database withSession {
    val q = Query(this).filter(b => b.contentid === contentid && 
        b.status =!= dict.CommonStatus.Deleted)
    val c = Query(q.map(_ => 1).length).first
    if (c == 0) {
      Query(this).filter(b => b.id === id && b.status === dict.CommonStatus.Deleted).map(_.status).update(dict.CommonStatus.Enabled)
    } else 0
  }
  def delete(id: Int) = database withSession {
    Query(this).filter(_.id === id).map(_.status).update(dict.CommonStatus.Deleted)
  }
  def read(id: Int) = database withSession {
    val now = new java.util.Date
    Query(this).filter(b => b.id === id && b.status === dict.CommonStatus.Enabled)
      .map(b => b.status ~ b.modifytime).update((dict.CommonStatus.Readed, now))
  }
  def isEnabledBooking(contentid: Int) = database withSession {
    val q = Query(this).filter(b => b.contentid === contentid && b.status =!= dict.CommonStatus.Deleted)
    Query(q.map(_ => 1).length).first
  } 
}