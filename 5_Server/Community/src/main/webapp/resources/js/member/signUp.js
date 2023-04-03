    // 유효성 검사 여부를 기록할 객체 생성
    const checkObj = {
        "memberEmail" : false,
        "memberPw" : false, // 영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성
        "memberPwConfirm" : false, // 비밀번호와 일치하는지 확인
        "memberNickname" : false, // 영어 / 숫자 / 한글 2~10글자 사이로 작성 + 중복검사
        "memberTel" : false, // - 제외 
        "sendEmail" : false,
        "checkNumber" : false
    };

    // checkObj 의 값이 모두 true 면,
    // 회원 가입 버튼 눌렀을 때
    // BEST ! 회원가입 실제로 동작 하게끔 (INSERT > MEMBER)
    // -> alert("가입이 완료되었습니다!" or "유효성 검사가 통과되지 않았습니다!")


    // 이메일 유효성 검사
    const memberEmail = document.getElementById("memberEmail");
    const emailMessage = document.getElementById("emailMessage");

    memberEmail.addEventListener("input", function() {

        // 입력이 되지 않은 경우
        if(memberEmail.value.length == 0) {
            emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
            emailMessage.classList.remove("confirm", "error");

            checkObj.memberEmail = false; // 유효 x라서 기록 안함
            return;
        }

        // 입력이 된 경우
        // 정규표현식
        const regExp = /^[\w\-\_\.]{4,}@[\w\-\_]+(\.\w+){1,3}$/;
        if( regExp.test(memberEmail.value) ) { // 유효한 경우
            // ************** 이메일 중복 검사(ajax) 진행 예정 **************

            $.ajax({
                url : "emailDupCheck", 
                // 필수속성 url
                // 현재 주소 : /community/member/signUp
                // 상대 경로 : /community/member/emailDupCheck

                data: {"memberEmail" : memberEmail.value},
                // data 속성 : 비동기 통신 시 서버로 전달한 값을 작성(JS 객체 형식)
                // -> 비동기 통신 시  input에 입력된 값을
                // "memberEmail"이라는 key값(파라미터)으로 전달

                success : function(result) {
                    // 비동기 통신(ajax)가 오류 없이 요청/응답 성공한 경우

                    // 매개변수 result : servlet에서 출력된 result 값이  담겨있음.
                    console.log(result);

                    if(result == 1) { // 중복 o
                        emailMessage.innerText = "이미 사용중인 이메일 입니다.";
                        emailMessage.classList.add("error");
                        emailMessage.classList.remove("confirm");
                        checkObj.memberEmail = false; // 유효하지않은 상태
                    } else { // 중복 x
                        emailMessage.innerText = "사용 가능한 이메일 입니다.";
                        emailMessage.classList.add("confirm");
                        emailMessage.classList.remove("error");
                        checkObj.memberEmail = true; // 유효한 상태
                    }
                },
                error : function() {
                    // 비동기 통신(ajax)중 오류가 발생한 경우
                    console.log("에러 발생");

                }

            })

        } else {
            emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
            emailMessage.classList.add("error");
            emailMessage.classList.remove("confirm");

            checkObj.memberEmail = false; //  유효 x라서 기록 x
        }

    });


    

    // 인증번호 보내기
    const sendBtn = document.getElementById("sendBtn");
    const cMessage = document.getElementById("cMessage");

    // 타이머에 사용될 변수
    let min = 4;
    let sec = 59;
    let checkInterval; // setInterval을 저장할 변수

    sendBtn.addEventListener("click", function() {

        if( checkObj.memberEmail ) { // 유효한 이메일이 작성되어 있을 경우에만 메일 보내기
            $.ajax({
                url : "sendEmail",
                data : {"inputEmail" : memberEmail.value},
                success : function(result) {
                    console.log("이메일 발송 성공");
                    console.log(result);

                    // 인증 버튼이 클리되어 정상적으로 메일이 보내졌음을
                    checkObj.sendEmail = true;
                },
                error : function() {
                    console.log("이메일 발송 실패");
                }
            })

            // Mail 발송 Ajax 코드는 동작이 느림...
            // -> 메일은 메일대로 보내지고, 타이머는 버튼이 클릭 되자 마자 바로 실행
            // --> ajax 코드가 비동기이기 때문에 메일이 보내지는 것을 기다리지 않고
            //  바로 수행된다!!!

            // 5분 타이머
            // setInterval(함수, 지연시간) : 지연시간이 지난 후 함수를 수행(반복)

            cMessage.innerText = "5:00"; // 초기값 5분

            min = 4;
            sec = 59;
            cMessage.classList.remove("confirm");
            cMessage.classList.remove("error");

            // 변수에 저장해야 해당 함수를 멈출 수 있음
            checkInterval = setInterval(function() {
                if(sec < 10) sec = "0" + sec;
                cMessage.innerText = min + ":" + sec;

                if(Number(sec) === 0) {
                    min--;
                    sec = 59;
                } else {
                    sec--;
                }

                if(min === -1) {
                    cMessage.classList.add("error");
                    cMessage.innerText = "인증번호가 만료되었습니다.";

                    clearInterval(checkInterval); // setInterval 함수 반복을 지움
                }

            }, 1000); // 1초 지연 후 수행

            alert("인증번호가 발송되었습니다. 이메일을 확인해 주세요.");

        }

    });

    // 인증번호 확인 클릭시에 대한 동작
    const cNumber = document.getElementById("cNumber");
    const cBtn = document.getElementById("cBtn");
    // + cMessage, memberEmail 요소도 사용

    cBtn.addEventListener("click", function() {
        // 1. 인증번호 받기 버튼이 클릭되어 이메일이 발송되었는지 확인
        if(checkObj.sendEmail) {
        
            // 2. 입력된 인증번호가 6자리가 맞는지 확인
            if(cNumber.value.length == 6) {
                // ajax
                $.ajax({
                    url : "checkNumber",
                    data : {"cNumber" : cNumber.value,
                            "inputEmail" : memberEmail.value
                    },
                    success : function(result) {
                        console.log(result);

                        // 1 : 인증번호는 일치 o, 시간 만족 o
                        // 2 : 인증번호는 일치 o, 시간 만족 x
                        // 3 : 인증번호 일치 x

                        if(result == 1) {
                            clearInterval(checkInterval); // 타이머 멈춤
                            cMessage.innerText = "인증되었습니다.";
                            cMessage.classList.add("confrim");
                            cMessage.classList.remove("error");

                            checkObj.sendEmail = true;
                        } else if(result == 2) {
                            alert("만료된 인증 번호 입니다.");
                        } else {
                            alert("인증 번호가 일치하지 않습니다.");
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
    });


    const memberPw = document.getElementById("memberPw");
    const memberPwConfirm = document.getElementById("memberPwConfirm");

    const pwMessage = document.getElementById("pwMessage");

    memberPw.addEventListener("input", function() {

        if(memberPw.value.length == 0) {
            pwMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.";
            pwMessage.classList.remove("confirm", "error");

            checkObj.memberPw = false;
            return;
        }
        
        const pwExp = /^[\w!@#_-]{6,30}$/;
        if( pwExp.test(memberPw.value) ) {
            pwMessage.innerText = "사용 가능한 비밀번호 입니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");

            checkObj.memberPw = true;
            
        } else {
            pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";
            pwMessage.classList.add("error");
            pwMessage.classList.remove("confirm");

            checkObj.memberPw = false;
        }
    });
    memberPwConfirm.addEventListener("input", function() {
        if(memberPw.value == memberPwConfirm.value) {
            pwMessage.innerText = "비밀번호가 일치합니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");

            checkObj.memberPwConfirm = true;
        } else {
            pwMessage.innerText = "비밀번호가 일치하지 않습니다..";
            pwMessage.classList.add("error");
            pwMessage.classList.remove("confirm");

            checkObj.memberPwConfirm = false;
        }
    });

    const memberNickname = document.getElementById("memberNickname");
    const nicknameMessage = document.getElementById("nicknameMessage");

    memberNickname.addEventListener("input", function() {

        if(memberNickname.value.length == 0) {
            nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
            nicknameMessage.classList.remove("confirm", "error");

            checkObj.memberNickname = false;
            return;
        }
        const nickExp = /^[a-zA-Z0-9가-힣]{2,10}$/;
        if( nickExp.test(memberNickname.value) ) {
            $.ajax({
                url : "nickCheck",

                data : {"memberNickname" : memberNickname.value},

                success : function(result) {
                    console.log(result);

                    if(result == 1) {
                        nicknameMessage.innerText = "이미 사용중인 닉네임 입니다.";
                        nicknameMessage.classList.add("error");
                        nicknameMessage.classList.remove("confirm");

                        checkObj.memberNickname = false;
                    } else {
                        nicknameMessage.innerText = "사용 가능한 닉네임 입니다.";
                        nicknameMessage.classList.add("confirm");
                        nicknameMessage.classList.remove("error");

                        checkObj.memberNickname = true;
                    }
                },
                error : function() {
                    console.log("에러 발생");
                }
            })
        } else {
            nicknameMessage.innerText = "닉네임 형식이 유효하지 않습니다.";
            nicknameMessage.classList.add("error");
            nicknameMessage.classList.remove("confirm");

            checkObj.memberNickname = false;
        }
    });

    const memberTel = document.getElementById("memberTel");
    const telMessage = document.getElementById("telMessage");

    memberTel.addEventListener("input", function() {
        if(memberTel.value.length == 0) {
            telMessage.innerText = "전화번호를 입력해주세요.(- 제외)";
            telMessage.classList.remove("confirm", "error");

            checkObj.memberTel = false;
            return;
        }

        const telExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;
        if( telExp.test(memberTel.value) ) {
            telMessage.innerText = "올바른 형식의 전화번호 입니다.";
            telMessage.classList.add("confirm");
            telMessage.classList.remove("error");

            checkObj.memberTel = true;
        } else {
            telMessage.innerText = "전화번호 형식이 유효하지 않습니다.";
            telMessage.classList.add("error");
            telMessage.classList.remove("confirm");

            checkObj.memberTel = false;
        }
    });

    // const signUpbtn = document.getElementById("signUp-btn");

    // signUpbtn.addEventListener("click", function() {
    //     if( checkObj.memberEmail == true &&
    //         checkObj.memberNickname == true &&
    //         checkObj.memberPw == true &&
    //         checkObj.memberPwConfirm == true &&
    //         checkObj.memberTel == true &&
    //         checkObj.sendEmail == true ) {
    //         $.ajax({
    //             url : "userUpdate",

    //             data : {"memberEmail" : memberEmail.value,
    //                     "memberPwConfirm" : memberPwConfirm.value,
    //                     "memberTel" : memberTel.value,
    //                     "memberNickname" : memberNickname.value
    //                     },
    //             success : function(result) {
    //                 console.log(result);
            
    //                 if(result == 1) {
    //                     alert("가입완료");
    //                 } else {
    //                     alert("정보를 다시 입력하세요");
    //                 }
    //             },
    //             error : function() {
    //                 console.log("에러 발생");
    //             }
    //         })
    //     } else {
    //         alert("정보를 다시 입력하세요");
    //     }
    // });


// 회원가입 버튼 클릭 시 유효성 검사가 완료 되었는지 확인하는 함수
function signUpValidate(){

    // checkObj에 있는 모든 속성을 반복 접근하여
    // false가 하나라도 있는 경우에는 form태그 기본 이벤트 제거

    let str;

    for( let key  in checkObj ){ // 객체용 향상된 for문

        // 현재 접근 중인 key의 value가 false인 경우
        if( !checkObj[key] ){ 

            switch(key){
            case "memberEmail":     str="이메일이"; break;
            case "memberPw":        str="비밀번호가"; break;    
            case "memberPwConfirm": str="비밀번호 확인이"; break;
            case "memberNickname":  str="닉네임이"; break;
            case "memberTel":       str="전화번호가"; break;
            case "sendEmail":       str="인증번호가"; break;
            }

            str += " 유효하지 않습니다.";

            alert(str);
            
            document.getElementById(key).focus();
            
            return false; // form태그 기본 이벤트 제거
        }
        
    }

    return true; // form태그 기본 이벤트 수행

}
