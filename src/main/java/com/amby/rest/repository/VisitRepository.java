package com.amby.rest.repository;

import com.amby.rest.entity.Visit;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;

/**
 * Repository class for <code>Visit</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 */
public interface VisitRepository {

    void save(Visit visit) throws DataAccessException;

    List<Visit> findByPetId(Integer petId);
    
	Visit findById(int id) throws DataAccessException;
	
	Collection<Visit> findAll() throws DataAccessException;

	void delete(Visit visit) throws DataAccessException;

}
