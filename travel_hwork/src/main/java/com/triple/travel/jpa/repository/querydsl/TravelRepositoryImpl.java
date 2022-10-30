package com.triple.travel.jpa.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.triple.travel.jpa.repository.TravelRepositoryCustom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TravelRepositoryImpl implements TravelRepositoryCustom {

    private final JPAQueryFactory queryFactory;

}
