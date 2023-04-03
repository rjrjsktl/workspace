
const inputImage = document.getElementById("input-image");

if(inputImage != null) { // inputImage 요소가 화면에 존재할때

    // input type="file" 요소는 파일이 선택될 때 change 이벤트 발생

    inputImage.addEventListener("change", function() {

        // 파일 목록에서 첫번째 파일 객체를 선택
        // files : input type="file"만 사용 가능한 속성으로
        //          선택된 파일 목록(배열)을 반환

        // this : 이벤트가 발생한 요소 (input type="file")

        if(this.files[0] != undefined) { // 파일이 선택되었을때

            const reader = new FileReader();
            // 자바스크립트의 FileReader
            // - 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 사용하는 객체

            reader.readAsDataURL(this.files[0]);
            // FileReader.readAsDataURL(파일)
            // - 지정된 파일의 내용을 읽기 시작함.
            // - 읽어오는게 완료되면 result 속성에 data에 
            // 읽어온 파일의 위치를 나타내는 URL이 포함된다.
            // -> 해당 URL을 이용해서 브라우저에서 이미지를 볼 수 있다.

            reader.onload = function(e) {
                // e : 이벤트 발생 객체
                // e.target : 이벤트가 발생한 요소 -> FileReader
                // e.target.result : FileReader가 읽어온 파일의 URL

                // 프로필 이미지의 src 속성의 값을 FileReader가 읽어온 파일의 URL로 변경
                const profileImage = document.getElementById("profile-image");

                profileImage.setAttribute("src", e.target.result);
                // -> setAttribute() 호출 시 중복되는 속성이 존재하면 덮어쓰기

                document.getElementById("delete").value = 0;
                // 새로운 이미지가 선택되었기 떄문에 1 -> 0(안눌러짐 상태)로 변경

            }

        }


    });

}


// 프로필 이미지 옆 X버튼 클릭 시
document.getElementById("delete-image").addEventListener("click", function() {
    // 0 : 안눌러짐
    // 1 : 눌러짐

    const del = document.getElementById("delete");

    if(del.value == 0) { //눌러지지 않은 경우

        // 1) 프로필 이미지를 기본 이미지로 변경
        document.getElementById("profile-image").setAttribute("src", contextPath + "/resources/images/user.png");

        // 2) input type="file"에 저장된 값(value)에 "" 대입
        document.getElementById("input-image").value = "";

        del.value = 1;
    }

});

// 이미지 선택 확인
function profileValidate() {
    const inputImage = document.getElementById("input-image");

    const del = document.getElementById("delete"); // hidden 타입

    if( inputImage.value == "" && del.value == 0 ) {
        // 파일선택 X        /     x 를 누르지도 않았다
        // -> 아무것도 안하고 변경버튼만 클릭한 경우

        return false;
    }

	else{
		
    return true;
	}
}


function infoValidate() {
	
    const memberNickname = document.getElementById("memberNickname");
	const memberTel = document.getElementById("memberTel");
	const regNick = /^[a-zA-Z0-9가-힣]{2,10}$/;
	const regTel = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$|^07(?:0|[7-9])(?:\d{3}|\d{4})\d{4}$/;
	
    if (memberNickname.value.trim() === "") {
        alert("닉네임을 입력해 주세요.");
        memberNickname.focus();
        return false;
    }
    
    if (!regNick.test(memberNickname.value)) {
        alert("영어/숫자/한글 2~10글자 사이로 작성해주세요.");
        memberNickname.focus();
        return false;
    }

    if (memberTel.value.trim() === "") {
        alert("전화번호를 입력해 주세요.");
        memberTel.focus();
        return false;
    }
    
     if (!regTel.test(memberTel.value)) {
        alert("-를 제외한 10~11자리 숫자만 입력해주세요");
        memberTel.focus();
        return false;
    }
    
	else{
    return true;
    }
};

function changePwValidate() {
    const currentPw = document.getElementById("currentPw");
    const newPw = document.querySelector("input[name='newPw']");
    const confirmPw = document.querySelector("input[name='newPwConfirm']");
    const regExp = /^[a-zA-Z0-9!@#_-]{6,30}$/;

    if (currentPw.value.trim() === "") {
        window.alert("현재 비밀번호를 입력해주세요.");
        currentPw.focus();
        return false;
    }

    if (!regExp.test(newPw.value)) {
        window.alert("새 비밀번호는 6~30자의 영문, 숫자, 특수문자(!@#_-)만 사용할 수 있습니다.");
        newPw.focus();
        return false;
    }

    if (confirmPw.value.trim() === "") {
        window.alert("새 비밀번호 확인을 입력해주세요.");
        confirmPw.focus();
        return false;  
    } 
    
    if (!regExp.test(confirmPw.value)) {
        window.alert("새 비밀번호는 6~30자의 영문, 숫자, 특수문자(!@#_-)만 사용할 수 있습니다.");
        newPw.focus();
        return false;
    }
    
    if (newPw.value.trim() !== confirmPw.value.trim()) {
        window.alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        confirmPw.focus();
        return false;
    }

    return true;
    
    
}

function secessionValidate() {
	
  const memberPw = document.getElementById("memberPw");
  const agree = document.getElementById("agree");
  
  if (memberPw.value.trim() === "") {
  alert("비밀번호를 확인해주세요.");
  memberPw.focus();
  return false;
}

  else if (!agree.checked) {
  alert("약관에 동의해주세요.");
  return false;
} 

   return true;

}