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
public class Category extends AuditorEntity {

    @Id
    @Comment("카테고리 코드")
    private String categoryCode;

    @Comment("카테고리명")
    @Column(nullable = false)
    private String categoryName;

    @Comment("카테고리명(영문)")
    @Column(nullable = false)
    private String categoryEngName;

    @Comment("상위 카테고리 코드")
    private String parentCategoryCode;

    @Comment("국외 구분")
    private String domesticOverseasDivision;

    @Builder
    public Category(String categoryCode, String categoryName, String categoryEngName, String parentCategoryCode, String domesticOverseasDivision) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.categoryEngName = categoryEngName;
        this.parentCategoryCode = parentCategoryCode;
        this.domesticOverseasDivision = domesticOverseasDivision;
    }
}
