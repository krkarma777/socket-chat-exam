# Web Chat Service Example

이 프로젝트는 WebSocket을 활용한 실시간 웹 채팅 서비스를 제공하는 예제입니다. Java, Spring Boot, Oracle Database를 사용하여 구현되었습니다.

## 기술 스택

- **JDK**: 11
- **Spring Boot**: 2.7.9
- **Database**: Oracle 11g

## 설치 방법

1. **Java 설치**: JDK 11이 설치되어 있어야 합니다.
2. **Spring Boot 프로젝트 설정**: Spring Boot 2.7.9을 기반으로 프로젝트를 설정합니다.
3. **Oracle Database 설정**: Oracle 11g 데이터베이스를 설정하고 아래의 스키마를 생성합니다.

## 데이터베이스 스키마

아래의 SQL 명령어를 사용하여 필요한 테이블과 시퀀스, 트리거를 생성합니다.

```sql
-- 시퀀스 생성
CREATE SEQUENCE chat_message_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE chat_room_id_seq START WITH 1 INCREMENT BY 1;

-- 채팅 메시지 테이블
CREATE TABLE chatmessages (
    id NUMBER PRIMARY KEY USING INDEX,
    messagetype VARCHAR2(10) NOT NULL,
    sendtime TIMESTAMP NOT NULL,
    chatroomid NUMBER NOT NULL,
    senderid VARCHAR2(50) NOT NULL,
    message CLOB NOT NULL,
    reported VARCHAR2(3)
);

-- 채팅 메시지 테이블을 위한 트리거
CREATE OR REPLACE TRIGGER chat_messages_id_trigger
BEFORE INSERT ON chatmessages
FOR EACH ROW
BEGIN
  SELECT chat_message_id_seq.NEXTVAL INTO :new.id FROM dual;
END;
/

-- 채팅방 테이블
CREATE TABLE chatrooms (
    id NUMBER PRIMARY KEY USING INDEX,
    roomtitle VARCHAR2(50) NOT NULL,
    roomdescription VARCHAR2(100) NOT NULL,
    currentcount NUMBER DEFAULT 0,
    maxamount NUMBER DEFAULT 10,
    location VARCHAR2(200),
    creationdate DATE DEFAULT SYSDATE,
    category VARCHAR2(20),
    leaderid VARCHAR2(50 BYTE) NOT NULL
);

-- 채팅방 테이블을 위한 트리거
CREATE OR REPLACE TRIGGER chat_rooms_id_trigger
BEFORE INSERT ON chatrooms
FOR EACH ROW
BEGIN
  SELECT chat_room_id_seq.NEXTVAL INTO :new.id FROM dual;
END;
/

-- 채팅방 멤버 테이블
CREATE TABLE chatroommembers (
    chatroomid NUMBER NOT NULL,
    userid VARCHAR2(50 BYTE) NOT NULL,
    CONSTRAINT fk_chat_room FOREIGN KEY (chatroomid) REFERENCES chatrooms (id),
    CONSTRAINT fk_user FOREIGN KEY (userid) REFERENCES memberdb (userid),
    CONSTRAINT pk_chat_room_members PRIMARY KEY (chatroomid, userid)
);
