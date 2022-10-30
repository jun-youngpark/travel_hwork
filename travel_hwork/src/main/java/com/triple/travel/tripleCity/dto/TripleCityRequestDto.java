package com.triple.travel.tripleCity.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.triple.travel.common.Vaild.create;
import com.triple.travel.common.Vaild.update;
import com.triple.travel.common.Vaild.delete;
import com.triple.travel.common.Vaild.get;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TripleCityRequestDto {

    @NotNull(message = "여행지를 선택해주세요." , groups = {update.class,delete.class,get.class})
    private Long tripleCitySeq;
    @NotNull(message = "여행지 도시명을 입력하세요" , groups = create.class)
    private String tripleCityName;
    @NotNull(message = "여행지(영문) 도시명을 입력하세요", groups = create.class)
    private String tripleCityEngName;
    private String tripleCityIntro;
    private String categoryCode;
    private String refCityCode;

}
