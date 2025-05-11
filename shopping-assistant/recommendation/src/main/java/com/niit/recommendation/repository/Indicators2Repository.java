package com.niit.recommendation.repository;

import com.niit.recommendation.entity.Indicators2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Indicators2Repository extends JpaRepository<Indicators2,Integer>, CrudRepository<Indicators2,Integer> {
}
