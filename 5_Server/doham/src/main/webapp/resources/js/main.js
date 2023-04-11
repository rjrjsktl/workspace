console.log("main.js loaded.");

// 회원 정보 조회 비동기 통신(AJAX)

document.getElementById("select1").addEventListener('click', function () {

    const input = document.getElementById("in1");
    const div = document.getElementById("result1");

    // AJAX 코드 작성 (JQuery 방식)

    $.ajax({
        url: "member/selectOne",  // Servlet 요청 주소, 
        data: { "memberNickname": input.value }, // body Post 방식의 일종. 데이터를 Body에 숨김
        type: "POST",
        dataType: "JSON", // dataType : 응답 데이터 형식을 지정 , "JSON" 으로 지정 시, 자동으로 JS 객체로 변환된다.

        success: function (member) { // 성공 시, 익명 함수 출력, 여기서 member는 DB에서 받아온다.
            console.log(member);

            // 1) div에 작성된 내용 모두 삭제
            div.innerHTML = "";

            if (member != null) { // 회원 정보 존재 O

                // 2) ul 요소 생성
                const ul = document.createElement("ul");

                // 3) li 요소 생성 * 5 + 내용 추가
                const li1 = document.createElement("li");
                li1.innerText = "이메일 : " + member.memberEmail;

                const li2 = document.createElement("li");
                li2.innerText = "닉네임 : " + member.memberNickname;

                const li3 = document.createElement("li");
                li3.innerText = "전화번호 : " + member.memberTel;

                const li4 = document.createElement("li");
                li4.innerText = "주소 : " + member.memberAddress;

                const li5 = document.createElement("li");
                li5.innerText = "가입일 : " + member.enrollDate;

                // 4) ul에 li를 순서대로 추가
                ul.append(li1, li2, li3, li4, li5);

                // 5) div에 ul 추가
                div.append(ul);

            } else { // 회원 정보 존재 X

                // 1) h4 요소 생성
                const h4 = document.createElement("h4");

                // 2) 내용 추가
                h4.innerText = "일치하는 회원이 없습니다";

                // 3) 색 추가
                h4.style.color = "red";

                // 4) div에 추가
                div.append(h4);
            }
        },

        error: function (request) { // 에러 시, 익명 함수 출력
            console.log("AJAX 에러 발생");
            console.log("상태코드 : " + request.status); // 404, 500 등등의 코드로 나온다.
            // request : {statusCode:404}0        
        }

    });

});


window.onload = function () {

    function selectAll() {
        console.log("selectAll is loaded.");

        $.ajax({
            url: "member/selectAll",
            type: "post",
            dataType: "JSON",
            success: function (memberList) {
                const member_TB = document.querySelector('#member_TB tbody');
                member_TB.innerHTML = "";

                if (memberList != null) {
                    for (let i = 0; i < memberList.length; i++) {
                        const member_3th =
                            `<tr>
                            <td>${memberList[i].memberNo}</td>
                            <td>${memberList[i].memberEmail}</td>
                            <td>${memberList[i].memberNickname}</td>
                        </tr>`;

                        member_TB.innerHTML += member_3th;
                    }
                }
            }
        });
    };

    selectAll();
    setInterval(selectAll, 2000);

};



