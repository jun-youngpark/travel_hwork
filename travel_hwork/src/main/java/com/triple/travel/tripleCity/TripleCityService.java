package com.triple.travel.tripleCity;

import static com.triple.travel.common.DuplicateUtils.*;
import static com.triple.travel.common.SessionUtils.getUserId;

import com.triple.travel.common.DuplicateUtils;
import com.triple.travel.common.exception.ApiException;
import com.triple.travel.jpa.entity.CityHitCount;
import com.triple.travel.jpa.repository.TravelRepository;
import com.triple.travel.jpa.repository.TripleCityHitCountRepository;
import com.triple.travel.jpa.repository.CityRepository;
import com.triple.travel.tripleCity.dto.TripleCityRequestDto;
import com.triple.travel.jpa.entity.TripleCity;
import com.triple.travel.jpa.repository.TripleCityRepository;
import com.triple.travel.tripleCity.dto.TripleCityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TripleCityService {

    private final TripleCityRepository tripleCityRepository;
    private final TravelRepository travelRepository;
    private final TripleCityHitCountRepository  hitCountRepository;
    private final CityRepository cityRepository;

    @Value("${print.tripleCity.count}")
    private long printTripleCityCnt;


    public TripleCity save(TripleCityRequestDto dto){
        return tripleCityRepository.save(TripleCity.builder()
                .tripleCityName(dto.getTripleCityName())
                .tripleCityEngName(dto.getTripleCityEngName())
                .categoryCode(dto.getCategoryCode())
                .refCityCode(dto.getRefCityCode())
                .tripleCityIntro(dto.getTripleCityIntro())
                .build());

    }

    public Long modify(TripleCityRequestDto dto)  {
        TripleCity entity = tripleCityRepository.findById(dto.getTripleCitySeq()).orElseThrow(() -> new ApiException("?????????????????? ???????????????."));
        entity.changeTripleCity(dto);
        return tripleCityRepository.save(entity).getTripleCitySeq();
    }

    public Long delete(Long tripleCitySeq) {
        TripleCity entity = tripleCityRepository.findById(tripleCitySeq).orElseThrow(() -> new ApiException("?????????????????? ???????????????."));
        if(travelRepository.existsByTripleCitySeq(tripleCitySeq)){
            throw new ApiException("???????????? ????????? ????????? ??? ????????????.");
        }

        tripleCityRepository.delete(TripleCity.builder()
                                    .tripleCitySeq(tripleCitySeq)
                                    .build());
         return tripleCitySeq;
    }

    public TripleCity get(Long tripleCitySeq){
        TripleCity entity =  tripleCityRepository.findById(tripleCitySeq).orElseThrow(() -> new ApiException("?????????????????? ???????????????."));
        entity.setRefCityNames(cityRepository.findByCityCodeIn(entity.SplitRefCityCode()));
        Optional<CityHitCount> cityHitCount = Optional.ofNullable(hitCountRepository.findByTripleCitySeqAndUserId(tripleCitySeq, getUserId()));

        if(cityHitCount.isPresent()) {
            cityHitCount.get().incHitCount();
            hitCountRepository.save(cityHitCount.get());
        }else{
            hitCountRepository.save(CityHitCount.builder()
                    .tripleCitySeq(tripleCitySeq)
                    .userId(getUserId())
                    .hitCount(1)
                    .build());
        }
        return entity;
    }

    public List<TripleCityResponseDto> listByUser(String userId) {
        List<TripleCity> joined;
        //?????? ?????? ??????
        List<TripleCity> travelingCity = tripleCityRepository.findByTravelingCity(getUserId());
        //????????? ????????? ??????
        List<TripleCity> expectedCity = tripleCityRepository.findByExpectedCity(getUserId());
        joined = Stream.concat(travelingCity.stream(), expectedCity.stream())
                .collect(Collectors.toList());

        //?????? ????????? ????????? ??????
        List<TripleCity> registedCity = tripleCityRepository.findRegistedWithinOnday(LocalDateTime.now().minusDays(1),LocalDateTime.now());

        joined = Stream.concat(joined.stream(), registedCity.stream())
                .collect(Collectors.toList());

        //?????? ????????? ????????? ?????? ?????? ????????? ??????
        List<TripleCity> recentViewCity = tripleCityRepository.findRecentViewCity(getUserId(),
                LocalDateTime.now().minusDays(7),LocalDateTime.now());

        joined = Stream.concat(joined.stream(), recentViewCity.stream())
                .collect(Collectors.toList());

        joined = deduplication(joined,TripleCity::getTripleCitySeq);
        joined.stream().limit(10);
        List<TripleCityResponseDto> responseList =
                joined.stream().map(e -> new TripleCityResponseDto(e)).collect(Collectors.toList());

        return responseList;
    }


}


