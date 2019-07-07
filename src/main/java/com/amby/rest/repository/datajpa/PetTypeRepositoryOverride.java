package com.amby.rest.repository.datajpa;

import com.amby.rest.entity.PetType;
import org.springframework.context.annotation.Profile;

//@Profile("spring-data-jpa")
public interface PetTypeRepositoryOverride {
	
	void delete(PetType petType);

}
