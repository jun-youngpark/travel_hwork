package com.triple.travel.jpa.repository;

import com.triple.travel.jpa.entity.City;
import com.triple.travel.jpa.entity.TripleCity;
import com.triple.travel.jpa.repository.CityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Long>, CityRepositoryCustom {
    @Query("select c.cityName from City c where c.cityCode in :cityCodes")
    List<String> findByCityCodeIn(@Param("cityCodes") Collection<String> cityCodes);


}


