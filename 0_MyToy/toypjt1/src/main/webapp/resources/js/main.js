const notectner = document.getElementById("notectner");
const checkbtn = document.getElementById("checkbtn");
const addbox = document.getElementById("addbox");

function viewNote() {
    console.log("viewnote");
    $.ajax({
        url: "viewnote",
        dataType: "json",

        success: function(ntlist) {
            // addnote
            addbox.innerHTML = "";
            for (let item of ntlist) {
                // note 추가 div 추가
                
                const addform = document.createElement("form");
                addform.id = item.noteNo;
                
                addform.classList.add("addform");
                
                addform.action="pass";
                addform.method="POST";

                const addinform = document.createElement("div");
                addinform.classList.add("addinform");
                //addinform.onclick = passNote; // 이걸 안쓰면됨 ㅇ?..아니 그뭐냐

                // div에 내용 추가
                const addtitle = document.createElement("div");
                addtitle.classList.add("addtitle");
                addtitle.innerText = item.noteTitle;

                const adddate = document.createElement("div");
                adddate.classList.add("adddate");
                adddate.innerText = item.noteDate;
               
                const addno = document.createElement("input");
                addno.classList.add("addno");
                addno.type="hidden";
                addno.name="noteNo";  // 왜 저기만 name값 주냐고 병신앙 
                addno.value=item.noteNo;

                addinform.append(addtitle, adddate, addno);
                // div에 넣고싶은 div 2개 추가
                addform.append(addinform);

                // addbox에 div 추가
                addbox.append(addform);

            }
            $('.addform').off().on('click', function(){
				console.log('test');
				console.log($(this).attr('id'));
				passNote($(this).attr('id'));
			})
            
        },
        error: function(request) {
            console.log("AJAX 에러 발생")
            console.log("상태코드 : " + request.status) // 404, 500
        }
    })
};

function passNote(num) {
    console.log("addform클릭");
    console.log(num);
    // location.href="/toypjt1/note/pass";
    //const noteNo = document.getElementsByClassName("addno");
    //console.log(noteNo[num]);
 
 	$('#'+num).submit();
    
    //$(".noteNo").val(noteNo[num]);
    //$(".noteNo").val(num);  // 이거 왜있는거임 씨발아 내가 말했자너 몰라서 다박았다고
    //피티형과 함께했음
    //$(".addform").submit();
}

(function () {
    console.log("start")
    window.setInterval(viewNote(), 1000000000000)})
    ();


const nttitle = document.getElementById("nttitle");
const ntmemo = document.getElementById("ntmemo");
const donebtn = document.getElementById("donebtn");
const removebtn = document.getElementById("removebtn");
const nowtm = document.getElementById("nowtm");

function clickDoneBtn() {

    //alert("click")
    // 제목 입력 x
    if (nttitle.value.trim() === "") {
        window.alert("제목을 입력하라옹");

        nttitle.focus();
        return;
    }
    // 제목 입력 o, 내용 압력 x
    if (ntmemo.value.trim() === "") {
        window.alert("내용이 없다옹");

        ntmemo.focus();
        return;
    } else {
        window.alert("노트 저장을 완료 했다옹");
    }
    console.log("doneServlet");
    $.ajax({
        url: "done",
        data: {"noteTitle" : nttitle.value,
                "noteMemo" : ntmemo.value},
        type: "POST",
        dataType: "json",

        success: function() {
            console.log("AJAX 성공");
            location.href="/toypjt1/note/note";
        },
        error: function(request) {
            console.log("AJAX 에러 발생")
            console.log("상태코드 : " + request.status) // 404, 500
        }
    })
};

function clickRemoveBtn() {
    window.alert("삭제 완료 했다옹");
    location.href="/toypjt1/note/remove"; 
}


// $(".addinform").on("click", passNote);
// function passNote() {
//     console.log("addform클릭");
//     const form = $(this); 
//     const noteNo = form.find(".addno").val(); // 수정된 부분
    
//     form.find("input[name='noteNo']").val(noteNo); // 수정된 부분
    
//     form.submit();
// }

// Servlet에서 받은 값(예: noteTitle, noteMemo)을 해당 페이지의 nttitle과 ntmemo에 입력하는 함수
//function setInputValues(noteTitle, noteMemo) {
   // document.getElementById("nttitle").value = noteTitle; // Note Title 값을 입력
   // document.getElementById("ntmemo").value = noteMemo; // Note Memo 값을 입력
//}
// 현재 시간을 nowtm 요소에 표시하는 함수
//function displayCurrentTime() {
//    var now = new Date();
//    var hours = String(now.getHours()).padStart(2, '0');
//    var minutes = String(now.getMinutes()).padStart(2, '0');
//    var seconds = String(now.getSeconds()).padStart(2, '0');
//    var currentTime = hours + ':' + minutes + ':' + seconds;
    //document.getElementById('nowtm').textContent = currentTime;
//}

// 페이지 로드 시 현재 시간 표시
//displayCurrentTime();

// clickNote 객체의 값을 nttitle, ntmemo 요소에 설정하는 함수
//function setNoteValues() {
    // var clickNote = { // clickNote 객체 예시
    //     noteTitle: 'Note Title Example',
    //     noteMemo: 'Note Memo Example'
    // }

 //   document.getElementById('nttitle').value = clickNote.noteTitle;
 //   document.getElementById('ntmemo').value = clickNote.noteMemo;
//}



// // Note Title, Note Memo, Note Date 값을 가져와서 필요한 처리를 수행하는 함수
// function clickUpdateBtn() {
//     var noteTitle = document.getElementById("nttitle").value; // Note Title 값을 가져옴
//     var noteMemo = document.getElementById("ntmemo").value; // Note Memo 값을 가져옴
//     var noteDate = document.getElementById("noteDate").value; // Note Date 값을 가져옴

//     // 필요한 처리를 수행
//     // ...

//     // 값들을 서버로 전송하고 페이지를 리다이렉트하는 등의 로직을 수행
//     // ...

// }