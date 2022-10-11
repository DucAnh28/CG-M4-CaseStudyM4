function showAllPlayer(){
    $.ajax({
        type: "get",
        url: "http://localhost:2828/player/list-player",
        success: function (data) {
            console.log(data);
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += `<tr class="alert" role="alert">
                            <td class="border-bottom-0">
                                <label class="checkbox-wrap checkbox-primary">
                                    <input type="checkbox">
                                    <span class="checkmark"></span>
                                </label>
                            </td>
                            <td class="d-flex align-items-center border-bottom-0">
                                <div class="img" style="background-image: url(images/person_1.jpg);"></div>
                                <div class="pl-3 email">
                                    <span>${data[i].name}</span>
                                    <span>Added: 01/03/2020</span>
                                </div>
                            </td>
                            <td class="border-bottom-0">${data[i].country}</td>
                            <td class="status border-bottom-0"><span class="waiting">${data[i].status.state}</span>
                            </td>
                            <td class="border-bottom-0">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true"><i class="fa fa-close"></i></span>
                                </button>
                            </td>
                        </tr>`;
            }
            document.getElementById("list").innerHTML = content;
        }
    })
}
showAllPlayer();

function showAllPlayer(){
    $.ajax({
            type:"get",
            url:"http://localhost:2828/player/list-player",
            success:function (data){
                let content="";
                for (let i=0;i<data.length;i++){
                    content+=`<tr>
        <td>${data[i].id}</td>
        <td>${data[i].country}</td>
        <td>${data[i].height}</td>
        <td>${data[i].weight}</td>
        <td>${data[i].introduction}</td>
        <td>${data[i].name}</td>
        <td>${data[i].salary}</td>
        <td><img src="${'http://localhost:2828'+data[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data[i].id}" onclick="deletePlayer(this)">Delete</a></td>
        <td><a href="${data[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
                }
                document.getElementById("list").innerHTML = content;

            }
        }
    )
}
showAllPlayer();

//done create player
function createPlayer() {
    let formData = new FormData();
    let name = $('#name').val();
    let country = $('#country').val();
    let weight = $('#weight').val();
    let height = $('#height').val();
    let salary = $('#salary').val();
    let introduction = $('#introduction').val();
    let image = $('#image')[0].files[0];
    formData.append('name', name);
    formData.append('country', country);
    formData.append('weight', weight);
    formData.append('height', height);
    formData.append('salary', salary);
    formData.append('introduction', introduction);
    formData.append('avaFile', image);
    $.ajax({
        contentType: false,
        processData: false,
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


function showFormUpdate(element){
    let id = element.getAttribute("href");
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
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
    let id = $('#id').val();
    let name = $('#name').val();
    let country = $('#country').val();
    let weight = $('#weight').val();
    let height = $('#height').val();
    let introduction = $('#introduction').val();
    let salary = $('#salary').val();
    let image = $('#image')[0].files[0];
    formData.append('name', name);
    formData.append('country', country);
    formData.append('weight', weight);
    formData.append('height', height);
    formData.append('introduction', introduction);
    formData.append('salary', salary);
    formData.append('avaFile', image);
    $.ajax({
        contentType: false,
        processData: false,
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
// done delete player
function deletePlayer(element){

    let id=element.getAttribute("href");
    $.ajax({
        type: "delete",
        url: "http://localhost:2828/coach/player/delete/"+id,
        success:function (date){
            console.log("Xoa thanh cong ");
            showAllPlayer();
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
        url: "http://localhost:2828/player/sort-salary-asc",
        success:function (data){
            let content="";
            for (let i=0;i<data.length;i++){
                content+=`<tr>
        <td>${data[i].id}</td>
        <td>${data[i].country}</td>
        <td>${data[i].height}</td>
        <td>${data[i].weight}</td>
        <td>${data[i].introduction}</td>
        <td>${data[i].name}</td>
        <td>${data[i].salary}</td>
        <td><img src="${'http://localhost:2828'+data[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data[i].id}" onclick="deletePlayer(this)">Delete</a></td>
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
        url: "http://localhost:2828/player/sort-salary-desc/",
        success:function (data){
            let content="";
            for (let i=0;i<data.length;i++){
                content+=`<tr>
      <td>${data[i].id}</td>
        <td>${data[i].country}</td>
        <td>${data[i].height}</td>
        <td>${data[i].weight}</td>
        <td>${data[i].introduction}</td>
        <td>${data[i].name}</td>
        <td>${data[i].salary}</td>
        <td><img src="${'http://localhost:2828'+data[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data[i].id}" onclick="deletePlayer(this)">Delete</a></td>
        <td><a href="${data[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;

            console.log(data);

            console.log("sort thanh cong")

        }
    })
}