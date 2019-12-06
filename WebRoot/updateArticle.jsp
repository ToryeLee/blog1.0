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
    
    <title>My JSP 'insert.jsp' starting page</title>
    
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

<c:if test="${requestScope.flag==0 }"><script>alert("标题，类别，简介，内容，皆不能为空！")</script></c:if>
<form action="UpdateServlet" method="post">
	<div class="mid">
    <h3>发表新博客</h3>
		<p>标题<input type="text" name="title" maxlength=40 class="title" size="120" value="${sessionScope.article.title }"/><br/>
        <p>类别
        <select name="atype">
        <option value="${sessionScope.article.atype }"  >${sessionScope.article.atype }</option>
        	<option value="web类"  >web类</option>
            <option value="java类"  >java类</option>
            <option value="数据库类"  >数据库类</option>
            <option value="日记类"  >日记类</option>
        </select><br/>
        <p>简介<textarea name="intro" rows="5" cols="85" onKeyUp="if(this.value.length>100)this.value=this.value.substr(0,100)" >${sessionScope.article.intro }</textarea>
        <p>内容</p>
		<textarea name="content" rows="50" cols="85" onKeyUp="if(this.value.length>80000)this.value=this.value.substr(0,80000)" >${sessionScope.article.content }</textarea>
        <input type="submit" name="submition" value="修改" class="in" />
        <input type="submit" name="submition" value="删除" class="in" />
        <input type="submit" name="submition" value="取消" class="in" />
	</div>
</form>
  </body>
</html>
