## 📌 스프링 배치 리팩토링 프로젝트입니다.
<br />
<br />

## 📌 기존 배치 구성 및 사용 기술
<br />

```bash
기존 스크래핑 배치 서비스는 java8, Vert.x, MyBatis, Redis, Linux cron, PHP, Mysql로 구성되어 있다.
```


## 📌 현재 회사의 배치 프로그램에 관련한 내 생각
https://dongjun6343.github.io/2023-10-19/TIL


<br />

## 📌 리팩토링 진행 - 사용 기술 스택

```bash
- java17
- SpringBoot 3.x
- JPA
- QueryDsl
- Spring Batch 5.x
- Spring Scheduler
- Mysql
- Teams Webhook
- NHN Cloud
```

<br />

## 📌 리팩토링 효과 (Refactoring Effect)

```bash
레거시한 부분이 많아 유지보수와 확장성 측면에서 어려움이 있다.
소스 코드의 가독성과 유지보수성 향상, 누락되는 문제 개선, 배치 작업을 효율적으로 처리할 수 있도록 하자!

+ 배치 성공 여부를 알 수 있도록 Teams Webhook 연동 ( 모니터링 추가 )


ci/cd 구축도 필요하다! (진행예정)
```

<br />

## 📌 nhn 클라우드 배치 서버 구축

<br />

```bash
운영환경 & 백그라운드에서 실행
nohup /data/apps/rnbatch/jdk-17.0.2/bin/java -Dspring.profiles.active=prod -jar rn-delivery-batch.jar &

서버 실행,중단 start.sh, stop.sh에 추가
```
<br />
<br />

## 📌 jar 프로세스 확인

`ps -ef | grep jar` 명령어 확인


<br />
<br />


## 📌 Job 관련 로그 확인

`tail -f rn-delivery-batch.log` 명령어 확인

<br />
<br />


## 📌 Teams Webhook 연동 방법

https://learn.microsoft.com/en-us/connectors/teams/?tabs=text1%2Cdotnet#adaptivecarditemschema

```bash
추가예정
```

## 📌 Teams Webhook 연동 후 모니터링

![image](https://github.com/user-attachments/assets/b06578d3-ce59-449d-8a23-4270dd8b1041)


## 📌 todolist
```bash
1. aws서버로 이관 (진행중)
2. ci/cd 구축 필요 (진행중)
3. Chunk vs Tasklet
 - 대용량 처리가 아니므로 코드가 간결하고 이해하기 쉬운 Tasklet 사용
4. webhook을 통해서 배치 오류가 발생했을때 팀즈 알림 추가 (팀즈 연동 완료) 
 - 배치 오류를 개발자가 빠르게 확인하기 위함

5. SimpleJob vs FlowJob
 - 현재는 부가세 정보만 배치로 저장하고 있어서 SimpleJob을 사용하고 있지만
   추후 부가세 뿐만 아니라, 매출,통계,수수로,리뷰 등 다양한 정보를 처리하려면
   FlowJob으로 변경해서 복잡한 비즈니스 로직, 조건부 실행이 가능하도록 하자

```
