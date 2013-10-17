package org.github.jeecms.bean

import org.springframework.context.{
  ApplicationContextAware,
  ApplicationContext
}

class SpringBean extends ApplicationContextAware {
  def setApplicationContext(ctx: ApplicationContext) {
    SpringBean.appCtx = ctx
  }
}

object SpringBean {
  private var appCtx: ApplicationContext = _
  def bean[A](name: String): A = appCtx.getBean(name).asInstanceOf[A]
  def beanOpt[A](name: String): Option[A] = {
    if (appCtx.containsBean(name)) Some(bean(name))
    else None
  } 
}