## 일정 API


# 일정 관리 API 명세서

## 기본 정보

- **Base URL**: `http://localhost:8080`
- **Content-Type**: `application/json`
- **ERD**: https://www.erdcloud.com/d/PjLKZtNY84gpkD7fp

---

## 일정 API

### 1. 일정 등록

**Request**

- **Method**: `POST`
- **URL**: `/api/schedules`

```json
{
  "title": "회의",
  "content": "팀 회의",
  "userId": 1
}
```

**Response** (201 Created)

```json
{
  "id": 10,
  "title": "회의",
  "content": "팀 회의",
  "userId": 1,
  "createdAt": "2026-04-17T10:00:00"
}
```

**Error Response**

- `400 Bad Request`: Validation 실패
- `404 Not Found`: 존재하지 않는 사용자

---

### 2. 일정 전체 조회

**Request**

- **Method**: `GET`
- **URL**: `/api/schedules`

**Response** (200 OK)

```json
[
  {
    "id": 1,
    "title": "회의",
    "content": "팀 회의",
    "createdAt": "2026-04-17T10:00:00"
  },
  {
    "id": 2,
    "title": "공부",
    "content": "Spring",
    "createdAt": "2026-04-17T11:00:00"
  }
]
```

---

### 3. 일정 단건 조회

**Request**

- **Method**: `GET`
- **URL**: `/api/schedules/{id}`
- **예시**: `/api/schedules/1`

**Response** (200 OK)

```json
{
  "id": 1,
  "title": "회의",
  "content": "팀 회의",
  "createdAt": "2026-04-17T10:00:00"
}
```

**Error Response**

- `404 Not Found`: 존재하지 않는 일정

---

### 4. 일정 수정

**Request**

- **Method**: `PUT`
- **URL**: `/api/schedules/{id}`
- **예시**: `/api/schedules/1`

```json
{
  "title": "회의 수정",
  "content": "팀 회의 내용 변경"
}
```

**Response** (200 OK)

```json
{
  "id": 1,
  "title": "회의 수정",
  "content": "팀 회의 내용 변경",
  "updatedAt": "2026-04-17T15:00:00"
}
```

**Error Response**

- `400 Bad Request`: Validation 실패
- `404 Not Found`: 존재하지 않는 일정

---

### 5. 일정 삭제

**Request**

- **Method**: `DELETE`
- **URL**: `/api/schedules/{id}`
- **예시**: `/api/schedules/1`

**Response** (200 OK)

```json
{
  "message": "삭제 완료"
}
```

**Error Response**

- `404 Not Found`: 존재하지 않는 일정

---

## 유저/인증 API

### 6. 회원가입

**Request**

- **Method**: `POST`
- **URL**: `/api/users/join`

```json
{
  "loginId": "maea",
  "password": "password123!",
  "email": "maea@example.com"
}
```

**Response** (201 Created)

```json
{
  "id": 1,
  "loginId": "maea",
  "email": "maea@example.com",
  "createdAt": "2026-04-17T10:00:00"
}
```

**Error Response**

- `400 Bad Request`: Validation 실패
- `409 Conflict`: 이미 존재하는 로그인 ID

---

### 7. 로그인

**Request**

- **Method**: `POST`
- **URL**: `/api/users/login`

```json
{
  "loginId": "maea",
  "password": "password123!"
}
```

**Response** (200 OK)

```json
{
  "message": "로그인 성공",
  "userId": 1
}
```

**Error Response**

- `400 Bad Request`: 로그인 정보가 올바르지 않습니다

---

## 댓글 API

### 8. 댓글 등록

**Request**

- **Method**: `POST`
- **URL**: `/api/schedules/{scheduleId}/comments`
- **예시**: `/api/schedules/1/comments`

```json
{
  "content": "좋은 일정이네요!",
  "userId": 1
}
```

**Response** (201 Created)

```json
{
  "id": 100,
  "content": "좋은 일정이네요!",
  "scheduleId": 1,
  "userId": 1,
  "createdAt": "2026-04-17T15:00:00"
}
```

**Error Response**

- `400 Bad Request`: Validation 실패
- `404 Not Found`: 존재하지 않는 일정 또는 사용자

---

### 9. 댓글 조회 (특정 일정의 댓글 전체)

**Request**

- **Method**: `GET`
- **URL**: `/api/schedules/{scheduleId}/comments`
- **예시**: `/api/schedules/1/comments`

**Response** (200 OK)

```json
[
  {
    "id": 100,
    "content": "좋은 일정이네요!",
    "userId": 1,
    "createdAt": "2026-04-17T15:00:00"
  },
  {
    "id": 101,
    "content": "감사합니다",
    "userId": 2,
    "createdAt": "2026-04-17T16:00:00"
  }
]
```

---

### 10. 댓글 수정

**Request**

- **Method**: `PUT`
- **URL**: `/api/comments/{id}`
- **예시**: `/api/comments/100`

```json
{
  "content": "댓글 내용 수정합니다"
}
```

**Response** (200 OK)

```json
{
  "id": 100,
  "content": "댓글 내용 수정합니다",
  "updatedAt": "2026-04-17T17:00:00"
}
```

**Error Response**

- `400 Bad Request`: Validation 실패
- `404 Not Found`: 존재하지 않는 댓글

---

### 11. 댓글 삭제

**Request**

- **Method**: `DELETE`
- **URL**: `/api/comments/{id}`
- **예시**: `/api/comments/100`

**Response** (200 OK)

```json
{
  "message": "댓글 삭제 완료"
}
```

**Error Response**

- `404 Not Found`: 존재하지 않는 댓글

---

## 에러 응답 형식

모든 에러는 다음 형식으로 반환됩니다:

```json
{
  "message": "에러 메시지"
}
```

### 주요 에러 코드

- `400 Bad Request`: 잘못된 요청 (Validation 실패 등)
- `404 Not Found`: 리소스를 찾을 수 없음
- `409 Conflict`: 리소스 중복

---

## 추가 API (구현됨)

### 사용자 일정 조회

**Request**

- **Method**: `GET`
- **URL**: `/api/schedules/user/{userId}`

### 최신 일정 조회

**Request**

- **Method**: `GET`
- **URL**: `/api/schedules/recent`

### 페이징 조회 (도전과제)

**Request**

- **Method**: `GET`
- **URL**: `/api/schedules/paged`
- **Query Parameters**:
    - `page`: 페이지 번호 (0부터 시작)
    - `size`: 페이지 크기
    - `sort`: 정렬 기준


src/main/java/com/nodeajva/ch3schedule/
├── Entity/
│   ├── BaseEntity.java      # 공통 시간 필드 (JPA Auditing)
│   ├── User.java            # 사용자 엔티티
│   ├── Schedule.java        # 일정 엔티티
│   └── Comment.java         # 댓글 엔티티
├── controller/
│   ├── UserController.java       # 사용자 API
│   ├── ScheduleController.java   # 일정 API
│   └── CommentController.java    # 댓글 API
├── service/
│   ├── UserService.java          # 사용자 비즈니스 로직
│   ├── ScheduleService.java      # 일정 비즈니스 로직
│   └── CommentService.java       # 댓글 비즈니스 로직
├── repository/
│   ├── UserRepository.java       # 사용자 DB 접근
│   ├── ScheduleRepository.java   # 일정 DB 접근
│   └── CommentRepository.java    # 댓글 DB 접근
├── dto/
│   ├── request/                  # 요청 DTO
│   └── response/                 # 응답 DTO
├── exception/                    # 커스텀 예외
└── config/
└── PasswordEncoder.java      # 비밀번호 암호화 설정

## 주요 클래스 설명

### Entity
- **BaseEntity**: JPA Auditing으로 `createdAt`, `updatedAt` 자동 관리
- **User**: 사용자 정보 (로그인ID, 비밀번호, 이름, 이메일)
- **Schedule**: 일정 정보 (제목, 내용, 작성자)
- **Comment**: 댓글 정보 (내용, 일정, 작성자)

### Service
- **@Transactional(readOnly = true)**: 클래스 기본값 (조회 최적화)
- **@Transactional**: 변경 작업 (INSERT/UPDATE/DELETE)
- BCrypt로 비밀번호 암호화

### Controller
- **@RestController**: REST API 컨트롤러
- **@Valid**: 요청 검증
- **@PathVariable**, **@RequestBody**: 파라미터 매핑


