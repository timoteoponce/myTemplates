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

    try {
      transaction.begin()
      entityManager.persist(t)
      entityManager.flush()
    } finally {
      transaction.commit()
    }
  }
}
