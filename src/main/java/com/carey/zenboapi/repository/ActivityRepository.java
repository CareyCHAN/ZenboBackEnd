package com.carey.zenboapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carey.zenboapi.model.Activity;;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

}
