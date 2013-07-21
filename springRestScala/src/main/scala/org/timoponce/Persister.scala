package org.timoponce

import javax.persistence.{ PersistenceContext, EntityManager }
import java.util
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.context.annotation.Scope
import org.springframework.beans.factory.annotation.Autowired
import org.hibernate.Session
import org.hibernate.SessionFactory

@Repository
@Transactional
@Scope("prototype")
class Persister {

  @Autowired
  var sessionFactory: SessionFactory = _

  def persist(t: AnyRef) = {
    session.persist(t)
  }

  def getAll[T](targetClass: Class[T]): util.List[T] = {
    val crit = session.createCriteria(targetClass)
    return crit.list.asInstanceOf[util.List[T]]
  }

  protected def session(): Session = {
    return sessionFactory.getCurrentSession();
  }

}
