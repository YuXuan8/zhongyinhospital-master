    $(window).preloader();


var department = '';
var registerType = '';

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
        $('#registerRecord').bootstrapTable({
            url: '/api/menu/page',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            // sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            queryParamsType: "",
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            height: 550,
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "cardId",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'no',
                title: '序号',
                sortable: true,
                align: "center",
                // width: 40,
                formatter: function (value, row, index) {
                    //获取每页显示的数量
                    var pageSize = $('#registerRecord').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#registerRecord').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1
                }

            }, {
                field: 'menuId',
                title: 'ID',
                align: 'center',
                valign: 'middle'

            }, {
                field: 'menuName',
                title: '菜单名称',
                align: 'center',
                valign: 'middle',
            }, {
                field: 'orderNum',
                title: '显示顺序',
                align: 'center',
                valign: 'middle',
            }, {
                field: 'url',
                title: '请求地址',
                align: 'center',
                valign: 'middle',
            }, {
                field: 'perms',
                title: '权限标识',
                align: 'center',
                valign: 'middle',
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'middle',
                formatter: addFunctionAlty//表格中增加按钮
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.pageSize,   //页面大小
            pageNumber: params.pageNumber - 1,//页码
            department: department,
            registerType: registerType,
            startTime: $(".startTime").val(),
            endTime: $(".endTime").val()
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

//操作
function addFunctionAlty(value, row, index) {
    if (row.annStatus == 0) {
        return [
            '<button id="btn_modify" class="btn btn-outline-primary" >修改</button>&emsp;',
            '<button id="btn_delete" class="btn btn-outline-danger">删除</button>&emsp;',
        ].join('');
    } else {
        return [
            '<button id="btn_modify" class="btn btn-outline-primary" >修改</button>&emsp;',
            '<button id="btn_delete" class="btn btn-outline-danger">删除</button>&emsp;',
        ].join('');
    }
}


window.operateEvents = {

    // 修改
    "click #btn_modify": function (e, value, row, index) {
        //弹出模态框
        window.location.hash = "#mymodal_2";
        $("#change_id").val(row.id);
        $("#title").val(row.title);
        $("#contents").val(row.contents);
    },
    // 删除
    'click #btn_delete': function (e, value, row, index) {
        window.location.hash = "#mymodal_1";
        $("#delete_id").val(row.id);
    },
    //添加到主页
    'click #btn_add': function (e, value, row, index) {
        window.location.hash = "#mymodal_3";
        $("#add_id").val(row.id);
    },
    //从主页移除
    'click #btn_sub': function (e, value, row, index) {
        window.location.hash = "#mymodal_4";
        $("#sub_id").val(row.id);
    }
};

//日期选择初始化
$(".startTime").flatpickr({
    maxDate: "today",
});
$(".endTime").flatpickr({
    minDate: "today",
});



function searchRegisterRecord() {

    $('#registerRecord').bootstrapTable('destroy');
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

}