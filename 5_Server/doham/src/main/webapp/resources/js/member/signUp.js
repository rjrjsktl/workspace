
// 유효성 검사 여부를 기록할 객체 생성
const checkObj = {
    "memberEmail" : false,
    "memberPw" : false,
    "memberPwConfirm" : false,
    "memberNickname" : false,
    "memberTel" : false,
    "sendEmail" : false,
    "checkEmail" : false
};

// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");

memberEmail.addEventListener("input", function() {

    // 입력이 되지 않은 경우
    if(memberEmail.value.length == 0) {
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        emailMessage.classList.remove("confirm", "error");

        checkObj.memberEmail = false;   // 유효 X 기록
        return;
    }

    // 입력이 된 경우
    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;
    if( regExp.test(memberEmail.value)) {   // 유효한 경우
        // 이메일 중복 검사(ajax) 진행 예정

        $.ajax({
            url : "emailDupCheck",
            // 필수속성 url
            // 현재 주소 : /community/member/signUp
            // 상대 경로 : /community/member/emailDupCheck

            data : {"memberEmail" : memberEmail.value},
            // data 속성 : 비동기 통신 시 서버로 전달한 값을 작성(JS 객체 형식)
            // -> 비동기 통신 시 input에 입력된 값을
            // "memberEmail"이라는 key값(파라미터)으로 전달

            success : function(result) {
                // 비동기 통신(ajax)가 오류 없이 요청/응답 성공한 경우

                // 매개변수 result : servlet에서 출력된 result 값이 담겨있음.
                console.log(result);

                if(result == 1) {   // 중복 O
                    emailMessage.innerText = "이미 사용 중인 이메일입니다.";
                    emailMessage.classList.add("error");
                    emailMessage.classList.remove("confirm");
                    checkObj.memberEmail = false;
                } else {
                    emailMessage.innerText = "사용 가능한 이메일입니다.";
                    emailMessage.classList.remove("error");
                    emailMessage.classList.add("confirm");
                    checkObj.memberEmail = true;    // 유효 O 기록
                }

            },

            error : function() {
                // 비동기 통신(ajax) 중 오류가 발생한 경우
                console.log("에러 발생");
            }

        });


    } else {
        emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");

        checkObj.memberEmail = false;   // 유효 X 기록
    }


})




// 인증번호 보내기

const sendBtn = document.getElementById("sendBtn");

const cMessage = document.getElementById("cMessage");

// 타이머에 사용될 변수

let min = 4;
let sec = 59;
let checkInterval;  // setInterval을 저장할 변수


sendBtn.addEventListener("click", function() {
    
    if(checkObj.memberEmail) { // 유효한 이메일이 작성되어 있을 경우에만 메일 보내기
        $.ajax({
            url : "sendEmail",
            data : {"inputEmail" : memberEmail.value},
            success : function(result) {
                console.log("이메일 발송 성공");
                console.log(result);

                // 인증 버튼이 클릭되어 정상적으로 메일이 보내졌음
                checkObj.sendEmail = true;
            },
            error: function() {
                console.log("이메일 발송 실패");
            }

        });

        // Mail 발송 Ajax 코드는 동작이 느림...
        // -> 메일은 메일대로 보내지고, 타이머는 버튼이 클릭되자마자 바로 실행
        // --> ajax 코드가 비동기이기 때문에 메일이 보내지는 것을 기다리지 않고
        //      바로 수행된다!!!

        // 5분 타이머
        // setInterval(함수, 지연시간) : 지연시간이 지난 후 함수를 수행(반복)

        cMessage.innerText = "5:00";

        min = 4;
        sec = 59;

        cMessage.classList.remove("confirm");
        cMessage.classList.remove("error");

        // 변수에 저장해야 해당 함수를 멈출 수 있음
        checkInterval = setInterval(function(){

            if(sec < 10) sec = "0" + sec;

            cMessage.innerText = min + " : " + sec;

            if(Number(sec) === 0) {
                min--;
                sec = 59;
            } else {
                sec--;
            }

            if(min === -1) {
                cMessage.classList.add("error");
                cMessage.innerText = "인증번호가 만료되었습니다.";

                clearInterval(checkInterval);   // setInterval 함수 반복을 지움
            }

        }, 1000);   // 1초 지연 후 수행

        alert("인증번호가 발송되었습니다. 이메일을 확인해주세요.");
    }
    
});



// 인증번호 확인 클릭시에 대한 동작
const cBtn = document.getElementById("cBtn");
const cNumber = document.getElementById("cNumber");

// cMessage, memberEmail 요소도 사용

cBtn.addEventListener("click", function() {
    // 1. 인증번호 받기 버튼이 클릭되어 이메일이 발송되었는지 확인
    if(checkObj.sendEmail) {

        // 2. 입력된 인증번호가 6자리가 맞는지 확인
        if(cNumber.value.length == 6) {
            // ajax
            $.ajax({
                url : "checkNumber",
                data : {
                    "cNumber" : cNumber.value,
                    "inputEmail" : memberEmail.value
                },
                success : function(result) {
                    console.log(result);

                    // 1 : 인증번호 일치 O, 시간 만족 O
                    // 2 : 인증번호 일치 O, 시간 만족 X
                    // 3 : 인증번호 일치 X

                    if(result == 1) {
                        clearInterval(checkInterval);   
                        checkObj.checkEmail = true;
                        cMessage.innerText = "인증되었습니다.";
                        cMessage.classList.add("confirm");
                        cMessage.classList.remove("error");
                    } else if(result == 2) {
                        alert("만료된 인증번호입니다.");
                    } else {
                        alert("인증번호가 일치하지 않습니다.");
                    }

                },
                error : function() {
                    console.log("이메일 인증 실패");
                }
            })
        } else {
            alert("인증번호를 정확하게 입력해주세요.");
            cNumber.focus();
        }

    } else {
        alert("인증번호 받기 버튼을 먼저 클릭해주세요!");
    }
})


// 비밀번호 인증

const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");

memberPw.addEventListener("input", function() {

    // /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/
    const regExp = /^[\w\!\@\#\-\_]{6,30}$/;

    if(regExp.test(memberPw.value)) {

        checkObj.memberPw = true;

        if(memberPw.value == memberPwConfirm.value) {
            checkObj.memberPwConfirm = true;
        } else {
            checkObj.memberPwConfirm = false;
        }

    } else {
        
        checkObj.memberPw = false;
    }

    checkPassword();

})


// 비밀번호 일치 인증

memberPwConfirm.addEventListener("input", function() {
    if(memberPw.value == memberPwConfirm.value) {
        checkObj.memberPwConfirm = true;
    } else {
        checkObj.memberPwConfirm = false;
    }

    checkPassword();
})


// 비밀번호 인증 표시

function checkPassword() {
    if(checkObj.memberPw && checkObj.memberPwConfirm) {
        pwMessage.innerText = "인증되었습니다.";
        pwMessage.classList.add("confirm");
        pwMessage.classList.remove("error");
    } else if(checkObj.memberPw && !checkObj.memberPwConfirm) {
        pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
        pwMessage.classList.remove("confirm");
        pwMessage.classList.add("error");
    } else if(!checkObj.memberPw) {
        pwMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.";
        pwMessage.classList.remove("confirm");
        if(memberPw.value.length == 0) {
            pwMessage.classList.remove("error");
        } else {
            pwMessage.classList.add("error");
        }
    }
}

// 닉네임 인증

const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNickname.addEventListener("input", function() {
    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if(regExp.test(memberNickname.value)) {
        $.ajax ({
            url : "nicknameDupCheck",
            data : {"memberNickname" : memberNickname.value},
            success : function(result) {

                if(result == 1) {
                    nicknameMessage.innerText = "중복된 닉네임입니다.";
                    nicknameMessage.classList.add("error");
                    nicknameMessage.classList.remove("confirm");
                    checkObj.memberNickname = false;
                } else {
                    nicknameMessage.innerText = "사용 가능한 닉네임입니다.";
                    nicknameMessage.classList.add("confirm");
                    nicknameMessage.classList.remove("error");
                    checkObj.memberNickname = true;
                }

            },

            error : function() {
                console.log("닉네임 통신 실패");
            }
        })
    } else {
        nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
        nicknameMessage.classList.remove("confirm", "error");
    }
})


// 전화번호 인증

const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

memberTel.addEventListener("input", function() {
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;
    if(regExp.test(memberTel.value)) {
        telMessage.innerText = "확인되었습니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
        checkObj.memberTel = true;
    } else {
        checkObj.memberTel = false;
        if(memberTel.value.length == 0) {
            telMessage.innerText = "전화번호를 입력해주세요.(- 제외)";
            telMessage.classList.remove("error", "confirm");
        } else {
            telMessage.innerText = "형식에 맞지 않는 전화번호입니다.";
            telMessage.classList.remove("confirm");
            telMessage.classList.add("error");
        }
    }
})


// 회원가입


function signUpValidate() {
    const sum = 0;
    
    for(let key in checkObj) {
        sum += checkObj[key];
    }

    if(sum == 7) {
        return true;
    } else {
        alert("필수 정보를 입력하지 않았습니다.");
    }  

    return false;

}
