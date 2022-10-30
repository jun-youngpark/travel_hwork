package com.triple.travel.jpa.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class City extends AuditorEntity {

    @Id
    @Comment("도시코드")
    private String cityCode;

    @Comment("도시명")
    @Column(nullable = false)
    private String cityName;

    @Comment("도시명(영문)")
    @Column(nullable = false)
    private String cityEngName;

    @Builder
    public City(String cityCode, String cityName, String cityEngName) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.cityEngName = cityEngName;
    }
}
