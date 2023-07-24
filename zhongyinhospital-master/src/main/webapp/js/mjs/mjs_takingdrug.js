$(window).preloader();

Split(['#myprescription', '#takingDrugOperation'], {
    sizes: [65, 35],
    minSize: [800, 400]
});

function getMedicalRecord() {
    var prescriptionNum = $("#prescriptionNum_input").val();

    if (prescriptionNum == null || prescriptionNum === '') {
        swal("请填写处方号！", "", "error");
        return false;
    }

    $.ajax({
        url: "/api/tollTakedrug/getMedicalRecord",
        type: "post",
        data: {
            "prescriptionNum": prescriptionNum
        },
        success: function (da) {
            var data =da.data
            console.log(data);
            if (da !== null && da.status == 1) {
                $("#name").val(data.name);
                $("#sex").val(data.sex);
                $("#nationality").val(data.nationality);
                $("#age").val(data.age);
                $("#prescriptionNum").val(prescriptionNum);
                var date = new Date(data.createDatetime);
                var dateString = date.toLocaleString(); // 将时间戳转换为本地日期字符串
                $("#createDate").val(dateString);
                $("#department").val(data.department);
                $("#diagnosisResult").val(data.diagnosisResult);
                $("#medicalOrder").html(data.medicalOrder);
                $("#drugCost").val(data.drugCost);
                $("#doctorName").val(data.doctorName);
                $("#prescription").html(data.prescription);
                $("#examinationCost").val(data.examinationCost);
                $("#nowDate").html(data.nowDate);
            } else {
                swal(da.message, "", "error")
            }
        }
    })
}

function saveTakingDrugInfo() {

    var prescriptionNum = $("#prescriptionNum").val();
    var department = $("#department").val();
    if(department == null || department === ''){
        return false;
    }
    if (prescriptionNum == null || prescriptionNum === '') {
        swal("请先查询处方信息！", "", "error");
        return false;
    }

    $.ajax({
        url: "/api/tollTakedrug/saveTakingDrugInfo",
        type: "post",
        data: {
            "prescriptionNum": prescriptionNum
        },
        success: function (data) {

            if (data !== null && data.status == 1) {
                swal({
                    title: "提交成功！",
                    type: "success",
                    showCancelButton: true,
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                }, function () {
                    window.location.reload()
                })
            } else {
                swal(data.message, "", "error")
            }
        }

    })
}