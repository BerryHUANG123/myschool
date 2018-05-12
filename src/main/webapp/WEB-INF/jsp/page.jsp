<%--
  Created by IntelliJ IDEA.
  User: DXM_0042
  Date: 2018/4/4
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>你好,用户!</title>
</head>
<body>
<h1>你好,用户!</h1>
<div>
    姓名:<input type="text" id="name"/><br/>
    性别:<input type="text" id="sex"/><br/>
    年龄:<input type="text" id="age"/><br/>
    <button type="button" id="addUserBtn">提交</button>
</div>
<script src="../../static/js/jquery/jquery-core.js"></script>
<script type="text/javascript">
    //获取当前location
    var getBaseUrl = function () {
        var location = (window.location.href).split('/');
        return location[0] + '//' + location[2] + '/';
    };
    $("#addUserBtn").click(function () {
        $.post(
            getBaseUrl() + "user/add.json",
            {
                name: $("#name").val(),
                sex: $("#sex").val(),
                age: $("#age").val()
            },
            function (data) {
                alert(data.code);
            }
        );
    });
</script>
</body>
</html>
