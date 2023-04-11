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

const notectner = document.getElementById("notectner");
const checkbtn = document.getElementById("checkbtn");
const addtitle = document.getElementById("addtitle");

checkbtn.addEventListener("click", function () {


});