<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat Room Members Management</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            // 채팅방 접속
            $('#joinRoom').click(function() {
                var chatRoomId = $('#chatRoomId').val();
                var userId = $('#userId').val();
                $.ajax({
                    url: '/api/chatroom-members/join',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ "chatRoomId": chatRoomId, "userId": userId }),
                    success: function(response) {
                        alert(response);
                    },
                    error: function(xhr) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            });

            // 채팅방 떠나기
            $('#leaveRoom').click(function() {
                var chatRoomId = $('#chatRoomId').val();
                var userId = $('#userId').val();
                $.ajax({
                    url: '/api/chatroom-members/leave',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ "chatRoomId": chatRoomId, "userId": userId }),
                    success: function(response) {
                        alert(response);
                    },
                    error: function(xhr) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            });

            // 채팅방 멤버 보기
            $('#getMembers').click(function() {
                var chatRoomId = $('#chatRoomId').val();
                $.ajax({
                    url: '/api/chatroom-members/room/' + chatRoomId,
                    type: 'GET',
                    success: function(response) {
                        var members = response;
                        var html = '<ul>';
                        members.forEach(function(member) {
                            html += '<li>User ID: ' + member.userId + '</li>';
                        });
                        html += '</ul>';
                        $('#membersList').html(html);
                    },
                    error: function(xhr) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            });
        });
    </script>
</head>
<body>
<h2>채팅방 접속 테스트</h2>
<div>
    <input type="text" id="chatRoomId" placeholder="채팅방 id" />
    <input type="text" id="userId" placeholder="회원 id" />
    <button id="joinRoom">방 접속</button>
    <button id="leaveRoom">방 떠나기</button>
    <button id="getMembers">채팅방 회원보기</button>
</div>
<div id="membersList"></div>
</body>
</html>
