//JS
layui.use(['element', 'layer', 'util'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var util = layui.util;
    var $ = layui.$;

    //获得登录用户信息
    $.ajax({
        url: "/getUser",
        type: "get",
        dataType: "json",
        success: function (res) {
            if (res.status == 1) {
                $("#loginUser").text(res.data.username);
                if (res.data.sex == '男') {
                    $("#userImg").attr("src", "/images/img/man.jpg");
                } else {
                    $("#userImg").attr("src", "/images/img/wman.jpg");
                }
            } else {
                layer.msg("你还未登录！");
                window.location.href = "/logout";
            }
        }
    });

    //获得用户的菜单信息
    $.ajax({
        url: "/getMenuDto",
        type: "post",
        dataType: "json",
        success: function (res) {
            console.log(res)
            for (var i = 0; i < res.data.length; i++) {
                var menu = res.data[i];
                if (menu.menuType == "M") {
                    $("#menuList").append("<li class=\'layui-nav-item\'>" +
                        "<a class=\'\' href=\'javascript:;\'><img class=\'imgIcon\' src=\'" + menu.icon + "\'>" + menu.menuName + "</a>" +
                        "<dl class=\'layui-nav-child\' id=\'M" + menu.menuId + "\'>" +
                        "</dl>" +
                        "</li>"
                    );
                } else if (menu.menuType == "C") {
                    $("#M" + menu.parentId + "").append("<dd><a href=\'" + menu.url + "\' target=\'mainFrame\' lay-on=\'tabAdd\'>" + menu.menuName + "</a></dd>");
                }
            }
            element.render();
        }
    });
    //判断选项卡是否存在
    var tabExists = {};
    // 给menuList下a标签绑定事件
    $('#menuList').on('click', 'a', function () {
        // 获取lay-on的内容
        var layOn = $(this).attr('lay-on');
        //如果内容为tabAdd 则添加选项卡
        if (layOn == "tabAdd") {
            var content = $(this).attr('href');//选中菜单的url
            var menuName = $(this).text(); // 获取菜单名称
            var id = "tab-" + menuName;
            console.log(tabExists);
            if (!tabExists[id]) {
                element.tabAdd('test-handle', {
                    title: menuName,//选向卡名称
                    content: content,//选项卡内容
                    id: id // 选项啦id
                })
                tabExists[id] = true;
            }
        }
        // 自定义tab 删除事件
        element.on('tabDelete(test-handle)', function (data) {
            var tabTitle = $(this).parent('li');
            var id = tabTitle.attr('lay-id'); // 获取被删除选项卡的id
            delete tabExists[id]; // 从tabExists中删除对应的选项卡记录
        });
        //自定义切换事件
        element.on('tab(test-handle)', function (data) {
            var tabContent = $('.layui-tab-content');//获取页面隐藏选项卡内容的div
            var tabItem = tabContent.find('.layui-tab-item:eq(' + data.index + ')'); // 获取指定下标的元素
            var content = tabItem.text(); // 获取元素的文本内容也就是url
            $('#mainFrame').attr('src', content);//切换内容
        });
    });
});