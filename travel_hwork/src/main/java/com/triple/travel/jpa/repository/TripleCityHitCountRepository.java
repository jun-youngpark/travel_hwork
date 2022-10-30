package com.triple.travel.jpa.repository;

import com.triple.travel.jpa.entity.CityHitCount;
import com.triple.travel.jpa.entity.TripleCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TripleCityHitCountRepository extends JpaRepository<CityHitCount, Long>, CityRepositoryCustom {
    CityHitCount findByTripleCitySeqAndUserId(Long tripleCitySeq, String userId);



}


