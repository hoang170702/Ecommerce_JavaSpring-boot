
$(document).ready(()=>{
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "fadeIn": 300,
        "fadeOut": 1000,
        "timeOut": 5000,
        "extendedTimeOut": 1000
    }

    loadCategory();
    addCategory();
    deleteCategory();
    updateCategory();

    // reset form
    $(document).on("click","#reset",function () {
        $("#nameCategory").val('')// Reset form
    });
});

function loadCategory(){
    $.get("/apiCategories/ListCategory",(data)=>{
        $("#lst-category").empty();
        $.each(data, (index, category)=>{
            let categoryHtml = '<tr>' +
                '<td>' + category.id + '</td>' +
                '<td>' + category.name + '</td>' +
                '<td> <div class="button-group">' +
                '<button class="btn btn-warning delete-btn" data-toggle="modal" data-target="#confirmModal" data-id="' + category.id + '">Delete</button>' +
                '<button class="btn btn-primary update-btn" data-toggle="modal" data-target="#confirmModal" data-id="' + category.id + '">Update</button>' +
                '</div></td>' +
                '</tr>';
            $("#lst-category").append(categoryHtml);
        });

    });
};
function cancelAction(){
    $("#cancelAction").click(function () {
        $("#confirmModal").hide();
    });
}

function addCategory() {
    // add category
    $("#frmCategory").submit(function (event){
        event.preventDefault();
        $.ajax({
            url: "/apiCategories/saveCategory",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                name: $("#nameCategory").val(),
            }),
            success: (response) => {
                toastr.success("Category added successfully");
                $("#nameCategory").val('')// Reset form
                loadCategory();
            },
            error: () => {
                toastr.error("Category added failed");
            },
        });
    });
}

function deleteCategory() {
    //delete category
    $(document).on("click", ".delete-btn", function() {
        let categoryId = $(this).attr('data-id');
        $('#confirmAction').off().on("click",function (){
            $.ajax({
                url: "/apiCategories/deleteCategory/" + categoryId,
                method: "GET",
                success: function(response) {
                    toastr.success(response);
                    loadCategory();
                },
                error: function(response) {
                    toastr.error(response);
                }
            });
        });
        cancelAction();
    });
}

function updateCategory() {
    //update category
    $(document).on("click",".update-btn", function (){
        let categoryId = $(this).attr('data-id');
        let nameUpdate = $("#nameCategory").val();

        if(nameUpdate === ""){
            toastr.error("can't empty");
        }else {
            if(nameUpdate){
                let updateNameCategory = {
                    name: nameUpdate
                }
                $('#confirmAction').off().on("click",function (){
                    $.ajax({
                        url: "/apiCategories/updateCategory/" + categoryId,
                        method: "PUT",
                        contentType: "application/json",
                        data: JSON.stringify(updateNameCategory),
                        success: function(response) {
                            $("#nameCategory").val('')// Reset form
                            toastr.success(response);
                            loadCategory();
                        },
                        error: function(response) {
                            toastr.error(response);
                        }
                    });
                });
                cancelAction();
            }
        }
    });
}