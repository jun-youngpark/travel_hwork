package com.triple.travel.jpa.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
public class CityHitCountPK implements Serializable {

    @Id
    private Long tripleCitySeq;

    @Id
    private String userId;

}
