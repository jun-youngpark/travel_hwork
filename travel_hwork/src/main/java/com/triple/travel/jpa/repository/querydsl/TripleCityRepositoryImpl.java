package com.triple.travel.jpa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.travel.jpa.entity.TripleCity;
import com.triple.travel.jpa.repository.TripleCityRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.triple.travel.jpa.entity.QTravel.travel;
import static com.triple.travel.jpa.entity.QTripleCity.*;

@RequiredArgsConstructor
public class TripleCityRepositoryImpl implements TripleCityRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public List<TripleCity> findByTravelingCity(String userId) {
        /*
        여행 중인 도시 : 여행 시작일이 빠른 것부터
        select * from travel t , triple_city tc
        where t.triple_city_seq = tc.triple_city_seq
        and t.user_id ='JYPARK'
        and (t.travel_start_date > '2022-10-29 16:15:00' or t.travel_end_date < '2022-10-29 16:15:00');
        */
        return queryFactory.selectFrom(tripleCity)
                .leftJoin(tripleCity.travels, travel)
                .fetchJoin()
                .where(travel.userId.eq(userId)
                        .and(travel.travelStartDate.after(LocalDateTime.now()))
                        .or(travel.travelEndDate.before(LocalDateTime.now())))
                .fetch();
    }

    public List<TripleCity> findByExpectedCity(String userId) {
        /*
        여행이 예정된 도시 : 여행 시작일이 가까운 것부터(오늘날짜)
        select tc.* from travel t , triple_city tc
        where t.triple_city_seq = tc.triple_city_seq
        and t.user_id ='JYPARK'
        and t.travel_start_date > '2022-10-29 16:15:00'
        order by t.travel_start_date desc   ;
        */
        return queryFactory.selectFrom(tripleCity)
                .leftJoin(tripleCity.travels, travel)
                .fetchJoin()
                .where(travel.userId.eq(userId)
                        .and(travel.travelStartDate.after(LocalDateTime.now())))
                .limit(10)
                .orderBy(travel.travelStartDate.desc())
                .distinct()
                .fetch();
    }
}
