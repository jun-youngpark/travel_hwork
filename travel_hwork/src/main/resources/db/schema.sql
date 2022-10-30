--  국가(나라) 테이블
CREATE TABLE CATEGORY(
   CATEGORY_CODE                     VARCHAR(255) NOT NULL,
   CATEGORY_NAME                     VARCHAR(255) NOT NULL,
   CATEGORY_ENG_NAME                 VARCHAR(255) NOT NULL,
   CONTIENT_NAME                     VARCHAR(255) NOT NULL,
   CONTIENT_ENG_NAME                 VARCHAR(255) NOT NULL,
   DOMESTIC_OVERSEAS_DIVISION        VARCHAR(255) NOT NULL,
   CREATE_AT                         TIMESTAMP     NULL,
   UPDATE_AT                         TIMESTAMP     NULL
)

ALTER TABLE CATEGORY ADD CONSTRAINT PK_CATEGORY PRIMARY KEY (CATEGORY_CODE);

 --  도시 테이블
CREATE TABLE CITY_INFO(
    CITY_CODE                       VARCHAR(255) NOT NULL,
    CITY_NAME                       VARCHAR(255) NOT NULL,
    CITY_ENG_NAME                   VARCHAR(255) NOT NULL,
    CREATE_AT                       TIMESTAMP   NULL,
    UPDATE_AT                       TIMESTAMP   NULL
)

ALTER TABLE CITY_INFO ADD CONSTRAINT PK_CITY_INFO PRIMARY KEY (CITY_CODE);

--  트리플 관리 도시 테이블
CREATE TABLE TRIPLE_CITY(
    TRIPLE_CITY_SEQ                     VARCHAR(255) NOT NULL,
    TRIPLE_CITY_NAME                    VARCHAR(255) NOT NULL,
    TRIPLE_CITY_INTRO                   VARCHAR(1000) NOT NULL,
    COUNTRY_CODE                        VARCHAR(255) NOT NULL,
    REF_CITY_CODE                       VARCHAR(1000) NOT NULL,
    CREATE_AT                           TIMESTAMP   NULL,
    UPDATE_AT                           TIMESTAMP   NULL
)

ALTER TABLE TRIPLE_CITY ADD CONSTRAINT PK_TRIPLE_CITY PRIMARY KEY (TRIPLE_CITY_SEQ);

CREATE TABLE CITY_HIT_COUNT(
    CITY_CODE                          VARCHAR(255) NOT NULL,
    USER_ID                            VARCHAR(50) NOT NULL,
    HIT_COUNT                          INT(10) NOT NULL,
    CREATE_AT                       TIMESTAMP   NULL,
    UPDATE_AT                       TIMESTAMP   NULL
)

ALTER TABLE CITY_HIT_COUNT ADD CONSTRAINT PK_CITY_HIT_COUNT PRIMARY KEY (CITY_CODE);

CREATE TABLE USER(
    USER_ID                            VARCHAR(255) NOT NULL,
    USER_NAME                          VARCHAR(255) NULL,
    CREATE_AT                           TIMESTAMP   NULL,
    UPDATE_AT                           TIMESTAMP   NULL
)

ALTER TABLE USER ADD CONSTRAINT PK_USER PRIMARY KEY (USER_ID);



CREATE TABLE TRALVE_INFO(
    TRAVLE_ID                          VARCAHR(50) NOT NULL,
    TRIPLE_CITY_SEQ                   VARCAHR(50) NOT NULL,
    USER_ID                           VARCAHR(50) NOT NULL,
    TRAVLE_TITLE                      VARCAHR(50) NULL,
    TRAVLE_COMPANION                  VARCAHR(50) NULL,
    TRAVLE_STYLE                      VARCAHR(50) NULL,
    TRAVLE_START_DATE                 VARCAHR(50) NULL,
    TRAVLE_END_DATE                   VARCAHR(50) NULL,
    CREATE_AT                       TIMESTAMP   NULL,
    UPDATE_AT                       TIMESTAMP   NULL
)

ALTER TABLE TRALVE_INFO ADD CONSTRAINT PK_TRALVE_INFO PRIMARY KEY (TRAVLE_ID);