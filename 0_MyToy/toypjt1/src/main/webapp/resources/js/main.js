













const nttitle = document.getElementById("nttitle");
const nttext = document.getElementById("nttext");
const donebtn = document.getElementById("donebtn");
const removebtn = document.getElementById("removebtn");

donebtn.addEventListener("click", function noteValidate() {

    // 제목 입력 x
    if (nttitle.value.trim() === "") {
        window.alert("제목을 입력하라옹");

        nttitle.focus();
        return false;
    }
    // 제목 입력 o, 내용 압력 x
    if (nttext.value.trim() === "") {
        window.alert("내용이 없다옹");
    }
    return true;
});

let notectner = document.getElementById("notectner");
let checkbtn = document.getElementById("checkbtn");
let addtitle = document.getElementById("addtitle");

function noteAll() {
    $.ajax({
        url: "note/noteAll",
        dataType: "JSON",

        sussess: function() 

    })

}





checkbtn.addEventListener("click", function () {

    addtitle.append('<div class="nttitle">노트제목</div><div class="nttime">노트시간</div>');
});