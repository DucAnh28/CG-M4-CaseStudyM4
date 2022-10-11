// function getPerformance() {
//     $.ajax({
//         type: "GET",
//         //tên API
//         url: `http://localhost:2828/player/performance`,
//         //xử lý khi thành công
//         success: function (data) {
//             let content = '<select id="performance-player-edit" class="form-control">\n'
//             for (let i = 0; i < data.length; i++) {
//                 content += displayPerformance(data[i]);
//             }
//             content += '</select>'
//             document.getElementById("div-performance-edit").innerHTML = content;
//         }
//     });
// }
//
// getPerformance();

//done show all player
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
    event.preventDefault();
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
    event.preventDefault();

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

function searchPlayerByName(){
    let search= $('#search').val();

    $.ajax({
        headers:{
            'Accept':'application/json',
            'Content-Type': 'application/json'
        },
        type:"GET",
        url: "http://localhost:2828/player/search?name="+search,
        success:function (data){
            console.log(data.content[0])
            let content="";
            for (let i=0;i<data.content.length;i++){
                content+=`<tr>
        <td>${data[i].id}</td>
        <td>${data[i].country}</td>
        <td>${data[i].height}</td>
        <td>${data[i].weight}</td>
        <td>${data[i].introduction}</td>
        <td>${data[i].name}</td>
        <td>${data[i].salary}</td>
        <td><img src="${'http://localhost:2828'+data.content[i].avatarURL}" width="80" height="80" alt="img"></td>
        <td><a href="${data.content[i].id}" onclick="deletePlayer(this)">Delete</a></td>
        <td><a href="${data.content[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;

            console.log(data);



        }
    })
    event.preventDefault();
}