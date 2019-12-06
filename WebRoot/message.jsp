<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="user.*"%>
<%@ page import="bean.*"%>
<%@ page import="pageutil.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'message.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style.css">

  </head>
  
  <body>
     <header>
		<nav id="nav">
    <ul>
    
      <li><a href="index.jsp">首页</a></li>
      	<li><a href="webtype.jsp">web类</a></li>
      	<li><a href="javatype.jsp">java类</a></li>
      	<li><a href="databasetype.jsp">数据库类</a></li>
      	<li><a href="diary.jsp">日记类</a></li>
      	<c:if test="${sessionScope.user.userid=='1375011167'}">
      		<li><a href="publish.jsp">发表博客</a></li>
      	</c:if>
      	<li><a href="aboutMe.jsp">关于我</a></li>
      	<li><a href="message.jsp">留言版</a></li>
      	<c:if test="${sessionScope.user==null}">
      		<li><a href="login.jsp">登录</a></li>
      		<li><a href="register.jsp">注册</a></li>
      	</c:if>
      	<c:if test="${sessionScope.user!=null}">
      		<li>
      			<form action="LoginServlet" method="post">
      				<input type="submit" name="submition" value="退出登录">
      			</form>
      		</li>
      	</c:if>
    </ul>
  </nav>
  <script>
window.onload = function ()
{
var obj=null;
var As=document.getElementById('nav').getElementsByTagName('a');
obj = As[0];
for(i=1;i<As.length;i++){if(window.location.href.indexOf(As[i].href)>=0)
obj=As[i];}
obj.id='selected'
}
</script> 
</header>
<c:if test="${flag==0 }"><script>alert("留言失败")</script></c:if>
<c:if test="${flag==1 }"><script>alert("留言成功！")</script></c:if>
<c:if test="${flag==2 }"><script>alert("请先登录，否则无法留言")</script></c:if>
<c:if test="${flag==3 }"><script>alert("留言内容不能为空！")</script></c:if>
		<form action="LeaveMsgServlet" method="post">
			<textarea name="msg"  rows="8" cols="100" onKeyUp="if(this.value.length>400)this.value=this.value.substr(0,400)" ></textarea><br/>
			<input type="submit" name="submition" value="留言">
		</form>
	
	
<%
	ArrayList<Messages> Mlist=new MessageOp().getMessages();
	request.setAttribute("Mlist",Mlist); %>
	
       <table width="500" border="0">  
			<c:forEach items="${requestScope.Mlist}" var="massage">
			<div class="comment">
				<li>
				<p>${massage.logname }</p>
				<h6>${massage.msg }</h6>
				<p>${massage.times}</p>
				</li>
				</div>
			</c:forEach>
  	   </table>


  </body>
</html>
