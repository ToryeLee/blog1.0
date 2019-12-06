<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style.css">


  </head>
  <!--  onfocus="if(value=='必填'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='必填'}" -->
  <body>
  <c:if test="${requestScope.flag==-1 }"><script>alert("注册失败！用户已存在")</script></c:if>
  <c:if test="${requestScope.flag==0 }"><script>alert("注册失败！密码不一致，请重新输入")</script></c:if>
  <c:if test="${requestScope.flag==2 }"><script>alert("注册失败！后台原因")</script></c:if>
  <c:if test="${requestScope.flag==3 }"><script>alert("注册失败！账号，密码，昵称 不可为空！")</script></c:if>
  
    <div class="register">
    <h3>新用户注册</h3>
    <form action="registerServlet" method="post">
    <table>
    <tr><td><p>账号：</td>
    	<td><input type="text" name="userid" size=35 maxlength=10 value="${requestScope.userid }">必填</td></tr>
    <tr><td><p>密码：</td><td><input type="password" name="userpsw1" size=35 maxlength=10  >必填</td></tr>
    <tr><td><p>确认密码：</td><td><input type="password" name="userpsw2" size=35 maxlength=10  >必填</td></tr>
    <tr><td><p>用户昵称：</td><td><input type="text" name="logname" size=35 maxlength=34 value="${requestScope.logname }" >必填</td></tr>
    <tr><td>性别</td><td><input type="radio" name="gender" value="男">男 <input type="radio" name="gender" id="女">女</td></tr>
    <tr><td><p>电话号码：</td><td><input type="text" name="phone" size=35 maxlength=11 value="${requestScope.phone }" ></td></tr>
    <tr><td><p>邮箱地址：</td><td><input type="text" name="email" size=35 maxlength=40 value="${requestScope.email }" ></td></tr>
    <tr><td><p>教育程度：</td><td><input type="text" name="education" size=35 maxlength=10 value="${requestScope.education }" ></td></tr>
    <tr>
        <td>密码召回问题</td>
        <td><input type="text" name="problem"  maxlength=40 value="${requestScope.problem }">
	    </td>
	  </tr>
      <tr>
        <td>密码问题答案</td>
        <td><input type="text" name="answer" maxlength=40 value="${requestScope.answer }" >
	    </td>
	  </tr>
    <tr><td><p>自我介绍：</td><td><textarea name="selfintro" rows="5" cols="40" onKeyUp="if(this.value.length>100)this.value=this.value.substr(0,100)">${requestScope.selfintro }</textarea></td></tr>
    </table>
    <input type="submit" name="submition" value="注册"><input type="submit" name="submition" value="取消">
    </form>
    </div>
  </body>
</html>
