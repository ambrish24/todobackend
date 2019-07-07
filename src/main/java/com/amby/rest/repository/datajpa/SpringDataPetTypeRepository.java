package com.amby.rest.repository.datajpa;

import com.amby.rest.entity.PetType;
import com.amby.rest.repository.PetTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;

//@Profile("spring-data-jpa")
public interface SpringDataPetTypeRepository extends PetTypeRepository, Repository<PetType, Integer>, PetTypeRepositoryOverride {

}
