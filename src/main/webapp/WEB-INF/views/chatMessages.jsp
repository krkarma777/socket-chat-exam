<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채팅 메시지 테스트</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        var stompClient = null;

        function connect() {
            var socket = new SockJS('/chat-socket');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/messages', function (messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body));
                });
            });
        }

        function disconnect() {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            console.log("Disconnected");
        }

        function sendMessage() {
            var chatRoomId = $('#chatRoomId').val();
            var messageContent = $('#messageContent').val();
            var senderId = $('#senderId').val();
            var messageType = $('#messageType').val();
            stompClient.send("/app/chat/send", {}, JSON.stringify({
                'chatRoomId': chatRoomId,
                'senderId': senderId,
                'message': messageContent,
                'messageType': messageType
            }));
        }

        function showMessageOutput(messageOutput) {
            $("#messages").append("<tr><td>" + messageOutput.chatRoomId + "</td><td>" + messageOutput.message + "</td></tr>");
        }

        $(function () {
            $("form").on('submit', function (e) {
                e.preventDefault();
            });
            $( "#connect" ).click(function() { connect(); });
            $( "#disconnect" ).click(function() { disconnect(); });
            $( "#send" ).click(function() { sendMessage(); });
        });
    </script>
</head>
<body>
<h2>Chat Message WebSocket</h2>
<form>
    <div>
        <label for="chatRoomId">채팅방 ID:</label>
        <input type="text" id="chatRoomId" required/>
        <label for="senderId">보내는 유저 ID:</label>
        <input type="text" id="senderId" required/>
        <label for="messageContent">메세지:</label>
        <input type="text" id="messageContent" required/>
        <!-- 메시지 유형 선택 추가 -->
        <select id="messageType">
            <option value="ENTER">입장</option>
            <option value="TALK">채팅</option>
        </select>

    </div>
    <div>
        <button id="connect">접속</button>
        <button id="disconnect" disabled="disabled">나가기</button>
        <button id="send">메세지 보내기</button>
    </div>
</form>
<table id="messages">
    <tr>
        <th>채팅방 ID</th>
        <th>메세지</th>
    </tr>
</table>
</body>
</html>
