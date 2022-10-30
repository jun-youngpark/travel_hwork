package com.triple.travel.jpa.repository;


import com.triple.travel.jpa.entity.TripleCity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TripleCityRepositoryCustom {

    List<TripleCity> findByTravelingCity(String userId);
    List<TripleCity> findByExpectedCity(String userId);




}
