package org.timoponce;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: timoteo
 * Date: 12/22/12
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Car {
	
	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	public long getId(){
		return id;
	}

  	public void setId(long otherId){
		id = otherId;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String otherName){
		name = otherName;
	}
}
