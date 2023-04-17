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
                addform.classList.add("addform");
                
                addform.action="pass";
                addform.method="POST";

                const addinform = document.createElement("div");
                addinform.classList.add("addinform");
                // div에 내용 추가
                const addtitle = document.createElement("div");
                addtitle.classList.add("addtitle");
                addtitle.innerText = item.noteTitle;

                const adddate = document.createElement("div");
                adddate.classList.add("adddate");
                adddate.innerText = item.noteDate;

                addinform.append(addtitle, adddate);
                // div에 넣고싶은 div 2개 추가
                addform.append(addinform);

                // addbox에 div 추가
                addbox.append(addform);

            }
        },
        error: function(request) {
            console.log("AJAX 에러 발생")
            console.log("상태코드 : " + request.status) // 404, 500
        }
    })
};



(function () {
    console.log("start")
    window.setInterval(viewNote(), 1000000000000)
})();


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
}

// function passNote() {
//     console.log("addform클릭");
//     location.href="/toypjt1/note/pass";

//     nttitle.value = "noteTitle";
//     ntmemo.value = "noteMemo";
//     nowtm.value = "noteDate";
// }
