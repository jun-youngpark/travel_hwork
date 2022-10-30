package com.triple.travel.travel;

import static com.triple.travel.common.SessionUtils.getUserId;
import com.triple.travel.common.exception.ApiException;
import com.triple.travel.jpa.entity.Travel;
import com.triple.travel.jpa.repository.TravelRepository;
import com.triple.travel.travel.dto.TravelRequestDto;
import com.triple.travel.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    public Travel save(TravelRequestDto dto){
        String existTravel = travelRepository.existsByExistTravel(dto.getTravelStartDate()
                , dto.getTravelEndDate());
        if(StringUtils.isNotBlank(existTravel)) throw new ApiException("출발 시간/도착 시간에 이미 등록된 여행지가 있습니다. ("+existTravel+")");

        return travelRepository.save(Travel.builder()
                                                .tripleCitySeq(dto.getTripleCitySeq())
                                                .userId(dto.getUserId())
                                                .travelTitle(dto.getTravelTitle())
                                                .travelStyle(dto.getTravelStyle())
                                                .travelCompanion(dto.getTravelCompanion())
                                                .travelStartDate(dto.getTravelStartDate())
                                                .travelEndDate(dto.getTravelEndDate())
                                                .build());
    }

    public Long modify(TravelRequestDto dto)  {
        Travel entity = travelRepository.findById(dto.getTravelId()).orElseThrow(() -> new ApiException("존재하지않는 여행 목록입니다."));
        entity.isOwner();
        entity.changeTravel(dto);
        return travelRepository.save(entity).getTravelId();
    }

    public Long delete(Long travelId) {
        Travel entity = travelRepository.findById(travelId).orElseThrow(() -> new ApiException("존재하지않는 여행 목록입니다."));
        entity.isOwner();
        travelRepository.delete(Travel.builder()
                                    .travelId(travelId)
                                    .build());
         return travelId;
    }

    public Optional<Travel> get(Long travelId){
        return travelRepository.findById(travelId);
    }


}


