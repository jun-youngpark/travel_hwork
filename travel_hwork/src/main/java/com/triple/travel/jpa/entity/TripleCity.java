package com.triple.travel.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.triple.travel.common.exception.ApiException;
import com.triple.travel.tripleCity.dto.TripleCityRequestDto;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TripleCity extends AuditorEntity {

    @Id
    @Comment("여행지 도시 시퀀스")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tripleCitySeq;

    @Comment("여행지 도시명")
    @Column(nullable = false)
    private String tripleCityName;

    @Comment("여행지 도시명(영문)")
    @Column(nullable = false)
    private String tripleCityEngName;

    @Comment("여행지 도시 소개글")
    private String tripleCityIntro;

    @Comment("여행지 도시 카테고리")
    private String categoryCode;

    @Comment("참조 도시 코드")
    private String refCityCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryCode", insertable = false, updatable = false)
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "tripleCity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Travel> travels;

    public String  refCityNames;
    public void setRefCityNames(List<String> cityNames){
        this.refCityNames = cityNames.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("/"));
    }

    public void changeTripleCity( TripleCityRequestDto dto) {
        if(dto.getTripleCitySeq() == null){
            throw new ApiException("SEQ 값 오류");
        }
        this.tripleCityName = dto.getTripleCityName();
        this.tripleCityEngName = dto.getTripleCityEngName();
        this.categoryCode = dto.getCategoryCode();
        this.refCityCode = dto.getRefCityCode();
    }

    public List<String> SplitRefCityCode() {
        return Arrays.asList(refCityCode.split(","));
    }

    @Builder
    public TripleCity(Long tripleCitySeq, String tripleCityName, String tripleCityEngName, String tripleCityIntro, String categoryCode, String refCityCode) {
        this.tripleCitySeq = tripleCitySeq;
        this.tripleCityName = tripleCityName;
        this.tripleCityEngName = tripleCityEngName;
        this.tripleCityIntro = tripleCityIntro;
        this.categoryCode = categoryCode;
        this.refCityCode = refCityCode;
    }


}
