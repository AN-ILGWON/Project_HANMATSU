# 🌸 Hanmatsu 포트폴리오 PPT 제작 스크립트 🌸

## Slide 1: 타이틀
- **제목**: 韓まつ (Hanmatsu) - 한일 축제 및 문화 교류 플랫폼
- **부제**: JSP/Servlet 기반의 하이브리드 커뮤니티 및 정보 공유 서비스
- **발표자**: [본인 이름]

## Slide 2: 개발 목적 및 배경
- **배경**: 일본 문화 및 축제(Matsuri)에 대한 관심 증가에도 불구하고, 파편화된 정보를 한눈에 확인하고 교류할 수 있는 통합 플랫폼 부재.
- **목적**:
  - 한국어/일본어 사용자 모두가 즐길 수 있는 축제 정보의 데이터베이스화.
  - 사용자 간 후기 공유 및 상호작용이 가능한 커뮤니티 구축.
  - 관리자 전용 기능을 통한 체계적인 콘텐츠 운영 시스템 구현.

## Slide 3: 기술 스택 (Tech Stack)
- **Language**: Java 11 (LTS)
- **Web**: JSP 2.3, Servlet 4.0, JSTL 1.2
- **Backend Architecture**: Command Pattern 기반 MVC 아키텍처
- **Frontend**: HTML5, CSS3, JavaScript (jQuery 3.7.1)
- **Database**: Oracle Database 11g/19c (XE)
- **Server**: Apache Tomcat 9.0
- **Library**: jBCrypt (비밀번호 암호화), cos.jar (파일 업로드), JSON

## Slide 4: 요구사항 및 주요 기능
- **User Side**:
  - 회원가입/로그인 (BCrypt 암호화, ID 중복 체크)
  - 축제 리스트/상세 정보 조회 및 '찜하기' 기능
  - 커뮤니티 (이미지 업로드, 게시글 CRUD, 좋아요, 댓글)
  - 마이페이지 (개인정보 수정, 찜한 목록 확인)
- **Admin Side**:
  - 배너/뉴스/축제 콘텐츠 통합 관리
  - 회원 목록 관리 및 게시물 모니터링

## Slide 5: 기술적 해결 사례 (Technical Challenges)
- **Issue**: Multipart Form 데이터 전송 시 일반 텍스트 파라미터 유실 문제.
- **Solution**: `cos.jar` 라이브러리 및 `@MultipartConfig` 설정을 통한 파일/텍스트 데이터 통합 처리 유틸리티 구현.
- **Security**: 단방향 해시 함수인 `BCrypt`를 적용하여 사용자 비밀번호 보안 강화.

## Slide 6: 향후 계획
- Spring Boot 프레임워크로의 마이그레이션.
- 소셜 로그인(Kakao, Google) 및 공공데이터 API 연동.

---

# 📊 상세 테이블 구성 (Database Schema)

### 1. 회원 테이블 (`hm_member`)
| 컬럼명 | 타입 | 제약조건 | 설명 |
| :--- | :--- | :--- | :--- |
| `userid` | VARCHAR2(50) | PK | 사용자 아이디 |
| `password` | VARCHAR2(200) | NOT NULL | 암호화된 비밀번호 |
| `nickname` | VARCHAR2(50) | UNIQUE, NOT NULL | 사용자 닉네임 |
| `email` | VARCHAR2(100) | NOT NULL | 이메일 주소 |
| `phone` | VARCHAR2(20) | - | 전화번호 |
| `last_name_kanji` | VARCHAR2(50) | NOT NULL | 성 (한자/이름) |
| `first_name_kanji`| VARCHAR2(50) | NOT NULL | 이름 (한자/이름) |
| `role` | VARCHAR2(20) | DEFAULT 'USER' | 권한 (USER, ADMIN) |
| `regdate` | DATE | DEFAULT SYSDATE | 가입일 |

### 2. 축제 정보 테이블 (`hm_festival`)
| 컬럼명 | 타입 | 제약조건 | 설명 |
| :--- | :--- | :--- | :--- |
| `fno` | NUMBER | PK | 축제 번호 (시퀀스) |
| `region` | VARCHAR2(50) | NOT NULL | 지역 분류 |
| `name` | VARCHAR2(200) | NOT NULL | 축제 명칭 |
| `description` | CLOB | - | 상세 설명 |
| `start_date` | DATE | - | 시작일 |
| `end_date` | DATE | - | 종료일 |
| `location` | VARCHAR2(200) | - | 개최 장소 |
| `imgfile` | VARCHAR2(500) | - | 축제 대표 이미지 |
| `likes` | NUMBER | DEFAULT 0 | 좋아요 수 |

### 3. 커뮤니티 게시판 테이블 (`hm_board`)
| 컬럼명 | 타입 | 제약조건 | 설명 |
| :--- | :--- | :--- | :--- |
| `bno` | NUMBER | PK | 게시글 번호 (시퀀스) |
| `userid` | VARCHAR2(50) | FK | 작성자 아이디 |
| `title` | VARCHAR2(200) | NOT NULL | 게시글 제목 |
| `content` | CLOB | - | 게시글 내용 |
| `views` | NUMBER | DEFAULT 0 | 조회수 |
| `category` | VARCHAR2(50) | - | 카테고리 (후기, 질문 등) |
| `imgfile` | VARCHAR2(500) | - | 첨부 이미지 |

### 4. 위시리스트 테이블 (`hm_wishlist`)
| 컬럼명 | 타입 | 제약조건 | 설명 |
| :--- | :--- | :--- | :--- |
| `wno` | NUMBER | PK | 위시 번호 (시퀀스) |
| `userid` | VARCHAR2(50) | FK | 사용자 아이디 |
| `fno` | NUMBER | FK | 축제 번호 |
| `wdate` | DATE | DEFAULT SYSDATE | 찜한 날짜 |

### 5. 게시글 좋아요 테이블 (`hm_board_like`)
| 컬럼명 | 타입 | 제약조건 | 설명 |
| :--- | :--- | :--- | :--- |
| `like_no` | NUMBER | PK | 좋아요 번호 |
| `bno` | NUMBER | FK | 게시글 번호 |
| `userid` | VARCHAR2(50) | FK | 사용자 아이디 |

---

# 🤖 Genspark AI 활용 가이드

Genspark AI에게 프로젝트를 설명하고 추가적인 도움(랜딩 페이지 제작, 홍보 문구 생성 등)을 받고 싶을 때 아래의 **[프로젝트 브리프]** 내용을 복사해서 전달해 보세요! (ﾟ∀ﾟ)

### **[Genspark 입력용 프로젝트 브리프]**

**1. 프로젝트 개요**
- **이름**: 韓まつ (Hanmatsu)
- **성격**: 한일 축제 정보 공유 및 커뮤니티 플랫폼
- **주요 타겟**: 일본 축제(마츠리)에 관심 있는 한국인 및 문화 교류 희망자

**2. 핵심 기능**
- 일본 전국 축제 데이터베이스 및 상세 정보(날짜, 장소, 이미지) 제공
- 사용자 맞춤형 '찜하기(Wishlist)' 및 '최근 본 축제' 기능
- 사진 업로드가 가능한 자유 게시판 및 좋아요/댓글 커뮤니티
- BCrypt 보안이 적용된 회원 관리 시스템

**3. 기술 스택**
- Backend: Java Servlet, JSP (MVC Model 2 Architecture)
- Database: Oracle SQL
- Frontend: JavaScript, jQuery, CSS3

**4. 요청 사항 (예시)**
- "위의 프로젝트 정보를 바탕으로 서비스의 핵심 가치를 강조하는 멋진 **랜딩 페이지**를 기획해줘."
- "이 서비스의 특징을 살린 **홍보용 슬로건** 5개를 추천해줘."
- "사용자 경험(UX)을 개선하기 위한 **추가 기능 아이디어**를 제안해줘."

---
*도움이 필요하면 언제든 말씀해 주세요! 에헤헤~ (´｡• ᵕ •｡`) ♡*
