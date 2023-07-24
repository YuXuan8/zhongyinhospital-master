功能说明：layui消息通知插件 6种样式，可手动关闭层，支持回调方法，可设置弹出位置，兼容主流浏览器，兼容至ie9 (ie没有svg动画)
![输入图片说明](https://images.gitee.com/uploads/images/2021/0731/194243_b57791df_1949066.png "20210731194204.png")

 **1、7种调用方法：** 

普通：info

警告：warning

成功：success

错误：error

加载：loading

模态框：alert、confirm

关闭：destroyAll

 **2、layui使用方法** 


```
layui.use(['notify'],function(){
     var notify=layui.notify;
     notify.info("自定义消息");
})
```
基本用法

参数说明：对参数没有顺序要求，会根据参数的数据类型进行解析，


```
        msg: "", //文字内容
        position: 'topCenter', // 弹出位置bottomRight, bottomLeft, topRight, topLeft, topCenter, bottomCenter, vcenter
        duration: 2000, //默认2秒关闭
        showClose: true //显示关闭按钮
        shadow:false //是否显示遮罩
```




>                 1、notify.info("提示消息");
>                 2、notify.warning("警告消息");
>                 3、notify.success("成功消息");
>                 4、notify.loading("加载中");
>                 5、notify.error("失败消息");
>                 6、notify.info("不显示关闭按钮", false);
>                 7、notify.warning("提示消息", function () {
>                     alert("回调成功");
>                 });
>                 8、notify.destroyAll(); //全部关闭
>                 9、notify.success("指定位置显示", "topLeft"); //参数：topLeft、topCenter、topRight、bottomLeft、bottomCenter、bottomRight、vcenter
>                 10、notify.alert("模态框", "vcenter","shadow"); //参数：shadow 显示遮罩
>                 11、notify.confirm("确认框", "vcenter","shadow"，function(){
>                         alert("回调方法")
>                     }); //参数：shadow 显示遮罩 、function 确定后回调方法

演示地址：[https://www.17780.cn/notify/demo.html](https://www.17780.cn/notify/demo.html)