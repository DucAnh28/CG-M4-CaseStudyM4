function login() {
    let username = $('#exampleInputUsername').val();
    let password = $('#exampleInputPassword').val();
    if (username === "") {
        document.getElementById("error_login").innerHTML = "username address cannot be blank !";
        return false;
    }
    if (password === "") {
        document.getElementById("error_login").innerHTML = "Password can not be blank !";
        return false;
    }
    let data = {
        name: username,
        password: password
    };
    $.ajax({
        url: `http://localhost:2828/login`,
        type: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        data: JSON.stringify(data),
        success: function (data) {
            if (data === undefined) {
                document.getElementById("error_login").innerHTML = "Username or password is incorrect !"
                return false;
            } else {
                localStorage.setItem("token", data.token);
                localStorage.setItem("currentUser", JSON.stringify(data));
                if (data.roles[0].name === "ROLE_ADMIN"){
                    location.href = "../admin/admin-home.html";
                }
                if (data.roles[0].name === "ROLE_COACH"){
                    console.log("hi")
                    location.href = "../coach/coach-home.html";
                }
            }
        }
    });
    event.preventDefault();
}