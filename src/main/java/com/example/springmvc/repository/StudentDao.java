package com.example.springmvc.repository;

import com.example.springmvc.models.CollegeStudent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
* Since, we are using Spring JPA so we would need to use
* interface instead of concrete class as Spring JPA inherently
* will provide concrete implementation for the classes.
* */
@Repository
public interface StudentDao extends CrudRepository<CollegeStudent,Integer> {
    CollegeStudent findByEmailAddress(String email);
}
