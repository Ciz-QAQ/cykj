<%--
  Created by IntelliJ IDEA.
  User: 瓷甃
  Date: 2020/6/8
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>心理咨询平台后台登入</title>

    <%
        String path=request.getContextPath();//获取工程路径
        String userName= (String) request.getAttribute("userName");
    %>

    <script src=<%=path+"/js/jquery-3.5.1.js"%> type="text/javascript"></script>
    <script src=<%=path+"/js/login.js"%>  charset="utf-8" type="text/javascript"></script>

    <style>
        *{
            margin: 0;
            padding: 0;
        }

        html,body{
            height: 100%;
            width: 100%;
        }

        body{
            background-repeat: no-repeat;
            background-image: url(<%=path+"/img/background1.jpg"%>);
            background-size: 100% 100%;
        }

        #tableDiv{
            margin: 100px auto;
            position: center;
            text-align: center;
            width: 200px;
            height: 350px;
            border-radius:20px;
            border: inset darkgray;
            background-color: rgba(224, 222, 222, 0.5);
        }

        td{
            text-align: center;
            padding-bottom: 20px;
        }

        th{
            /*text-align: center;*/
            padding-top: 10px;
            padding-bottom: 20px;
            margin-left: 80px;
        }

        .midStyle{
            text-align: center;
            margin-left: 25px;
        }

        #toRegister{
            /*text-align: right;*/
            margin-left: 120px;
            display: inline;
        }

        input{
            border-radius: 15px;
            border: thin;
        }

        #adminLogin{
            width: 70px;
            height: 25px;
            margin-left: 92%;
        }

    </style>
</head>
<body onclick="show()">

<div id="body" style="overflow: hidden">

    <input type="hidden" id="path" value=<%=path%>>

    <input type="button" id="adminLogin" onclick="toUserLogin()" value="用户页面">

    <div id="tableDiv" style="overflow: hidden">
        <table style="text-align: center;">
            <tr>
                <th><h2 style="margin-left: 30px">后台登入</h2></th>
            </tr>
            <tr>
                <td>
                    <hr size="1" color="black"/>
                </td>
            </tr>
            <tr>
                <td><input class="midStyle" type="text" id="account" name="account" onblur="" placeholder="请输入账号"></td>
            </tr>
            <tr>
                <td><input class="midStyle" type="password" id="psd" name="psd" placeholder="请输入密码"></td>
            </tr>
            <tr>
                <td><input class="midStyle" id="vCodeUser" name="vCodeUser" type="text" placeholder="请输入验证码"></td>
            </tr>
            <tr>
                <td>
                    <img class="imgVCode" onclick="changeImg()" style="width: 80px; height: 35px;display: inline" name="vCodeImg" id="vCodeImg" src=<%=path+"/verifyCodeServlet"%>>
                    <a class="imgVCode" id="changeImg" href="javascript:void(0);" style="display: inline" onclick="changeImg()">换一个</a>
                </td>
            </tr>
            <tr>
                <td>
                    <input class="midStyle" style="width: 100px;height: 35px;" type="button" onclick="adminLogin()" id="loginBtn" value="登入">

                </td>
            </tr>
        </table>
    </div>
</div>

</body>
</html>
