## 📌 기존 배치 관련 리팩토링 프로젝트입니다.
<br />
<br />

## 📌 기존 배치 구성 및 사용 기술
<br />
<br />
<br />
<img width="1078" alt="image" src="https://github.com/user-attachments/assets/9ddbfa10-d92a-49e8-949c-159f23c1d6de">
<div align="center">
<br />
<br />
</div>
<br />


## 📌 현재 회사의 배치 프로그램에 관련한 내 생각
https://dongjun6343.github.io/2023-10-19/TIL

<br />
<br />

## 📌 기존 스크래핑 배치 서비스

```bash
기존 스크래핑 배치 서비스는 java8, Vert.x, MyBatis, Redis, Linux cron, PHP, Mysql로 구성되어 있다.
```

<br />

## 📌 리팩토링 진행중 

```bash
java17 , SpringBoot 3.x , JPA, QueryDsl, Spring Batch 5.x, Spring Scheduler, Mysql로 리팩토링 하자!
```

<br />

## 📌 리팩토링 효과 (Refactoring Effect)

```bash
레거시한 부분이 많아 유지보수와 확장성 측면에서 어려움이 있다.
소스 코드의 가독성과 유지보수성 향상, 누락되는 문제 개선, 배치 작업을 효율적으로 처리할 수 있도록 하자!

2024.07.19
ci/cd 구축도 필요하다!
```

<br />

## 📌 nhn 클라우드 배치 서버 구축
![image](https://github.com/user-attachments/assets/efd0e40c-24ee-43f1-b1b9-42955723a2b2)

<br />
<br />

## 📌 jar 프로세스 확인

`ps -ef | grep jar` 명령어 확인
<br />
<img width="1563" alt="image" src="https://github.com/user-attachments/assets/70509503-d1f1-4bc0-b88c-1483d7649837">

<br />
<br />


## 📌 Job 관련 로그 확인

`tail -f rn-delivery-batch.log` 명령어 확인
<br />
![image](https://github.com/user-attachments/assets/4b5b59e8-591d-4fed-9347-e06cfab5052d)

<br />
<br />


## 📌 todolist
```bash
1. aws서버로 이관
2. ci/cd 구축 필요
3. chunk 사용
4. webhook을 통해서 배치 오류가 발생했을때 팀즈 알림 추가
 - 배치 오류를 개발자가 빠르게 확인하기 위함
```
