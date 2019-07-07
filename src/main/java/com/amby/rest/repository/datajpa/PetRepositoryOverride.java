package com.amby.rest.repository.datajpa;

import com.amby.rest.entity.Pet;
import org.springframework.context.annotation.Profile;

//@Profile("spring-data-jpa")
public interface PetRepositoryOverride {
	
	void delete(Pet pet);

}
