package org.timoponce

import javax.inject.Inject
import javax.persistence.{PersistenceContext, EntityManager}
import javax.transaction.UserTransaction

class Persister {

  @PersistenceContext
  var entityManager: EntityManager = _

  @Inject
  var transaction: UserTransaction = _

  def persist(t: AnyRef) = {
    val results = entityManager.createQuery("SELECT t FROM Car t").getResultList
    println("saving ..." + results.size())
    executeTransacted(entityManager.persist(t))
  }

  def executeTransacted[R](block: => R): R = {
    try {
      transaction.begin()
      val result = block
      return result
    } catch {
      case e: Exception =>
        transaction.rollback()
        throw new IllegalArgumentException("Something went wrong")
    } finally {
      transaction.commit()
    }
  }
}
