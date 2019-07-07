package com.amby.rest.repository.datajpa;

import com.amby.rest.entity.Visit;
import com.amby.rest.repository.VisitRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;

//@Profile("spring-data-jpa")
public interface SpringDataVisitRepository extends VisitRepository, Repository<Visit, Integer>, VisitRepositoryOverride {
}
