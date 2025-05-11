package com.niit.recommendation.repository;

import com.niit.recommendation.entity.Indicators1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Indicators1Repository extends JpaRepository<Indicators1,Integer>, CrudRepository<Indicators1,Integer> {
}
