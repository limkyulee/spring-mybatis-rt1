# Admin Core API

Spring Boot REST API for Common Admin Management (User, Member, WebSocket)

### ⚙️ Setting

1. `.env` 파일 루트 경로에 생성 후 DB 정보 입력 
    - `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
2. schema.sql 파일 실행 오류 시, 테이블 수동 생성
   - `USER`, `MEMBER`

### Tech
- Java 21
- Spring Boot 3.1.x
- MyBatis
- MySQL
- Gradle
- Lombok

### Contents

- [X] 회원가입
- [X] 로그인/JWT 인증
- [X] 사용자관리 CRUD
- [X] GlobalExceptionHandler
- [X] BizException
- [ ] WebSocket
- [ ] Redis

