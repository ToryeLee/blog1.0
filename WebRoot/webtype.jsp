<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    
    <title>My JSP 'webtype.jsp' starting page</title>
    
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
<% 
    	Show show=new Show();
    	
    	int pageCount = 10;//显示每页记录数
    	
       	int totalCount=show.getTCount("web类");//页数获取总
       	
       	
       	int currentPage=1;//当前页
       	
       	String p=null;//
       	
       	if((p=request.getParameter("ppage"))!=null){
       	 	currentPage = Integer.parseInt(p);
       	}
       	
       	Page ppage = PageUtil.createPage(pageCount, totalCount, currentPage);
       	
       	ArrayList<Article> Alist=show.getTArticle(currentPage,pageCount,"web类");
       	
       	request.setAttribute("ppage",ppage);
       	session.setAttribute("Alist",Alist);
       	
         %>
         <c:if test="${requestScope.flag==0 }"><script>alert("抱歉，暂时没有找到有关该文章的信息！")</script></c:if>
      	<form action="SearchServlet?n=4" method="post" >
        	<input type="text" name="search" style="color: rgb(153, 153, 153);" class="input_text" value="请输入关键字词 " onfocus="if(value=='请输入关键字词'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字词'}" />
        	<input type="submit" name="submition" class="input_submit" value="搜索">
      	</form>
	<div class="searchcard">
	<table width="500" border="0">  
			<c:forEach items="${sessionScope.Alist}" var="article">
				<li>
				<h3><a href="showArticle.jsp?articleid=${article.articleid }">${article.title }</a></h3>
				<p>${article.intro }</p>
				<p>点赞量（${article.likes}） 阅读量 （${article.readcount}） ${article.times }</p>
				</li>
			</c:forEach>
  	   </table>
   
  
       <br>
		<c:if test="${requestScope.ppage.totalPage!=0 }">
			<div class="pagelist">
				<c:if test="${requestScope.ppage.hasPre }">
					<a href="index.jsp?ppage=${requestScope.ppage.currentPage-1 }">上一页</a>
				</c:if>
				<c:forEach begin="1" end="${requestScope.ppage.totalPage }"  var="p">
					<a href="index.jsp?ppage=${p }">${p }</a> 
				</c:forEach>
				<c:if test="${requestScope.ppage.hasNext }">
					<a href="index.jsp?ppage=${requestScope.ppage.currentPage+1 }">下一页</a>
				</c:if>
			</div>
       </c:if>
</div>
  </body>
</html>
