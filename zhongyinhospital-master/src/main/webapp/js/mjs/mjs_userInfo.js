$(window).preloader();
//查询登录用户的信息，并进行渲染到页面中
$(function () {
    $.ajax({
        url: "/getUser",
        type: "get",
        dataType: "json",
        success: function (data) {
            var userInfo = data.data;
            $("#id").val(userInfo.id);
            $("#username").val(userInfo.username);
            /*select同样可以这样赋值*/
            $("#sex").val(userInfo.sex);
            $("#birthday").val(userInfo.birthday);
            $("#politicalStatus").val(userInfo.politicalStatus);
            $("#phone").val(userInfo.phone);
            $("#address").val(userInfo.address);
        }
    })
});
//完善个人信息
function changeUserInfo() {
    //获取页面参数
    var username = $("#username").val();
    //将参数封装到一个userInfor中
    var userInfor = {
        id: $("#id").val(),
        username: username,
        sex: $("#sex").val(),
        birthday: $("#birthday").val(),
        politicalStatus: $("#politicalStatus").val(),
        phone: $("#phone").val(),
        address: $("#address").val()
    };
    $.ajax({
        url: "/api/user/update",
        type: "put",//请求的类型
        contentType: 'application/json',//指定请求的参数以json字符串传递
        data: JSON.stringify(userInfor),//将获取的数据对象转换成json格式字符串
        success: function (data) {
            if (data !== null && data.status === 1) {
                swal({
                    title: "修改成功！",
                    type: "success",
                    showCancelButton: true,
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                });
                /*异步刷新导航栏用户昵称*/
                $("#head_username").html(username);
            } else {
                swal(data.message, "", "error")
            }
        }
    })
}
//重置密码
function changePassword() {
    var ChangePasswordReqVO = {
        password: $("#oldPassword").val(),
        newPassword: $("#newPassword").val(),
        okPassword: $("#okPassword").val()
    };
    $.ajax({
        async: false,
        url: "/api/user/changePassword",
        type: "post",
        contentType: 'application/json',
        data: JSON.stringify(ChangePasswordReqVO),
        success: function (data) {
            if (null != data && data.status === 1) {
                swal(data.message, "", "success");
                /*防止重复提交*/
                $("#changePassword").attr("disabled", true);
                setTimeout("$('#changePassword').removeAttr('disabled')", 6000);
            } else {
                swal(data.message, "", "error")
            }
        }
    })
}