=================================<br>
-- 채팅방 대화 내용 테이블<br>
=================================<br>
CREATE SEQUENCE chat_message_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE chat_messages (
    id NUMBER PRIMARY KEY USING INDEX,
    messagetype VARCHAR2(10) NOT NULL,  -- ENTER, TALK 등
    sendtime TIMESTAMP NOT NULL DEFAULT SYSTIMESTAMP,
    chatroomid NUMBER NOT NULL,
    senderid NUMBER NOT NULL,
    message CLOB NOT NULL,
    reported VARCHAR2(3)  -- YES 또는 NO
);

CREATE OR REPLACE TRIGGER chat_messages_id_trigger
BEFORE INSERT ON chat_messages
FOR EACH ROW
BEGIN
  SELECT chat_message_id_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/

=================================<br>
-- 채팅방 정보 테이블<br>
=================================<br>
CREATE SEQUENCE chat_room_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE chat_rooms (
    id NUMBER PRIMARY KEY USING INDEX,
    roomtitle VARCHAR2(50) NOT NULL,
    roomdescription VARCHAR2(100) NOT NULL,
    currentcount NUMBER DEFAULT 0,  -- 현재 참여 인원
    maxamount NUMBER DEFAULT 10,  -- 최대 참여 가능 인원
    location VARCHAR2(200),
    creation_date DATE DEFAULT SYSDATE,
    category VARCHAR2(20),
    leaderid NUMBER NOT NULL
);

CREATE OR REPLACE TRIGGER chat_rooms_id_trigger
BEFORE INSERT ON chat_rooms
FOR EACH ROW
BEGIN
  SELECT chat_room_id_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/

=================================<br>
-- 유저 채팅방 정보 테이블<br>
=================================<br>
CREATE TABLE chat_room_members (
    chat_room_id NUMBER NOT NULL,
    userid NUMBER NOT NULL,
    CONSTRAINT fk_chat_room FOREIGN KEY (chatroomid) REFERENCES chatrooms (id),
    CONSTRAINT fk_user FOREIGN KEY (userid) REFERENCES MemberDB (userid),
    CONSTRAINT pk_chat_room_members PRIMARY KEY (chatroomid, userid)
);
