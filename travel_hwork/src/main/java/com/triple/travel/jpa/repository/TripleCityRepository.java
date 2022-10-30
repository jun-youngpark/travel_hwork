package com.triple.travel.jpa.repository;

import com.triple.travel.jpa.entity.TripleCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TripleCityRepository extends JpaRepository<TripleCity, Long>, TripleCityRepositoryCustom {

    @Query("SELECT tc FROM  TripleCity tc, CityHitCount chc" +
            " WHERE tc.tripleCitySeq = chc.tripleCitySeq" +
            " AND chc.userId =  :userId " +
            " AND chc.hitCount >= 1 " +
            " AND chc.updateAt BETWEEN :startDate and :endDate  " +
            " ORDER BY chc.updateAt ")
    List<TripleCity> findRecentViewCity(@Param("userId") String userId
            , @Param("startDate") LocalDateTime travelStartDate,
                                        @Param("endDate") LocalDateTime travelEndDate);

    @Query("select t from TripleCity t where t.createAt between ?1 and ?2 ORDER BY t.createAt")
    List<TripleCity> findRegistedWithinOnday(LocalDateTime createAtStart, LocalDateTime createAtEnd);




}


