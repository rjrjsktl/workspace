const notectner = document.getElementById("notectner");
const checkbtn = document.getElementById("checkbtn");
const addtitle = document.getElementById("addtitle");
const addbox = document.getElementById("addbox");

function viewNote() {
    $.ajax({
        url: "viewnote",
        dataType: "JSON",

        sussess: function (ntlist) {

            const addbox = document.getElementById("addbox");

            // addnote
            addbox.innerHTML = "";

            for (let item of ntlist) {
                // note 추가 div 추가
                const addform = document.createElement("div");
                addform.classList.add("addform");

                // div에 내용 추가
                const addtitle = document.createElement("div");
                addtitle.classList.add("addtitle");
                addtitle.innerText = item.noteTitle;

                const adddate = document.createElement("div");
                adddate.classList.add("adddate");
                adddate.innerText = item.noteDate;

                // div에 넣고싶은 div 2개 추가
                addform.append(addtitle, adddate);

                // addbox에 div 추가
                addbox.append(addform);
            }
        },
        error: function (request) {
            console.log("AJAX 에러 발생")
            console.log("상태코드 : " + request.status) // 404, 500
        }
    })
}

(function () {
    console.log("start")
    viewNote();
    window.setInterval(viewNote, 1000)
})();


const nttitle = document.getElementById("nttitle");
const nttext = document.getElementById("nttext");
const donebtn = document.getElementById("donebtn");
const removebtn = document.getElementById("removebtn");


function clickDoneBtn() {

    //alert("click")
    // 제목 입력 x
    if (nttitle.value.trim() === "") {
        window.alert("제목을 입력하라옹");

        nttitle.focus();
        return false;
    }
    // 제목 입력 o, 내용 압력 x
    if (nttext.value.trim() === "") {
        window.alert("내용이 없다옹");

        nttext.focus();
        return false;
    } else {
        window.alert("노트 저장을 완료 했다옹");
    }
    return true;
}

function clickRemoveBtn() {
    window.alert("삭제 완료 했다옹");
}




// donebtn.addEventListener("click", function noteValidate() {

//     // 제목 입력 x
//     if (nttitle.value.trim() === "") {
//         window.alert("제목을 입력하라옹");

//         nttitle.focus();
//         return false;
//     }
//     // 제목 입력 o, 내용 압력 x
//     if (nttext.value.trim() === "") {
//         window.alert("내용이 없다옹");

//         nttext.focus();
//         return false;
//     } else {
//         window.alert("노트 저장을 완료했다옹");
//     }
//     return true;
// });

