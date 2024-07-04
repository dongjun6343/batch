# 기존 배치 소스를 리팩토링 해보자!


### 기존 스크래핑 배치 서비스 : java8 , Vert.x , Redis, Linux cron , PHP

### 리팩토링 진행중 : java17 , SpringBoot 3.x , JPA, QueryDsl, Spring Batch 5.x 


#### 레거시한 부분이 많아 유지보수와 확장성 측면에서 어려움이 있다.

#### => 소스 코드의 가독성과 유지보수성 향상, 누락되는 문제 개선, 배치 작업을 효율적으로 처리할 수 있도록 하자!


### nhn 클라우드에 배치 서버 구축(추후, aws서버로 이관 후 ci/cd 구축)
![image](https://github.com/READNUMBER/rn-admin-web/assets/45116087/427a8f19-2f25-4194-9185-2187d8808ddb)

![image](https://github.com/dongjun6343/batch/assets/45116087/5adb7074-1069-4cf5-b1f9-eaa4c2520ff4)


### nhn서버 -> aws서버 이관 (진행중)

### ci/cd 구축 (예정)
