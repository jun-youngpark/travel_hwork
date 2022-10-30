package com.triple.travel.jpa.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    private String userId;

    private String userName;

    @Builder
    public Users(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}