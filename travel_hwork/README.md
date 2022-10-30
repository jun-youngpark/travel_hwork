
# 사용 언어 안내
1) Spring boot 버전 2.75 (2022/10/23일 기준 GA 버전)
2) JDK 1.8 
3) Maven 

# 사용법
## 이클립스 or Intellij 구동 방법
1) 로컬 H2 DB 사용 시 자바 옵션 -Dspring.profiles.active=dev 추가 후 TravelApplication run 하시면 됩니다. 
2) MYSQL db 사용 시 자바 옵션 -Dspring.profiles.active=prd 추가 후 TravelApplication run 하시면 됩니다.
(application-prd.yml 파일 내 DB datasource 정보 수정이 필요합니다) 
## Jar 파일 실행 시 
1) openjdk 18 설치
 
 
 
# 설계 내용
## 설계에 대한 내용은 /doc 문서에 있습니다.

## 설명
1) 도시 등록 API 관련 (관리자)
### Triple 도시는 트리플에서 관리자가 생성하는 도시입니다. 
### 1개 이상의 도시를 결합하여 사용자가 편리하게 여행지를 선택 할 수 있도록 트리플 도시를 만들 수 있습니다.

2) 여행 등록 API 관련 (사용자)
### 사용자는 관리자가 생성한 Triple 도시를 선택하여 여행지 일정을 계획 합니다.


## 데이터 베이스 설계
1) ERD 참조
2) 테이블 명세서.xlsx 참조
## 기능 설계
1) 기능 목록.xlsx 참조
