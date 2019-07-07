package com.amby.rest.repository.datajpa;

import com.amby.rest.entity.Pet;
import com.amby.rest.entity.PetType;
import com.amby.rest.repository.PetRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Spring Data JPA specialization of the {@link PetRepository} interface
 *
 */

//@Profile("spring-data-jpa")
public interface SpringDataPetRepository extends PetRepository, Repository<Pet, Integer>, PetRepositoryOverride {

    @Override
    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    List<PetType> findPetTypes() throws DataAccessException;
}
