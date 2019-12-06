<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page import="pageutil.*"%>
<%@ page import="user.*"%>
<%@ page import="bean.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style.css">

  </head>
  
  <body>
    <form action="UpdateServlet" method="post">
    <% User user=new User();
       user=(User)session.getAttribute("user"); %>
       
    <table>
      <tr>
        <td>修改个人信息</td>
      </tr>
      <tr>
        <td>账号</td><%--1 --%>
        <td><input type="text" name="userid" value="${sessionScope.user.userid}" maxlength=10 readonly >
	    </td>
      </tr>
      <tr>
        <td>昵称</td><%--1 --%>
        <td><input type="text" name="logname" value="${sessionScope.user.logname}" maxlength=16 >
	    </td>
      </tr>

      <tr>
        <td>邮箱</td><%--4 --%>
        <td><input type="text" name="email" value="${sessionScope.user.email}" maxlength=20>
	    </td>
      </tr>
      
      <tr>
        <td>性别</td><%--5 --%>
        <td><input type="radio" name="gender" value="男">男 <input type="radio" name="gender" id="女">女
	    </td>
      </tr>
      
      <tr>
        <td>电话</td><%--7 --%>
        <td><input type="text" name="phone" value="${sessionScope.user.phone}" maxlength=11>
	    </td>
      </tr>
      <tr>
        <td>密码提示问题</td><%--8 --%>
        <td><input type="text" name="problem" value="${sessionScope.user.problem}" maxlength=40>
	    </td>
      </tr>
      <tr>
        <td>密码问题答案</td><%--9 --%>
        <td><input type="text" name="answer" value="" maxlength=40>
	    </td>
      </tr>
      
      <tr>
        <td>教育程度</td><%--11 --%>
        <td><input type="text" name="education" value="${sessionScope.user.education}" maxlength=10> 
	    </td>
      </tr>

      <tr>
        <td>自我介绍</td><%--13 --%>
        <td>
        <textarea name="selfintro"  rows="5" cols="40" onKeyUp="if(this.value.length>100)this.value=this.value.substr(0,100)" >${ sessionScope.user.selfintro }</textarea>
	    </td>
      </tr>
      <tr>
        <td><input type="submit" name="submition" value="提交修改"></td>
        <td><input type="button" value="退出" onclick="window.location.href='index.jsp'" ></td>
      </tr>
    
    </table>
    </form>
  </body>
</html>
