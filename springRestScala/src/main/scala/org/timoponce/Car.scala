package org.timoponce

import javax.persistence.{GenerationType, GeneratedValue, Id, Entity}
import reflect.BeanProperty

/**
 * Created with IntelliJ IDEA.
 * User: timoteo
 * Date: 12/22/12
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
class Car {
  @BeanProperty
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = _

  @BeanProperty
  var name: String = _
}
