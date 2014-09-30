package org.timoponce;

import javax.faces.bean.*;
import javax.persistence.*;
import java.util.*;
import javax.ejb.*;
import org.slf4j.*;

@ManagedBean
@RequestScoped
public class HelloBean {
	
  static final Logger LOG = LoggerFactory.getLogger(HelloBean.class);

  @EJB
  Persister persister;

  public String getGreetings(){
	//save a car
    Car car = new Car();
    car.setName("car - " + System.currentTimeMillis());
    persister.persist(car);	
	//list all cars
	StringBuilder buffer = new StringBuilder("[");	
	for(Car _car : persister.list(Car.class))
		buffer.append(_car.getName()).append(",");
	
	buffer.setLength( buffer.length() - 1 );		
	buffer.append("]");
    String msg = "Hello World! : " + buffer.toString();
	LOG.info(msg);
	return msg;
  }
}
