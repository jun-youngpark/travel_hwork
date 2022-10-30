package com.triple.travel.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.triple.travel.common.SessionUtils;
import com.triple.travel.common.exception.ApiException;
import com.triple.travel.travel.dto.TravelRequestDto;
import com.triple.travel.user.UserInfo;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.triple.travel.common.SessionUtils.getUserId;

@Getter @Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Travel extends AuditorEntity {

    @Id
    @Comment("여행지 ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long travelId;

    @Comment("도시코드")
    @Column(nullable = false)
    private Long tripleCitySeq;

    @Comment("사용자 ID")
    @Column(nullable = false)
    private String userId;

    @Comment("여행지명")
    private String travelTitle;

    @Comment("여행 동행자")
    private String travelCompanion;

    @Comment("여행 스타일")
    private String travelStyle;

    @Comment("여행 시작일")
    @Column(nullable = false)
    private LocalDateTime travelStartDate;

    @Comment("여행 종료일")
    @Column(nullable = false)
    private LocalDateTime travelEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripleCitySeq", insertable = false, updatable = false)
    @JsonBackReference
    private TripleCity tripleCity;

    @Builder
    public Travel(Long travelId, Long tripleCitySeq, String userId, String travelTitle, String travelCompanion, String travelStyle, LocalDateTime travelStartDate, LocalDateTime travelEndDate) {
        this.travelId = travelId;
        this.tripleCitySeq = tripleCitySeq;
        this.userId = userId;
        this.travelTitle = travelTitle;
        this.travelCompanion = travelCompanion;
        this.travelStyle = travelStyle;
        this.travelStartDate = travelStartDate;
        this.travelEndDate = travelEndDate;
    }


    public void isOwner(){
        if(!SessionUtils.getUserId().equals(userId)){
            new ApiException("허용 권한이 없습니다.");
        }
    }


    public void changeTravel(TravelRequestDto dto) {
        this.travelId = dto.getTravelId();
        this.tripleCitySeq = dto.getTripleCitySeq();
        this.travelCompanion = dto.getTravelCompanion();
        this.travelTitle = dto.getTravelTitle();
        this.travelStyle = dto.getTravelStyle();
        this.travelStartDate = dto.getTravelStartDate();
        this.travelEndDate = dto.getTravelEndDate();
    }
}
