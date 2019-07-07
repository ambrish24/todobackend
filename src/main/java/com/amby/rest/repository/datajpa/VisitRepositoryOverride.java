package com.amby.rest.repository.datajpa;

import com.amby.rest.entity.Visit;
import org.springframework.context.annotation.Profile;

//@Profile("spring-data-jpa")
public interface VisitRepositoryOverride {
	
	void delete(Visit visit);

}
