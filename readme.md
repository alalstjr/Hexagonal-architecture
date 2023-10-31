# 헥사고날

## 헥사고날 아키텍처

기존에는 mysql 로 연결되어 있었지만 mongodb 로 바꾸는 작업을 진행한다.

## 사전준비

### QueryDSL 사전준비

Gradle -> Tasks -> other -> compileJava 실행해서 Qfile 생성

### API

- 맥북을 저장
  - [POST] http://localhost:8080/macbook
- 저장된 맥북을 조회
  - [GET] http://localhost:8080/macbook

### MongoDB

데이터 확인 명령어

~~~
db.MACBOOK.find();
db.BATTERY.find();
~~~

### 상황 발생

사용자의 요청으로 JPA-MySQL 에서 MongoDB 로 전환해야 하는 상황  
개발자는 비즈니스 코드에 피해를 주지 않고도 기술 코드를 변경해야합니다.  
DB 를 변경해야하고 외부 기술이므로 Framework 헥사고날의 변경이 필요하므로 `framework.adapter` 에서 작업한다.  
out 패키지에 mongodb 기능 코드를 구현하고 `MacBookMongoAdapter` 에 `MacBookManagementOutPort` 연결하여 mongodb 어뎁터 연결  
사용하려는 어뎁터 구현체에 `@Primary` 어노테이션을 추가하여 Bean 주입받도록 설정  

이렇게 되면 최종적으로 `framework.adapter` 의 변경만으로 DB 를 교체할 수 있었다.  
비즈니스 로직에 영향이 가지 않았다.

### 헤결 방안

어플리케이션 헥사곤 (서비스), 도메인 헥사곤 의 변경 없이 외부 통신을 모아두는 Framework 어댑터에 MongoDB 를 구축하고 Application out port 만 연결해서 기능을 구현한다.  

`com.example.hexagonal.framework.adapter.out` 위치에 mongodb 패키지를 만들고 구현합니다.  
기존 jpa 패키지는 지워도 되지만 그냥 두는 경우 MacBookMongoAdapter.class 에 @Primary 어노테이션을 추가해서 해당 어뎁터가 먼저 실행되도록 하고 테스트한다.  

`application.yaml` profiles 그룹의 설정을 mongodb 로 바꾼다. 

## MVC 아키텍처

### 상황 발생

사용자의 요청으로 JPA-MySQL 에서 MongoDB 로 전환해야 하는 상황  
JPA-MySQL 을 완전 삭제가 아닌 추 후 에 다시 돌아갈 수 도 있는 상황임을 인지해야 합니다.  

MVC 아키텍처의 경우 전환시 비즈니스 코드를 (Service) 수정해야함  
Service 와 JPA Entity 가 서로 의존하고 있기 때문이다.

### 헤결 방안

domain entity 의 설정을 바꾸고 repository 를 바꾸고 service 의 영역도 바꿔야합니다.  
이는 기술 로직이 서비스 로직에 영향을 미치게 되므로 프로젝트 규모가 커질수록 유지보수에 어려움이 생길 수 있습니다.