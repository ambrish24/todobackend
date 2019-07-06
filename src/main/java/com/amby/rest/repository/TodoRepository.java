package com.amby.rest.repository;

import java.util.List;

import com.amby.rest.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
    List<Todo> findByUsername(String username);
}
