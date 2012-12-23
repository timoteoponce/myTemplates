package org.timoponce

import javax.faces.bean.{RequestScoped, ManagedBean}
import javax.inject.Inject
import javax.persistence.{PersistenceContext, EntityManager}
import javax.transaction.UserTransaction

@ManagedBean
@RequestScoped
class HelloBean {

  @Inject
  var persister: Persister=_

  def greetings(): String = {
    val car = new Car
    car.setName("car - " + System.currentTimeMillis())

    persister.persist(car)
    return "Scello World!"
  }
}
