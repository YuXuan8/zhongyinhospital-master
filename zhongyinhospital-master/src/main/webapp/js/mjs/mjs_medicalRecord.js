$(window).preloader();

$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
});
var TableInit = function () {
    var oTableInit = {};
    //初始化Table
    oTableInit.Init = function () {
        $('#medicalRecord').bootstrapTable({
            url: '/api/medicalRecord/page',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,
            //是否启用排序
            responseHandler: function (res) {
                console.log(res)
                return {
                    "total": res.total,
                    "rows": res.list
                };
            },
            // sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            queryParamsType: "",
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "cardId",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'cardId',
                title: '卡号',
                sortable: true,
                align: "center",
                // width: 40,
                /*formatter: function (value, row, index) {
                    console.log(row);
                    //获取每页显示的数量
                    var pageSize = $('#medicalRecord').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#medicalRecord').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1
                }*/
            },{
                field: 'prescriptionNum',
                title: '处方号',
                align: 'center',
                valign: 'middle'

            }, {
                field: 'name',
                title: '名称',
                align: 'center',
                valign: 'middle'

            }, {
                field: 'conditionDescription',
                title: '主诉',
                align: 'center',
                valign: 'middle',
            }, {
                field: 'diagnosisResult',
                title: '诊断结果',
                align: 'center',
                valign: 'middle',
            }, {
                field: 'medicalOrder',
                title: '医嘱',
                align: 'center',
                valign: 'middle',
            },//;//'取药状态：-1未付款 0未取药 1已取药',
            {
                field: 'takingDrugStatus',
                    title: '状态',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return row.takingDrugStatus==-1?"未付款":(row.takingDrugStatus==0?"未取药":"已取药");
                }
            },{
                field: 'createDatetime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.pageSize,   //页面大小
            pageNum: params.pageNumber,//页码
            name: $(".patientOrUserName").val()
        };
        return temp;
    };
    return oTableInit;
};

var ButtonInit = function () {
    var oInit = {};
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};

function searchmedicalRecord() {
    $('#medicalRecord').bootstrapTable('destroy');
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

}