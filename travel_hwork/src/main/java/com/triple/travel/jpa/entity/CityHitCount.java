package com.triple.travel.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@IdClass(CityHitCountPK.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CityHitCount extends AuditorEntity {

    @Id
    @Comment("여행지 도시 시퀀스")
    private Long tripleCitySeq;

    @Id
    @Comment("사용자 ID")
    private String userId;

    @Comment("조회 수")
    @Column(nullable = false)
    private Integer hitCount;

    public void incHitCount(){
        this.hitCount++;
    }

    @Builder
    public CityHitCount(Long tripleCitySeq, String userId, Integer hitCount) {
        this.tripleCitySeq = tripleCitySeq;
        this.userId = userId;
        this.hitCount = hitCount;
    }
}
