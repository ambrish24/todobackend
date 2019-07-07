package com.amby.rest.repository.datajpa;

import com.amby.rest.entity.Vet;
import com.amby.rest.repository.VetRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;

//@Profile("spring-data-jpa")
public interface SpringDataVetRepository extends VetRepository, Repository<Vet, Integer> {
}
