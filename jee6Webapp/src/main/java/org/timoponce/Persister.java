package org.timoponce;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;
import javax.transaction.UserTransaction;

@Stateless @LocalBean
public class Persister {

  @PersistenceContext
  EntityManager entityManager;

  	public <T>void persist(T t) {
		entityManager.persist(t);	
  	}

	public <T>List<T> list(Class<T> target){
		return (List<T>)entityManager
			.createQuery("SELECT t FROM " + target.getSimpleName() + " t")
			.getResultList();
	}
}
