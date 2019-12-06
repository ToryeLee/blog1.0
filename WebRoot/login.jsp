<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style.css">

  </head>
  
  <body>
  <c:if test="${requestScope.flag3==null }">
    <div class="login">
    <h3>登录</h3>
    <c:if test="${requestScope.flag==0 }"><script>alert("账号密码不能为空！！")</script></c:if>
    <c:if test="${requestScope.flag==2 }"><script>alert("密码错误！！")</script></c:if>
    <c:if test="${requestScope.flag==3 }"><script>alert("用户不存在！！")</script></c:if>
    <c:if test="${requestScope.f==4 }"><script>alert("密码重置成功！！")</script></c:if>
    	<form action="LoginServlet" method="post">
        	<p>账号：<input type="text" name="userid" value="${sessionScope.userid }" maxlength="10" > <br/>
            <p>密码：<input type="password" name="userpsw" maxlength="10"> <br/>
            <input type="submit" name="submition" value="登录">
            &nbsp;&nbsp;<input type="submit" name="submition" value="注册">
            &nbsp;&nbsp;<input type="button"  value="取消" onclick="window.location.href='index.jsp'">
            &nbsp;&nbsp;<input type="submit" name="submition" value="找回密码">
    	</form>
    </div>
    </c:if>
    
    
    <c:if test="${requestScope.flag3==1 }">
    <c:if test="${requestScope.f==0 }"><script>alert("账号不可为空！")</script></c:if>
    <c:if test="${requestScope.f==1 }"><script>alert("账号不存在！")</script></c:if>
    <form action ="./FindpswServlet" method="post">
    <div class="login">
	  <p>请输入账号 : <input type="text" name="userid" maxlength=10>
	  <p><input type="submit" name="submition"  value="提交" />
	  <input type="button" onclick="window.location.href='register.jsp'" value="去注册" >
	  <input type="button" onclick="window.location.href='login.jsp'" value="返回登录" >
	</div>  
	</form>
    </c:if>
    
    
    <c:if test="${requestScope.flag3==2 }">
  <div class="login">
  <c:if test="${requestScope.f==2}"><script>alert("回答错误！")</script></c:if>
  <form action ="./FindpswServlet" method="post">
      <p>账号：<input type="text" name="userid" value="${param.userid }" readonly/>
      <p>问题: <input type="text" name="problem" value="${requestScope.problem}" readonly/>
	  <p>答案: <input type="text" name="answer" maxlength=40>
	  <p> <input type="submit" name="submition"  value="找回" />
	   <input type="button" onclick="window.location.href='register.jsp'" value="去注册" >
	   <input type="button" onclick="window.location.href='login.jsp'" value="去登录" >
	</form>
  </div>
    </c:if>
    
    
        <c:if test="${requestScope.flag3==3 }">
        <c:if test="${requestScope.f==3 }"><script>alert("密码不一致，请重新输入")</script></c:if>
        <div class="login">
        	<form action="./UpdateServlet" method="post" >
       		<p>用户名：<input type="text" name="userid" value="${param.userid }" readonly/>
       		<p>请输入新密码<input type="password" name="newpsw1" maxlength=10>
      		<p>请确认密码    <input type="password" name="newpsw2" maxlength=10>
      		<br/>
      		<input type="submit" name="submition" value="重置"><input type="button" value="返回登录" onclick="window.location.href('login.jsp')"><input type="button" value="去注册" onclick="window.location.href('regidter.jsp')">
    </form>
    </div>
        </c:if>
        
        
  </body>
</html>
