package com.amby.rest.repository.datajpa;

import com.amby.rest.entity.Specialty;
import com.amby.rest.repository.SpecialtyRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;


//@Profile("spring-data-jpa")
public interface SpringDataSpecialtyRepository extends SpecialtyRepository, Repository<Specialty, Integer>, SpecialtyRepositoryOverride {

}
