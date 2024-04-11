<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채팅방 생성 테스트</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            // 채팅방 생성
            $('#createRoom').click(function() {
                var roomTitle = $('#roomTitle').val();
                var roomDescription = $('#roomDescription').val();
                var maxAmount = $('#maxAmount').val();
                var location = $('#location').val();
                var category = $('#category').val();
                var leaderId = $('#leaderId').val();

                $.ajax({
                    url: '/api/chatroom',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        "roomTitle": roomTitle,
                        "roomDescription": roomDescription,
                        "maxAmount": maxAmount,
                        "location": location,
                        "category": category,
                        "leaderId": leaderId
                    }),
                    success: function(response) {
                        alert(response);
                        $('#getAllRooms').click();  // 채팅방 목록 갱신
                    },
                    error: function(xhr) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            });

            // 채팅방 목록 조회
            $('#getAllRooms').click(function() {
                $.ajax({
                    url: '/api/chatroom',
                    type: 'GET',
                    success: function(response) {
                        var rooms = response;
                        var html = '';
                        rooms.forEach(function(room) {
                            html += '<div>' + room.id + ' - ' + room.roomTitle + ' - <button onclick="deleteRoom(' + room.id + ')">Delete</button></div>';
                        });
                        $('#roomList').html(html);
                    },
                    error: function(xhr) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            });

            // 채팅방 삭제 함수
            window.deleteRoom = function(id) {
                $.ajax({
                    url: '/api/chatroom/' + id,
                    type: 'DELETE',
                    success: function(response) {
                        alert(response);
                        $('#getAllRooms').click();  // 채팅방 목록 갱신
                    },
                    error: function(xhr) {
                        alert('Error: ' + xhr.responseText);
                    }
                });
            };
        });
    </script>
</head>
<body>
<h2>채팅방 테스트</h2>
<div>
    <input type="text" id="roomTitle" placeholder="방 이름" /><br>
    <input type="text" id="roomDescription" placeholder="방 설명" /><br>
    <input type="number" id="maxAmount" placeholder="최대 접속 인원" /><br>
    <input type="text" id="location" placeholder="지역" /><br>
    <input type="text" id="category" placeholder="카테고리" /><br>
    <input type="text" id="leaderId" placeholder="방장 id" /><br>
    <button id="createRoom">방 생성</button><br>
</div>
<div>
    <button id="getAllRooms">전체 채팅방 보기</button>
</div>
<div id="roomList"></div>
</body>
</html>