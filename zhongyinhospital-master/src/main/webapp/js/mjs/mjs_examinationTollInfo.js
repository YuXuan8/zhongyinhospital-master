$(window).preloader();


function getExaminationTollInfo(command) {
    var GetCardIdInforReqVO = {
        command: command, //0:表示读卡器输入卡号 1:表示手动输入卡号
        cardId: parseInt($("#cardId").val())
    };
    $.ajax({
        url: "/api/toll/getexaminationtoll",
        type: "post",
        data: GetCardIdInforReqVO,
        success: function (da) {
            if (null != da && da.status == 1) {
                let data=da.data;
                $("#name").val(data.name);
                $("#sex").val(data.sex);
                $("#nationality").val(data.nationality);
                $("#age").val(data.age);
                $("#bodyTemperature").val(data.bodyTemperature);
                $("#heartRate").val(data.heartRate);
                $("#bloodPressure").val(data.bloodPressure);
                $("#examinationCost").val(data.examinationCost);
                $("#prescriptionNum").val(data.prescriptionNum);
                $("#pulse").val(data.pulse);

                $("#registerId").val(data.registerId);
            } else {
                swal(da.message, "", "error")
            }
        }
    })
}

/*保存体检收费记录*/
function saveExaminationTollInfo() {

    var payPrice = $("#payMoney").val();
    var Change = $("#Change").val();
    if (payType == null || payType === '') {
        swal("请先选择支付方式！", "", "error");
        return false;
    } else if (payType=='现金'&&(payMoney == null || payMoney === '')) {
        swal("请输入支付现金金额！", "", "error");
        return false;
    }else if (payType=='现金'&&(Change == null || Change <0)) {
        swal("金额不对，请从新输入！", "", "error");
        return false;
    }else{
        $.ajax({
            url: "/api/toll/saveexaminationtollinfo",
            type: "post",
            data: {
                "id": $("#registerId").val(),
                "payType": payType,
                "payPrice": payPrice,
                "changePrice": Change
            },
            success: function (data) {

                if (null != data && data.status === 1) {
                    swal({
                        title: "保存成功！",
                        type: "success",
                    }, function () {
                        setTimeout(function () {
                            window.location.reload()
                        }, 500)
                    })
                } else {
                    swal(data.message, "", "error")
                }
            }
        })
    }
}

var payType = '';

$('.payType').chosen({disable_search: true}).change(function () {

    payType = $(".payType option:selected").val();

    if (payType === "现金") {
        $("#money").css("display", "block");
        $("#apay").css("display", "none");
        $("#payMoney").val("");
        $("#Change").val("")
    }
    else if (payType === "支付宝") {
        $("#money").css("display", "none");
        $("#apay").css("display", "block")
    } else {
        $("#money").css("display", "none");
        $("#apay").css("display", "none")
    }
});


function getChange() {
    var m = $("#payMoney").val();
    var n = $("#examinationCost").val();
    var x = m - n;
    $("#Change").val(x)
}
