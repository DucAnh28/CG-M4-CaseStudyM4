function showAllCoach(){
    $.ajax({
        type: "get",
        url: "http://localhost:2828/coach",
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
                                <div class="img" style="background-image: url(coach/images/2021.jpg);"></div>
                               
                                <div class="pl-3 email">
                                    <span>${data[i].name}</span>
                                    <span>Added: 01/03/2020</span>
                                </div>
                            </td>
                            <td class="border-bottom-0">${data[i].country}</td>
                            <td class="status border-bottom-0"><span class="waiting">${data[i].achievement}</span>
                            </td>
                            <td class="border-bottom-0">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close" ">
                                    <span aria-hidden="true"><i class="fa fa-close" onclick="deleteCoach()"></i></span>
                                </button>
                            </td>
                        </tr>`;
            }
            document.getElementById("list").innerHTML = content;
            console.log(data.avatar)
        }
    })
}
showAllCoach();
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