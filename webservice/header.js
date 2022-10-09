let currentUser = JSON.parse(localStorage.getItem("currentUser"));
let token = localStorage.getItem("token");
console.log(currentUser)

if(currentUser === null){
    window.location.href = "homepage/login.html";
}

function logout(){
    localStorage.clear();
    window.location.href = "homepage/login.html";
}

