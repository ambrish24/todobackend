package com.amby.rest.repository;

import com.amby.rest.entity.Pet;
import com.amby.rest.entity.PetType;
import org.springframework.dao.DataAccessException;

import java.util.Collection;
import java.util.List;

/**
 * Repository class for <code>Pet</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 */
public interface PetRepository {

    /**
     * Retrieve all <code>PetType</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>PetType</code>s
     */
    List<PetType> findPetTypes() throws DataAccessException;

    /**
     * Retrieve a <code>Pet</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Pet</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    Pet findById(int id) throws DataAccessException;

    /**
     * Save a <code>Pet</code> to the data store, either inserting or updating it.
     *
     * @param pet the <code>Pet</code> to save
     *
     */
    void save(Pet pet) throws DataAccessException;
    
    /**
     * Retrieve <code>Pet</code>s from the data store, returning all owners 
     *
     * @return a <code>Collection</code> of <code>Pet</code>s (or an empty <code>Collection</code> if none
     * found)
     */
	Collection<Pet> findAll() throws DataAccessException;

    /**
     * Delete an <code>Pet</code> to the data store by <code>Pet</code>.
     *
     * @param pet the <code>Pet</code> to delete
     * 
     */
	void delete(Pet pet) throws DataAccessException;

}
