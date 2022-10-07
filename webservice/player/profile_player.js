function getPerformance() {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:2828/performance`,
        //xử lý khi thành công
        success: function (data) {
            let content = '<select id="performance-player-edit" class="form-control">\n'
            for (let i = 0; i < data.length; i++) {
                content += displayPerformance(data[i]);
            }
            content += '</select>'
            document.getElementById("div-performance-edit").innerHTML = content;
        }
    });
}

getPerformance();


function displayPerformance(performance) {
    return `<option id="${performance.id}" value="${performance.id}">${performance.name}</option>`;
}

function getPosition() {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:2828/position`,
        //xử lý khi thành công
        success: function (data) {
            let content = '<select id="position-player-edit" class="form-control">\n'
            for (let i = 0; i < data.length; i++) {
                content += displayPosition(data[i]);
            }
            content += '</select>'
            document.getElementById("div-position-edit").innerHTML = content;
        }
    });
}

getPosition();

function displayPosition(position) {
    return `<option id="${position.id}" value="${position.id}">${position.name}</option>`;
}

function getStatus() {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:2828/status`,
        //xử lý khi thành công
        success: function (data) {
            let content = '<select id="status-player-edit" class="form-control">\n'
            for (let i = 0; i < data.length; i++) {
                content += displayStatus(data[i]);
            }
            content += '</select>'
            document.getElementById("div-status-edit").innerHTML = content;
        }
    });
}

getStatus();

function displayStatus(status) {
    return `<option id="${status.id}" value="${status.id}">${status.state}</option>`;
}

function displayEditPlayer(player) {

    $('#name-player-edit').val(player.name);
    $('#country-player-edit').val(player.country);
    $('#height-player-edit').val(player.height);
    $('#weight-player-edit').val(player.weight);
    $('#salary-player-edit').val(player.salary);
    $('#introduction-player-edit').val(player.introduction);
    $('#position-player-edit').val(player.position.id);
    $('#performance-player-edit').val(player.performance.id);
    $('#status-player-edit').val(player.status.id);
}

function displayBGPlayer(player) {
    return `<img class="img-fluid" src="${player.avatarBackGround}" alt="Card image cap" width="800px" height="500px">`;
    // alt dùng để hiển thị khi ảnh không hiển thị
}

function displayAvaPlayer(player) {
    return `<img src="${player.avatarURL}" alt="profile-image" class="profile" width="110px" height="110px">
            <h5 class="card-title">${player.name}</h5>
            <p class="card-text">${player.introduction}</p>`;
}

function displayValuePlayer(player) {
    return `
            <tr>
                <th>
                  <strong>Country</strong>
                </th>
                <td>
                  <h6>${player.country}</h6>
                </td>
            </tr>
            <tr>
                <th>
                  <strong>Height</strong>
                </th>
                <td>
                  <h6>${player.height}</h6>
                </td>
            </tr>
            <tr>
                <th>
                  <strong>Weight</strong>
                </th>
                <td>
                  <h6>${player.weight}</h6>
                </td>
            </tr>

            <tr>
                <th>
                   <strong>Salary</strong>
<!--                   // strong dùng để in đậm-->
<!--                   <small class="badge float-right badge-light">bonus</small>-->
                </th>
                <td>
                    <strong>$ ${player.salary}</strong>
                </td>
            </tr>
            <tr>
                <th>
                    <strong>Position</strong>
                </th>
                <td>
                    <strong>${player.position.name}</strong>
                </td>
            </tr>
            <tr>
                <th>
                    <strong>Performance</strong>
                </th>
                <td>
                    <strong>${player.performance.name}</strong>
                </td>
            </tr>
            <tr>
                <th>
                    <strong>Status</strong>
                </th>
                <td>
                    <strong>${player.status.state}</strong>
                </td>
            </tr>`;
}

function findPlayerById() {
    $.ajax({
        type: "GET",
        url: `http://localhost:2828/find-player-by-id/${localStorage.getItem("id_player")}`,
        success: function (data) {
            document.getElementById("displayBGPlayer").innerHTML = displayBGPlayer(data);
            document.getElementById("displayAvaPlayer").innerHTML = displayAvaPlayer(data);
            document.getElementById("displayValuePlayer").innerHTML = displayValuePlayer(data);
            console.log(localStorage.getItem("id_player"));
            displayEditPlayer(data);
        }
    });
}

findPlayerById();

function editPlayer()
{
    let data = new FormData();
    // let data dùng để lưu dữ liệu
    // new FormData() dùng để lưu dữ liệu dạng file
    let name = $('#name-player-edit').val();
    // let name = $('#name-player-edit') dùng để lấy giá trị của id
    // .val() dùng để lấy giá trị của id
    let country = $('#country-player-edit').val();
    let height = $('#height-player-edit').val();
    let weight = $('#weight-player-edit').val();
    let salary = $('#salary-player-edit').val();
    let introduction = $('#introduction-player-edit').val();
    let position = $('#position-player-edit').val();
    let performance = $('#performance-player-edit').val();
    let status = $('#status-player-edit').val();
    // if (gmail === '') {
    //     document.getElementById("card-edit-player").innerHTML = "Gmail cannot be blank!";
    //     return false;
    // }
    // if (password === '') {
    //     document.getElementById("card-edit-player").innerHTML = "Password cannot be blank!";
    //     return false;
    // }
    let newPlayer = {
        name: name,
        country: country,
        height: height,
        weight: weight,
        salary: salary,
        introduction: introduction,
        position: {
            id: position,
        },
        performance: {
            id: performance,
        },
        status: {
            id: status,
        }
    };
    data.append("player", new Blob([JSON.stringify(newPlayer)], {type: 'application/json'}))
    // append dùng để thêm dữ liệu
    // new Blob([JSON.stringify(newPlayer)], {type: 'application/json'}) dùng để chuyển dữ liệu thành dạng json
    // data.append("player", new Blob([JSON.stringify(newPlayer)], {type: 'application/json'})) dùng để thêm dữ liệu
    if ($("#avaFile-player-edit")[0].files[0] !== undefined) {
        data.append("avaFile-player", $('#avaFile-player-edit')[0].files[0]);
    }
    if ($("#backGroundFile-player-edit")[0].files[0] !== undefined)
    // $("#backGroundFile-player-edit")[0].files[0] dùng để lấy giá trị của id
        // .files[0] dùng để lấy giá trị của id
        // !== undefined dùng để kiểm tra giá trị của id có phải là undefined hay không
    {
        data.append("backGroundFile-player", $('#backGroundFile-player-edit')[0].files[0]);
    }
    if (confirm("Are you sure you want to edit event ?")) {
        $.ajax({
            contentType: false,
            processData: false,
            type: "PUT",
            data: data,
            url: `http://localhost:2828/edit-player/${localStorage.getItem("id_player")}`,
            success: function () {
                if (data.status === 204 || data.status === 400 || data.status === 404) {
                    document.getElementById("card-edit-player").innerHTML = "Pay attention to the information in the registration form (Gmail may be duplicated)!";
                } else {
                    document.getElementById("card-edit-player").innerHTML = "Update Success !";
                    window.location.href = "profile_player.html"
                    findPlayerById();
                }
            }
        });
    }
    event.preventDefault();
}