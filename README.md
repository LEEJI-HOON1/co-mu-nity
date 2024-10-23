# CO-MU-NITY 
# 일정 관리 앱 2
## 기능목록
- 모든 테이블은 고유 식별자(ID)를 가진다
- `3 Layer Architecture` 에 따라 각 Layer의 목적에 맞게 개발
- CRUD 필수 기능은 모두 데이터베이스 연결 및 `JPA`를 사용해서 개발
- JPA 연관관계는 `양방향`으로 구현
- 인증/인가 절차는 `JWT`를 활용하여 개발.


### 멤버
- 멤버 저장(회원가입, 로그인), 조회(전체), 수정, 삭제

### 게시글

- 게시글 저장, 조회(전체, 선택), 수정, 삭제
- 기본 정렬은 생성일자 기준으로 내림차순 정렬
- 10개씩 페이지네이션하여, 각 페이지 당 뉴스피드 데이터가 10개씩 나옴

### 댓글
- 댓글 생성, 조회(전체), 수정, 삭제

### 팔로워
- 팔로워, 조회(전체), 삭제

## API 명세서
- POST 회원가입
  - https://postman-rest-api-learner.glitch.me//info
- POST 로그인
  -  https://postman-rest-api-learner.glitch.me//info
- PUT 게시글 수정
  -  https://postman-rest-api-learner.glitch.me//info?id=1
- DELETE 게시글 삭제
  -  https://postman-rest-api-learner.glitch.me//info?id=1
- POST 게시글 생성
  -  https://postman-rest-api-learner.glitch.me//info
-  GET 게시글 조회
  -  http://localhost:8080/api/boards
-  GET 게시글 상세 조회
  -  http://localhost:8080/api/boards/3
-  POST 댓글 생성
  -  https://postman-rest-api-learner.glitch.me//info
-  GET 댓글 조회
  -  http://localhost:8080/api/boards/4/comments
-  PUT 댓글 수정
  -  http://localhost:8080/api/boards/4/comments
-  DELETE 댓글 삭제
  -  http://localhost:8080/api/boards/4/comments


## ERD
![erd](img/erd.png)