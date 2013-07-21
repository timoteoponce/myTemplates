package org.timoponce

import javax.persistence.{PersistenceContext, EntityManager}
import java.util

class Persister {

  @PersistenceContext
  var entityManager: EntityManager = _

  def persist(t: AnyRef) = {
    entityManager.persist(t)
  }

  def getAll[T]( targetClass: Class[T] ): util.List[T]={
    val className = targetClass.getSimpleName
    val result = entityManager.createQuery("SELECT t FROM "+ className +" t").getResultList
    return result.asInstanceOf[ util.List[T] ]
  }

}
