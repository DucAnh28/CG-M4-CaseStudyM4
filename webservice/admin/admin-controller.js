// let currentUser = JSON.parse(localStorage.getItem("currentUser"));
// let token = localStorage.getItem("token");

function showAllCoach() {
    $.ajax({
        type: "get",
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        url: "http://localhost:2828/admin/coach",
        success: function (data) {
            console.log(data)
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += `
                 <tr>        
                    <td>
                     <div class="d-flex px-2 py-1">
                      <div>
                         <img src="${'http://localhost:2828' + data[0].avatarURL}" class="avatar avatar-sm me-3" alt="user1">
                      </div>
                      <div class="d-flex flex-column justify-content-center">
                            <h6 class="mb-0 text-sm">${data[i].name}</h6>
                                <p class="text-xs text-secondary mb-0">${data[0].achievement}</p>
                      </div>
                      </div>
                    </td>
                    <td>
                     <p class="text-xs font-weight-bold mb-0">${data[i].role}</p>
                    </td>
                    <td class="align-middle text-center text-sm">
                      <span class="badge badge-sm bg-gradient-success">${data[i].salary} $</span>
                    </td>
                    <td class="align-middle text-center">
                      <span class="text-secondary text-xs font-weight-bold">${data[i].country}</span>
                    </td>
                    <td class="align-middle">
                      <a href="${data[i].id}"  class="text-secondary font-weight-bold text-xs" 
                      data-toggle="tooltip" data-original-title="Edit user" onclick="showFormUpdate(this)">Edit</a>
                      <a href="${data[i].id}"  class="text-secondary font-weight-bold text-xs" 
                      data-toggle="tooltip" data-original-title="Edit user" onclick="deleteCoach(this)">Delete</a>
                    </td>
                    </tr>`;
            }
            document.getElementById("listCoach").innerHTML = content;

        }
    })
}

showAllCoach();

function createCoach() {
    let formData = new FormData();
    let name = $('#name').val();
    let country = $('#country').val();
    let achievement = $('#achievement').val();
    let salary = $('#salary').val();
    let role = $('#role').val();
    let username = $('#username').val();
    let password = $('#password').val();
    let image = $('#image')[0].files[0];
    formData.append('name', name);
    formData.append('country', country);
    formData.append('achievement', achievement);
    formData.append('salary', salary);
    formData.append('role', role);
    formData.append('username', username);
    formData.append('password', password);
    if (image !== undefined){
        formData.append('avaFile', image);
    }
    $.ajax({
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        dataType: "json",
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        url: "http://localhost:2828/admin/coach",
        data: formData,
        success: function (data) {
            console.log(data);
            showAllCoach();
        }
    })
}

function showFormUpdate(element) {
    let id = element.getAttribute("href");
    $.ajax({
        headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json'
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        type: "get", url: "http://localhost:2828/admin/coach/" + id,
        success: function (data) {
            console.log(data);
            console.log(id);
            $('#id-coach').attr('value', `${data.id}`)
            $('#name').attr('value', `${data.name}`)
            $('#country').attr('value', `${data.country}`)
            $('#achievement').attr('value', `${data.achievement}`)
            $('#salary').attr('value', `${data.salary}`)
            $('#role').attr('value', `${data.role}`)
            $('#image').attr('value', `${data.image}`)
        }
    })
    event.preventDefault();
}

function updateCoach() {
    let formData = new FormData();
    let id = $('#id-coach').val();
    let name = $('#name').val();
    let country = $('#country').val();
    let achievement = $('#achievement').val();
    let salary = $('#salary').val();
    let role = $('#role').val();
    let image = $('#image')[0].files[0];
    formData.append('name', name);
    formData.append('country', country);
    formData.append('achievement', achievement);
    formData.append('salary', salary);
    formData.append('role', role);
    if (image !== undefined){
        formData.append('avaFile', image);
    }
    $.ajax({
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        dataType: "json",
        type: "PUT",
        url: "http://localhost:2828/admin/coach/" + id,
        data: formData,
        success: function (data) {
            console.log(data);
            showAllCoach();
        }
    })

    event.preventDefault();
}

function deleteCoach(element) {
    let id = element.getAttribute("href");
    $.ajax({
        type: "delete",
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        url: "http://localhost:2828/admin/coach/" + id,
        success: function (date) {
            console.log("Xoa thanh cong ");
            showAllCoach();
        }
    })
    event.preventDefault();
}

function sortSalaryAsc() {
    $.ajax({
        headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json'
        }, type: "get", url: "http://localhost:2828/admin/coach/sortAsc/", success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
        <td>${data[i].id}</td>
        <td>${data[i].name}</td>
        <td>${data[i].country}</td>
        <td>${data[i].achievement}</td>
        <td>${data[i].salary}</td>
        <td>${data[i].role}</td>
        <td><img src="${'http://localhost:2828' + data[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data[i].id}" onclick="deleteCoach(this)">Delete</a></td>
        <td><a href="${data[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;

            console.log(data);

            console.log("sort thanh cong")

        }
    })
}

function searchCoachByName() {
    let search = $('#search').val();

    $.ajax({
        headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json'
        }, type: "GET", url: "http://localhost:2828/admin/coach/search?name=" + search, success: function (data) {
            console.log(data.content[0])
            let content = "";
            for (let i = 0; i < data.content.length; i++) {
                content += `<tr>
        <td>${data.content[i].id}</td>
        <td>${data.content[i].name}</td>
        <td>${data.content[i].country}</td>
        <td>${data.content[i].achievement}</td>
        <td>${data.content[i].salary}</td>
        <td>${data.content[i].role}</td>
        <td><img src="${'http://localhost:2828' + data.content[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data.content[i].id}" onclick="deleteCoach(this)">Delete</a></td>
        <td><a href="${data.content[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;
            console.log(data);
        }
    })
    event.preventDefault();
}

function searchByRole() {
    let search = $('#searchrole').val();
    $.ajax({
        headers: {
            'Accept': 'application/json', 'Content-Type': 'application/json'
        }, type: "GET", url: "http://localhost:2828/admin/coach/role?role=" + search, success: function (data) {
            console.log(data.content[0])
            let content = "";
            for (let i = 0; i < data.content.length; i++) {
                content += `<tr>
        <td>${data.content[i].id}</td>
        <td>${data.content[i].name}</td>
        <td>${data.content[i].country}</td>
        <td>${data.content[i].achievement}</td>
        <td>${data.content[i].salary}</td>
        <td>${data.content[i].role}</td>
        <td><img src="${'http://localhost:2828' + data.content[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data.content[i].id}" onclick="deleteCoach(this)">Delete</a></td>
        <td><a href="${data.content[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;
            console.log(data);
            console.log("sort thanh cong")
        }
    })
    event.preventDefault();
}

 // Player
function showAllPlayer() {
    $.ajax({
        type: "get",
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        url: "http://localhost:2828/admin/player/list",
        success: function (data) {
            console.log(data)
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += `
                 <tr>        
                    <td>
                     <div class="d-flex px-2 py-1">
                      <div>
                         <img src="${'http://localhost:2828' + data[0].avatarURL}" class="avatar avatar-sm me-3" alt="user1">
                      </div>
                      <div class="d-flex flex-column justify-content-center">
                            <h6 class="mb-0 text-sm">${data[i].name}</h6>
                                <p class="text-xs text-secondary mb-0">${data[0].country}</p>
                      </div>
                      </div>
                    </td>
                    <td>
                     <p class="text-xs font-weight-bold mb-0">${data[i].position}</p>
                    </td>
                    <td class="align-middle text-center text-sm">
                      <span class="badge badge-sm bg-gradient-success">${data[i].status.state}</span>
                    </td>
                    <td class="align-middle text-center">
                      <span class="text-secondary text-xs font-weight-bold">${data[i].height}</span>
                    </td>
                    <td class="align-middle text-center">
                      <span class="text-secondary text-xs font-weight-bold">${data[i].weight}</span>
                    </td>
                    <td class="align-middle text-center">
                      <span class="text-secondary text-xs font-weight-bold">${data[i].salary} $</span>
                    </td>
                    <td class="align-middle text-center">
                      <span class="text-secondary text-xs font-weight-bold">${data[i].performance}</span>
                    </td>
                    <td class="align-middle">
                      <a href="${data[i].id}"  class="text-secondary font-weight-bold text-xs" 
                      data-toggle="tooltip" data-original-title="Edit user" onclick="showFormUpdatePlayer(this)">Edit</a>
                      <a href="${data[i].id}"  class="text-secondary font-weight-bold text-xs" 
                      data-toggle="tooltip" data-original-title="Edit user" onclick="deletePlayer(this)">Delete</a>
                    </td>
                    </tr>`;
            }
            document.getElementById("listPlayer").innerHTML = content;
        }
    })
}
showAllPlayer();

function createPlayer() {
    let formData = new FormData();
    let name = $('#name').val();
    let country = $('#country').val();
    let weight = $('#weight').val();
    let height = $('#height').val();
    let salary = $('#salary').val();
    let introduction = $('#introduction').val();
    let status = $('#status').val();
    let performance = $('#performance').val();
    let image = $('#image')[0].files[0];
    formData.append('name', name);
    formData.append('country', country);
    formData.append('weight', weight);
    formData.append('height', height);
    formData.append('salary', salary);
    formData.append('introduction', introduction);
    formData.append('status', status);
    formData.append('performance', performance);
    if (image !== undefined){
        formData.append('avaFile', image);
    }
    $.ajax({
        contentType: false,
        processData: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        enctype: 'multipart/form-data',
        dataType: "json",
        type: "POST",
        url: "http://localhost:2828/coach/player/create",
        data: formData,
        success: function (data) {
            console.log(data);
            showAllPlayer();
        }
    })
}

function showFormUpdatePlayer(element){
    let id = element.getAttribute("href");
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        type: "get",
        url: "http://localhost:2828/player/find-player-by-id/"+id,
        success: function (data) {
            console.log(data);
            console.log(id);
            $('#id').attr('value',`${data.id}`)
            $('#name').attr('value',`${data.name}`)
            $('#country').attr('value',`${data.country}`)
            $('#weight').attr('value',`${data.weight}`)
            $('#height').attr('value',`${data.height}`)
            $('#introduction').attr('value',`${data.introduction}`)
            $('#salary').attr('value',`${data.salary}`)
            $('#image').attr('value',`${data.image}`)
        }
    })
    event.preventDefault();
}

function updatePlayer() {
    let formData = new FormData();
    let name = $('#name').val();
    let country = $('#country').val();
    let weight = $('#weight').val();
    let height = $('#height').val();
    let salary = $('#salary').val();
    let introduction = $('#introduction').val();
    let status = $('#status').val();
    let performance = $('#performance').val();
    let image = $('#image')[0].files[0];
    formData.append('name', name);
    formData.append('country', country);
    formData.append('weight', weight);
    formData.append('height', height);
    formData.append('salary', salary);
    formData.append('introduction', introduction);
    formData.append('status', status);
    formData.append('performance', performance);
    if (image !== undefined){
        formData.append('avaFile', image);
    }
    $.ajax({
        contentType: false,
        processData: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        enctype: 'multipart/form-data',
        dataType: "json",
        type: "put",
        url: "http://localhost:2828/coach/player/edit/"+id,
        data: formData,
        success: function (data) {
            console.log(data);
            showAllPlayer();
        }
    })
    event.preventDefault();
}

function deletePlayer(element){

    let id=element.getAttribute("href");
    $.ajax({
        type: "delete",
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Bearer " + token);
        },
        url: "http://localhost:2828/coach/player/delete/"+id,
        success:function (date){
            console.log("Xoa thanh cong ");
            showAllPlayer();
        }
    })
    event.preventDefault();
}