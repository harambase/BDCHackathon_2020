package com.harambase.hackathon.server.dao.repository;

import com.harambase.hackathon.server.pojo.base.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CitiesRepository extends JpaRepository<Cities, Integer> {

    List<Cities> findByRegionId(int regionId);

}
