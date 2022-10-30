package com.triple.travel.tripleCity.dto;


import com.triple.travel.common.Vaild.create;
import com.triple.travel.common.Vaild.delete;
import com.triple.travel.common.Vaild.get;
import com.triple.travel.common.Vaild.update;
import com.triple.travel.jpa.entity.TripleCity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TripleCityResponseDto {

    private Long tripleCitySeq;
    private String tripleCityName;
    private String tripleCityEngName;
    private String tripleCityIntro;
    private String categoryCode;
    private String refCityCode;

    public TripleCityResponseDto(TripleCity tripleCity) {
        this.tripleCitySeq = tripleCity.getTripleCitySeq();
        this.tripleCityName = tripleCity.getTripleCityName();
        this.tripleCityIntro = tripleCity.getTripleCityIntro();
        this.categoryCode = tripleCity.getCategoryCode();
        this.refCityCode = tripleCity.getRefCityCode();
    }
}
