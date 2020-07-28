<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: 瓷甃
  Date: 2020/6/28
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>蕉警大队欢迎您</title>

    <%
        String path=request.getContextPath();//获取工程路径
    %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/admin_page.js" charset="utf-8" type="text/javascript"></script>

    <style>
        html,body{
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        /*搜索面板列表*/
        .search_panel{
            width: 80%;
        }
        .search_panel_info{
            font-size: 14px;
            color: #888888;
            height: 35px;
        }
        .field{
            width: 180px;
            height: 30px;
            color: #888888;
        }

        /*管理员界面列表*/
        .user_panel{
            width: 98%;
        }
        .user_panel_title{
            background-color: #60707F;
            color: #E4E4E4;
            font-size: 14px;
            height: 35px;
        }
        .user_panel_info{
            font-size: 14px;
            color: #888888;
            height: 35px;
        }
        .btnStyle{
            width: 70px;
            height: 30px;
            text-align: center;
            border-radius: 10px;
            border: 1px solid #60707F;
            color: #60707F;
            background-color: white;
        }

        /*字体颜色*/
        .font{
            color: #888888;
            font-size: 14px;
        }
    </style>

</head>
<body>

<input type="hidden" id="path" value=<%=path%>>

<form action="${pageContext.request.contextPath}/carInfoServlet?methodName=getCarInfo&curPage=1" method="post">
    <table class="search_panel" align="center">
        <tr class="search_panel_info">
            <td width="8%"  >输入车牌号:</td>
            <td width="20%">
                <input class="field" id="carNumber" name="carNumber" type="text" placeholder="" value="${carNumber}"/>
            </td>
            <td width="8%" colspan="3">
                <input type="button" class="btnStyle" value="搜索" onclick="searchInfo()"/>
            </td>
<%--            <td width="8%" colspan="3">--%>
<%--                <input type="button" class="btnStyle" value="新增" data-toggle="modal" data-target="#myModal">--%>
<%--            </td>--%>
        </tr>
    </table>
</form>
<table id="user_tab" class="user_panel" align="center" border="1" bordercolor="#E4E4E4" cellpadding="0" cellspacing="0">
    <tr class="user_panel_title" align="center">

        <td>序号</td>
        <td  width="200px">违章车牌</td>
        <td>车辆颜色</td>
        <td  width="200px">违章时间</td>
        <td  width="200px">违章地点</td>
        <td  width="100px">状态</td>
        <td width="200px">操作</td>
    </tr>

    <c:if test="${not empty pageBean}">
        <c:forEach items="${pageBean.list}" var="i">
            <tr class="user_panel_info" align="center">
                <td>${i.cid}</td>
                <td>${i.cNumber}</td>
                <td>${i.ccolor}</td>
                <td>${i.cDate}</td>
                <td>${i.cPlace}</td>

                <c:choose>
                    <c:when test="${i.state==3}">
                        <td>待审核</td>
                    </c:when>
                    <c:when test="${i.state==4}">
                        <td>违章</td>
                    </c:when>
                    <c:when test="${i.state==5}">
                        <td>误拍</td>
                    </c:when>
                    <c:otherwise>
                        <td></td>
                    </c:otherwise>
                </c:choose>

                <td>
                    <input class="btnStyle" type="button" value="查看详情" onclick="getInfo(this)" data-toggle="modal" data-target="#myModal"/>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>

<table class="function_panel" align="center">
    <c:if test="${not empty pageBean}">
    <tr>
        <td>
            <a href="${pageContext.request.contextPath}/adminServlet?methodName=getPage&curPage=1&uname=${uname}&ustate=${uState}">首页</a>
            <c:if test="${pageBean.curPage!=1}">
                <a href="${pageContext.request.contextPath}/adminServlet?methodName=getPage&curPage=${pageBean.prePage}&uname=${uname}&ustate=${uState}">上一页</a>
            </c:if>
        </td>
        <td>
            ${pageBean.curPage}/${pageBean.totalPage}
        </td>
        <td>
            <c:if test="${pageBean.curPage!=pageBean.totalPage}">
                <a href="${pageContext.request.contextPath}/adminServlet?methodName=getPage&curPage=${pageBean.nextPage}&uname=${uname}&ustate=${uState}">下一页</a>
            </c:if>

            <a href="${pageContext.request.contextPath}/adminServlet?methodName=getPage&curPage=${pageBean.totalPage}&uname=${uname}&ustate=${uState}" >尾页</a>
        </td>
    </tr>
    </c:if>
</table>
<a onclick="goToAdmin()" style="margin-left: 95%">后台界面</a>


<!-- 模态框 -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">违章详情</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <table id="registerTable">
                    <tr>
                        <td><input type="hidden" id="hiddenCID" value=""></td>
                    </tr>
                    <tr>
                        <td><input type="text" placeholder="违章车牌："></td><td><span id="cNumber"></span></td>
                    </tr>
                    <tr>
                        <td><input type="text"  placeholder="车辆颜色："></td><td><span id="cColor"></span></td>
                    </tr>
                    <tr>
                        <td><input type="text"  placeholder="违章时间："></td><td><span id="cDate"></span></td>
                    </tr>
                    <tr>
                        <td><input type="text"  placeholder="违章地点："></td><td><span id="cPlace"></span></td>
                    </tr>
                    <tr>
                        <td>
                            <label>抓拍照片:</label>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/img/sick.gif" id="pic1">
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/img/sick.gif" id="pic2">
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/img/sick.gif" id="pic3">
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/img/sick.gif" id="pic4">
                        </td>
                    </tr>
                </table>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="missTake()">误拍</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">违章</button>
            </div>

        </div>
    </div>
</div>


</body>
</html>
