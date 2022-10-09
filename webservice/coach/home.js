
function showAllCoach(){
    $.ajax({
        type:"get",
        url:"http://localhost:2828/coach",
        success:function (data){
            let content="";
            for (let i=0;i<data.length;i++){
                content+=`<tr><td>${data[i].id}</td>
        <td>${data[i].name}</td>
        <td>${data[i].country}</td>
        <td>${data[i].achievement}</td>
        <td>${data[i].salary}</td>
        <td>${data[i].role}</td>
        <td>${data[i].image}</td>
        
        <td><a href="${data[i].id}" onclick="deleteCoach(this)">Delete</a></td></tr>
        <td><a href="${data[i].id}" onclick="showFormUpdate(this)">Update</a></td></tr>`;
            }
            document.getElementById("list").innerHTML = content;

        }
        }
    )
}
showAllCoach();
function deleteCoach(element){

    let id=element.getAttribute("href");
    $.ajax({
        type: "delete",
        url: "http://localhost:2828/coach/"+id,
        success:function (date){
            console.log("Xoa thanh cong ");
            showAllCoach();
        }
    })
    event.preventDefault();
}
function addNewCoach(){

    let name = $('#name').val();
    let country = $('#country').val();
    let achievement = $('#achievement').val();
    let salary = $('#salary').val();
    let role = $('#role').val();
    let image = $('#image').val();
    let newCoach={
        Name:name,
        country:country,
        achievement:achievement,
        salary:salary,
        role:role,
        image:image,
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newCoach),
        url: "http://localhost:2828/coach",
        success: function (){
            console.log("tao thanh cong"+newCoach)
            showAllCoach();
        }
    });
    event.preventDefault();
}
function searchCoachBySalaryAsc(){

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
    let id = $('#id').val();
    let name = $('#name').val();
    let country = $('#country').val();
    let achievement = $('#achievement').val();
    let salary = $('#salary').val();
    let role = $('#role').val();
    let image = $('#image').val();
    let editCoach={
        name:name,
        country:country,
        achievement:achievement,
        salary:salary,
        role:role,
        image:image,
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "put",
        url: "http://localhost:2828/coach/"+id,
        data:JSON.stringify(editCoach),
        success: function (data) {
            console.log("cap nhat thanh cong"+editCoach)
            showAllCoach();
        }
    })

    event.preventDefault();
}

function searchCoachSortSalary(){
   showAllCoach()
    event.preventDefault();
}

