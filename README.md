
# 📘 일정 관리 앱 - REST API 명세서

---

## 📘 API Table
| 기능           | Method | URL                          | request                                                                                      | response                                                                                      | 상태코드         |
|----------------|--------|------------------------------|-----------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|------------------|
| 일정 생성      | POST   | /api/ch3/schedules           | `{ "title": "스터디 하기", "contents": "Spring 복습", "name": "sun", "pw": "1234", "email": "1234@naver.com" }` | `{ "id": 1, "title": "스터디 하기", "contents": "Spring 복습", "name": "sun", "createDate": "...", "updateDate": "..." }` | 201 Created      |
| 전체 일정 조회 | GET    | /api/ch3/schedules           | -                                                                                             | `[ { "id": 1, "name": "sun", "title": "스터디 하기", "contents": "내용", "updateDate": "..." } ]` | 200 OK           |
| 선택 일정 조회 | GET    | /api/ch3/schedules/{id}      | -                                                                                             | `{ "id": 1, "title": "스터디 하기", "contents": "내용", "name": "sun", "updateDate": "..." }`   | 200 OK           |
| 일정 수정      | PATCH  | /api/ch3/schedules/{id}      | `{ "title": "운동하기", "contents": "필라테스 수업", "name": "sun", "pw": "1234" }`            | `{ "message": "일정이 성공적으로 수정되었습니다." }`                                           | 200 OK           |
| 일정 삭제      | DELETE | /api/ch3/schedules/{id}      | `{ "pw": "1234" }`                                                                            | `{ "message": "일정이 삭제되었습니다." }`                                                      | 200 OK           |


## ✅ 1. 일정 생성 (Create)

- **Method**: `POST`
- **URL**: `/api/schedules`
- **설명**: 새로운 일정을 생성합니다
- **요청 헤더**: `Content-Type: application/json`

### 📥 요청 Body 예시
```json
{
  "title": "스터디 하기",
  "contents": "Spring 복습",
  "name": "sun",
  "pw": "1234",
  "email": "abc@naver.com"
}
```

### 📤 응답 (201 Created)
```json
{
  "id": 1,
  "title": "스터디 하기",
  "contents": "Spring 복습",
  "name": "sun",
  "createDate": "2025-03-17T20:00:00",
  "updateDate": "2025-03-17T20:00:00"
}
```

---

## ✅ 2. 전체 일정 조회 (List)

- **Method**: `GET`
- **URL**: `/api/schedules`
- **설명**: 전체 일정 조회 (작성자명, 수정일 조건 필터 가능)
- **쿼리 파라미터**: `name`, `updateDate=YYYY-MM-DD`
- **예시**: `/api/schedules?name=sun&updateDate=2025-03-17`

### 📤 응답 (200 OK)
```json
[
  {
    "id": 1,
    "title": "스터디 하기",
    "contents": "Spring 복습",
    "name": "sun",
    "updateDate": "2025-03-17T20:00:00"
  }
]
```

---

## ✅ 3. 특정 일정 조회 (Read)

- **Method**: `GET`
- **URL**: `/api/schedules/{id}`
- **설명**: 특정 일정(ID)의 상세 정보를 조회합니다
- **예시**: `/api/schedules/1`

### 📤 응답 (200 OK)
```json
{
  "id": 1,
  "title": "스터디 하기",
  "contents": "Spring 복습",
  "name": "sun",
  "createDate": "2025-03-17T20:00:00",
  "updateDate": "2025-03-17T20:00:00"
}
```

---

## ✅ 4. 일정 수정 (Update)

- **Method**: `PATCH`
- **URL**: `/api/schedules/{id}`
- **설명**: 특정 일정의 `title`, `contents`, `name`을 수정합니다

### 📥 요청 Body 예시
```json
{
  "title": "운동하기",
  "contents": "필라테스 수업",
  "name": "sun",
  "pw": "1234"
}
```

### 📤 응답 (200 OK)
```json
{
  "message": "일정이 성공적으로 수정되었습니다."
}
```

---

## ✅ 5. 일정 삭제 (Delete)

- **Method**: `DELETE`
- **URL**: `/api/schedules/{id}`
- **설명**: 특정 일정을 삭제합니다

### 📥 요청 Body 예시
```json
{
  "id": "1", 
  "pw": "1234"
}
```

### 📤 응답 (200 OK)
```json
{
  "message": "일정이 삭제되었습니다."
}
```

---

## 🧾 상태 코드 요약

| 상태 코드 | 설명 |
|-----------|------|
| `200 OK` | 요청 성공 |
| `201 Created` | 리소스 생성 성공 |
| `400 Bad Request` | 잘못된 요청 |
| `401 Unauthorized` | 인증 실패 |
| `404 Not Found` | 일정이 없음 |
| `500 Internal Server Error` | 서버 오류 |


# 📘 ERD 설계 - 일정 관리 앱

## 👤 Users Table
| 컬럼명   | 타입 | 설명                    |
|-------|------|-----------------------|
| id    | BIGINT (Auto Increment) | 기본키 (PK), 각 유저 고유 식별자 |
| name  | VARCHAR | 작성자명                  |
| pw    | VARCHAR | 비밀번호                  |
| email | VARCHAR | 이메일                   |

---

## 📅 Schedule Table
| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| id | BIGINT | 일정 고유 식별자 (PK) |
| user_id | BIGINT | 외래키 (FK) → Users.id |
| title | VARCHAR(200) | 할 일 제목 |
| contents | VARCHAR(500) | 할 일 설명 |
| create_date | DATETIME | 생성일 (작성일) |
| update_date | DATETIME | 수정일 (최초 생성 시 작성일과 동일) |

---

## 🔗 테이블 간 관계 (Relationship)

```
Users 1 ─────────────< 0..* Schedule
```

- 하나의 유저는 **0개 이상의 일정을 가질 수 있음** (`Zero or Many`)
- 일정은 반드시 하나의 유저에게 **속해야 함**
- 외래키: `Schedule.user_id → Users.id`

---

## 💡 비즈니스 조건 정리

- 유저는 일정을 안 만들 수도 있고, 여러 개 만들 수도 있음
- 일정 생성 시 작성일/수정일 자동 설정
- 수정 시 비밀번호 검증 필요