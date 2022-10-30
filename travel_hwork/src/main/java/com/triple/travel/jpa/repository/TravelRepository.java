package com.triple.travel.jpa.repository;

import com.triple.travel.jpa.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TravelRepository extends JpaRepository<Travel, Long>, TravelRepositoryCustom {

    @Query("SELECT travelTitle FROM Travel t " +
            "WHERE (t.travelStartDate BETWEEN :startDate and :endDate)" +
            "OR  (t.travelEndDate BETWEEN :startDate and :endDate) ")
    String existsByExistTravel(@Param("startDate") LocalDateTime travelStartDate,
                               @Param("endDate") LocalDateTime travelEndDate);

    boolean existsByTripleCitySeq(Long tripleCitySeq);
}


