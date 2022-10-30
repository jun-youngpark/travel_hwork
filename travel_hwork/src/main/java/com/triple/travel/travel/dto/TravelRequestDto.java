package com.triple.travel.travel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.format.annotation.DateTimeFormat;
import com.triple.travel.common.Vaild.create;
import com.triple.travel.common.Vaild.update;
import com.triple.travel.common.Vaild.delete;
import com.triple.travel.common.Vaild.get;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Api(value = "여행 API ", tags = "여행지 API에 대한 항목입니다")
public class TravelRequestDto {

    @NotNull(message = "여행지를  선택해주세요" , groups = update.class)
    private Long travelId;

    @ApiModelProperty(value = "도시 번호", dataType = "Long", required = true)
    @NotNull(message = "도시를  선택해주세요" , groups = {create.class, update.class})
    private Long tripleCitySeq;

    @ApiModelProperty(value = "사용자 ID", dataType = "String")
    @NotNull(message = "로그인이 필요합니다", groups = create.class)
    private String userId;

    @ApiModelProperty(value = "여행지 이름", dataType = "String")
    private String travelTitle;

    @ApiModelProperty(value = "여행 동행자", dataType = "String")
    private String travelCompanion;

    @ApiModelProperty(value = "여행 스타일", dataType = "String")
    private String travelStyle;

    @ApiModelProperty(value = "여행 시작일", dataType = "String")
    @NotNull(message = "여행을 시작일을 선택해주세요", groups = create.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime travelStartDate;

    @ApiModelProperty(value = "여행 종료일", dataType = "String")
    @NotNull(message = "여행을 종료일을 선택해주세요", groups = create.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime travelEndDate;

}
