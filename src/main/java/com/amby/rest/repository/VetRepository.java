package com.amby.rest.repository;

import com.amby.rest.entity.Vet;
import org.springframework.dao.DataAccessException;


import java.util.Collection;

/**
 * Repository class for <code>Vet</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 */
public interface VetRepository {

    /**
     * Retrieve all <code>Vet</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Vet</code>s
     */
    Collection<Vet> findAll() throws DataAccessException;
    
	Vet findById(int id) throws DataAccessException;

	void save(Vet vet) throws DataAccessException;
	
	void delete(Vet vet) throws DataAccessException;


}
