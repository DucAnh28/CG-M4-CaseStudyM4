function showAllCoach(){
    $.ajax({
            type:"get",
            url:"http://localhost:2828/coach",
            success:function (data){
                let content="";
                for (let i=0;i<data.length;i++){
                    content+=`<tr>
        <td>${data[i].id}</td>
        <td>${data[i].name}</td>
        <td>${data[i].country}</td>
        <td>${data[i].achievement}</td>
        <td>${data[i].salary}</td>
        <td>${data[i].role}</td>
        <td><img src="${'http://localhost:2828'+data[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data[i].id}" onclick="deleteCoach(this)">Delete</a></td>
        <td><a href="${data[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
                }
                document.getElementById("list").innerHTML = content;

            }
        }
    )
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
    formData.append('avaFile', image);
    $.ajax({
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        dataType: "json",
        type: "POST",
        url: "http://localhost:2828/coach",
        data: formData,
        success: function (data) {
            console.log(data);
            showAllCoach();
        }
    })

}
function showFormUpdate(element){
    let id = element.getAttribute("href");
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "get",
        url: "http://localhost:2828/coach/"+id,
        success: function (data) {
            console.log(data);
            console.log(id);
            $('#id').attr('value',`${data.id}`)
            $('#name').attr('value',`${data.name}`)
            $('#country').attr('value',`${data.country}`)
            $('#achievement').attr('value',`${data.achievement}`)
            $('#salary').attr('value',`${data.salary}`)
            $('#role').attr('value',`${data.role}`)
            $('#image').attr('value',`${data.image}`)
        }

    })

    event.preventDefault();
}

function updateCoach() {
    let formData = new FormData();
    let id = $('#id').val();
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
    formData.append('avaFile', image);
    $.ajax({
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        dataType: "json",
        type: "put",
        url: "http://localhost:2828/coach/"+id,
        data: formData,
        success: function (data) {
            console.log(data);
            showAllCoach();
        }
    })

    event.preventDefault();
}

function deleteCoach(element){

    let id=element.getAttribute("href");
    $.ajax({
        type: "delete",
        url: "http://localhost:2828/admin/coach/"+id,
        success:function (date){
            console.log("Xoa thanh cong ");
            showAllCoach();
        }
    })
    event.preventDefault();
}
function sortSalaryAsc(){
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type:"get",
        url: "http://localhost:2828/coach/sortAsc/",
        success:function (data){
            let content="";
            for (let i=0;i<data.length;i++){
                content+=`<tr>
        <td>${data[i].id}</td>
        <td>${data[i].name}</td>
        <td>${data[i].country}</td>
        <td>${data[i].achievement}</td>
        <td>${data[i].salary}</td>
        <td>${data[i].role}</td>
        <td><img src="${'http://localhost:2828'+data[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data[i].id}" onclick="deleteCoach(this)">Delete</a></td>
        <td><a href="${data[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;

            console.log(data);

            console.log("sort thanh cong")

        }
    })
}

function sortSalaryDesc(){
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type:"get",
        url: "http://localhost:2828/coach/sortDesc/",
        success:function (data){
            let content="";
            for (let i=0;i<data.length;i++){
                content+=`<tr>
        <td>${data[i].id}</td>
        <td>${data[i].name}</td>
        <td>${data[i].country}</td>
        <td>${data[i].achievement}</td>
        <td>${data[i].salary}</td>
        <td>${data[i].role}</td>
        <td><img src="${'http://localhost:2828'+data[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data[i].id}" onclick="deleteCoach(this)">Delete</a></td>
        <td><a href="${data[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;

            console.log(data);

            console.log("sort thanh cong")

        }
    })
}

function searchCoachByName(){
    let search= $('#search').val();

    $.ajax({
        headers:{
            'Accept':'application/json',
            'Content-Type': 'application/json'
        },
        type:"GET",
        url: "http://localhost:2828/coach/search?name="+search,
        success:function (data){
            console.log(data.content[0])
            let content="";
            for (let i=0;i<data.content.length;i++){
                content+=`<tr>
        <td>${data.content[i].id}</td>
        <td>${data.content[i].name}</td>
        <td>${data.content[i].country}</td>
        <td>${data.content[i].achievement}</td>
        <td>${data.content[i].salary}</td>
        <td>${data.content[i].role}</td>
        <td><img src="${'http://localhost:2828'+data.content[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data.content[i].id}" onclick="deleteCoach(this)">Delete</a></td>
        <td><a href="${data.content[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;

            console.log(data);



        }
    })
    event.preventDefault();
}

function searchByRole(){
    let search= $('#searchrole').val();

    $.ajax({
        headers:{
            'Accept':'application/json',
            'Content-Type': 'application/json'
        },
        type:"GET",
        url: "http://localhost:2828/coach/role?role="+search,
        success:function (data){
            console.log(data.content[0])
            let content="";
            for (let i=0;i<data.content.length;i++){
                content+=`<tr>
        <td>${data.content[i].id}</td>
        <td>${data.content[i].name}</td>
        <td>${data.content[i].country}</td>
        <td>${data.content[i].achievement}</td>
        <td>${data.content[i].salary}</td>
        <td>${data.content[i].role}</td>
        <td><img src="${'http://localhost:2828'+data.content[i].avatarURL}" width="80" height="80" alt="img"></td>
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
function checkValue(){
    let value=$('.check').val();
    if (value==1){
        sortSalaryAsc();
    }
    if (value==2){
        sortSalaryDesc();
    }
}
