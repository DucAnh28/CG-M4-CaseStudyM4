let currentUser = JSON.parse(localStorage.getItem("currentUser"));
let token = localStorage.getItem("token");
console.log(currentUser)
document.getElementById("admin-name").innerHTML = currentUser.username;
function checkLogin() {
    if (currentUser === null) {
        window.location.href = "../homepage/login.html";
    }
    if (currentUser.roles[0].authority !== "ROLE_ADMIN") {
        window.location.href = "../homepage/login.html"
    }
}

checkLogin();

function logout() {
    localStorage.clear();
    window.location.href = "../homepage/login.html";
}

