package org.timoponce.interface

import javax.ejb.{LocalBean, Stateless}
import javax.ws.rs._
import javax.inject.{Named, Inject}
import org.timoponce.{Car, Persister}
import javax.ws.rs.core.MediaType
import java.util

/**
 * Created with IntelliJ IDEA.
 * User: timoteo
 * Date: 7/20/13
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@LocalBean
@Path("/cars")
@Named
class CarService {

  @Inject
  var persister: Persister=_

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getCars(): util.List[Car] = {
    val car = new Car
    car.setName("car - " + System.currentTimeMillis())
    persister.persist( car )

    return persister.getAll( classOf[Car] )
  }
}
