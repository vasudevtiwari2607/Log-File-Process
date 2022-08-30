package com.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.model.LogObject;

@Repository
public interface LogRepository extends CrudRepository<LogObject, Long>{

}
