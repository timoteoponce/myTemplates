package org.timoponce.rest

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.timoponce.Persister
import java.util.List
import org.timoponce.Car
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class CarService {

  @Autowired
  var persister: Persister = _
  
  @RequestMapping(value = Array("/cars") ,method = Array(RequestMethod.GET))
  @ResponseBody
  def cars() : List[Car]={
    val car = new Car
    car.setName("car-" + System.nanoTime()) 
    persister.persist( car )
    
    return persister.getAll( classOf[Car] )
  }
}